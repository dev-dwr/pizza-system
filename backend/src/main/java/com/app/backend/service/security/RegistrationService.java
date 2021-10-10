package com.app.backend.service.security;

import com.app.backend.domain.security.AppUser;
import com.app.backend.domain.security.AppUserRole;
import com.app.backend.domain.security.ConfirmationToken;
import com.app.backend.email.EmailSender;
import com.app.backend.registration.EmailValidator;
import com.app.backend.registration.RegistrationRequest;
import com.app.backend.util.EmailBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final ConfirmationTokenService tokenService;
    private final EmailSender emailSender;
    private final EmailBuilder emailBuilder;
    private static final String REGISTRATION_LINK = "http://localhost:8080/api/v1/registration/confirm?token=";

    public String register(RegistrationRequest request){

        boolean isValidEmail = emailValidator.test(request.getEmail());

        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }

        String token =  appUserService.signUpUser(new AppUser(
                request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        ));

        String link = REGISTRATION_LINK + token;

        emailSender.send(
                request.getEmail(),
                emailBuilder.buildEmail(request.getFirstname(), link)
        );

        return token;
    }

    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = tokenService.getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if(confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        tokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());

        return "confirmed";
    }

}

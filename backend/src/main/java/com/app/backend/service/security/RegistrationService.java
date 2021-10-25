package com.app.backend.service.security;

import com.app.backend.domain.security.AppUser;
import com.app.backend.domain.security.AppUserRole;
import com.app.backend.domain.security.ConfirmationToken;
import com.app.backend.email.EmailSender;
import com.app.backend.authentication.EmailValidator;
import com.app.backend.authentication.RegistrationRequest;
import com.app.backend.util.EmailBuilder;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final ConfirmationTokenService tokenService;
    private final EmailBuilder emailBuilder;
    private final EmailSender emailSender;
    private static final String REGISTRATION_LINK = "http://localhost:8080/api/v1/registration/confirm?token=";

    public String register(RegistrationRequest request) {
        validateEmail(request);
        String token = createToken(request);
        String link = REGISTRATION_LINK + token;

        emailSender.send(
                request.getEmail(),
                emailBuilder.buildEmail(request.getFirstname(), link)
        );

        return token;
    }


    private String createToken(RegistrationRequest request) {
         return appUserService.signUpUser(new AppUser(
                request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        ));

    }

    private void validateEmail(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = tokenService.getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
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

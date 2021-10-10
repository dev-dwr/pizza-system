package com.app.backend.service.security;

import com.app.backend.domain.security.AppUser;
import com.app.backend.domain.security.ConfirmationToken;
import com.app.backend.email.EmailSender;
import com.app.backend.repository.security.AppUserRepository;
import com.app.backend.util.EmailBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService tokenService;
    private final EmailSender emailSender;
    private final EmailBuilder emailBuilder;

    private static final String REGISTRATION_LINK = "http://localhost:8080/api/v1/registration/confirm?token=";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("%s was not found in database", email)));
    }

    public String signUpUser(AppUser appUser) {

        String token = UUID.randomUUID().toString();

        boolean userExists = repository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists && !appUser.getEnabled()) {
            String link = REGISTRATION_LINK + token;
            emailSender.send(appUser.getEmail(), emailBuilder.buildEmail(appUser.getFirstname(), link));
            throw new IllegalStateException("email already taken or you haven't confirm your email");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        repository.save(appUser);




        ConfirmationToken confirmationToken = new ConfirmationToken(
                token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), appUser);

        tokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableAppUser(String email){
        return repository.enableAppUser(email);
    }

}

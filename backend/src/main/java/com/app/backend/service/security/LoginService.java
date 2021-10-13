package com.app.backend.service.security;

import com.app.backend.domain.security.AppUser;
import com.app.backend.domain.security.ConfirmationToken;
import com.app.backend.domain.security.TokenRequest;
import com.app.backend.authentication.LoginRequest;
import com.app.backend.authentication.LoginResponse;
import com.app.backend.authentication.EmailValidator;
import com.app.backend.repository.security.AppUserRepository;
import com.app.backend.repository.security.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class LoginService {
    private final AppUserRepository repository;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Transactional
    public LoginResponse loginUser(LoginRequest request) {
        String email = request.getEmail();

        boolean isValidEmail = emailValidator.test(email);
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        AppUser user = repository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("%s was not found in database", email)));
        user.setLoggedIn(true);
        ConfirmationToken token = confirmationTokenRepository.findConfirmationTokenByAppUser(user);
        return new LoginResponse(user.getLoggedIn(), token.getToken());
    }

    public void logoutUser(TokenRequest token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token.getToken()).orElseThrow(() ->
                new UsernameNotFoundException((String.format("%s token was not found in database", token.getToken()))));
        AppUser appUser = confirmationToken.getAppUser();
        appUser.setLoggedIn(false);

    }

}

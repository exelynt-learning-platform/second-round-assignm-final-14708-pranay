package com.ecommerce.app.service;

import com.ecommerce.app.model.User;
import com.ecommerce.app.security.request.SignupRequest;
import com.ecommerce.app.security.response.UserInfoResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {

    User registerUser(SignupRequest signupRequest);

    boolean existByUserName(String username);

    boolean existByEmail(String email);

    String currentUserName(Authentication authentication);

    UserInfoResponse getUserDetails(Authentication authentication);
}

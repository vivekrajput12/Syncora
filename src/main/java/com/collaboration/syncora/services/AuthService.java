package com.collaboration.syncora.services;

import com.collaboration.syncora.dtos.request.LoginReq;
import com.collaboration.syncora.dtos.request.RegisterUserReq;
import com.collaboration.syncora.dtos.response.JwtAuthResponse;
import com.collaboration.syncora.dtos.response.RegisterUser;
import com.collaboration.syncora.models.Role;
import com.collaboration.syncora.models.User;
import com.collaboration.syncora.repository.RoleRepo;
import com.collaboration.syncora.repository.UserRepo;
import com.collaboration.syncora.security.JwtUtils;
import com.collaboration.syncora.security.UserDetailImpForJwt;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private RoleRepo roleRepo;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    public RegisterUser registerUserService(RegisterUserReq req) {
        Role role = roleRepo.findRoleByRoleId(req.getRole())
                .orElseThrow(()->new RuntimeException("Provide a valid Role"));
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setName(req.getName());
        user.setRole(role);
        User savedUser = userRepo.save(user);
        return RegisterUser.from(savedUser);
    }

    public JwtAuthResponse loginUserService(LoginReq req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail() , req.getPassword())
        );
        System.out.println("checking 1");
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("checking 2");
        UserDetailImpForJwt userDetailImpForJwt = (UserDetailImpForJwt) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetailImpForJwt);
        System.out.println(">>> "+ jwt);
        return new JwtAuthResponse(jwt);
    }
}

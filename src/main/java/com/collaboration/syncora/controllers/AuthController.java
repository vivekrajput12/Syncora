package com.collaboration.syncora.controllers;

import com.collaboration.syncora.Constants.CommonConstant;
import com.collaboration.syncora.dtos.response.ApiResponse;
import com.collaboration.syncora.dtos.request.LoginReq;
import com.collaboration.syncora.dtos.request.RegisterUserReq;
import com.collaboration.syncora.dtos.response.JwtAuthResponse;
import com.collaboration.syncora.dtos.response.RegisterUser;
import com.collaboration.syncora.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterUser>> registerUser(@Valid @RequestBody RegisterUserReq req){
        try {
            RegisterUser data =  authService.registerUserService(req);
            ApiResponse <RegisterUser> apiResponse = new ApiResponse<>(0 , CommonConstant.REQUEST_SUCCESSFUL,data);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse <RegisterUser> apiResponse = new ApiResponse<>(-1 , CommonConstant.REQUEST_FAILED);
            return ResponseEntity.ok(apiResponse);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtAuthResponse>> registerUser(@Valid @RequestBody LoginReq req , HttpServletResponse response){
        try {
            JwtAuthResponse data =  authService.loginUserService(req);
            if(data.getToken()!=null){
                Cookie cookie = new Cookie("authToken", data.getToken());
//                cookie.setHttpOnly(false);
//                cookie.setSecure(false);
//                cookie.setPath("/");
//                cookie.setAttribute("SameSite", "None");
//                cookie.setMaxAge(3600);
                cookie.setHttpOnly(true);      // recommended
                cookie.setSecure(false);       // IMPORTANT for localhost
                cookie.setPath("/");
                cookie.setMaxAge(3600);
                cookie.setAttribute("SameSite", "Lax");
                response.addCookie(cookie);
            }
            ApiResponse <JwtAuthResponse> apiResponse = new ApiResponse<>(0 , CommonConstant.REQUEST_SUCCESSFUL);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse <JwtAuthResponse> apiResponse = new ApiResponse<>(-1 , CommonConstant.REQUEST_FAILED);
            System.out.println("ERROR IN LOGIN API "  + e.getMessage());
            return ResponseEntity.ok(apiResponse);
        }
    }
}

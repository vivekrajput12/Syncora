package com.collaboration.syncora.controllers;

import com.collaboration.syncora.Constants.CommonConstant;
import com.collaboration.syncora.dtos.response.ApiResponse;
import com.collaboration.syncora.dtos.AppPrincipal;
import com.collaboration.syncora.dtos.response.LoggedInUserResponse;
import com.collaboration.syncora.models.User;
import com.collaboration.syncora.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    @GetMapping("/getLoggedInUser")
    public ResponseEntity<ApiResponse<LoggedInUserResponse>>getLoggedInUserController(Authentication authentication){
        try {
            AppPrincipal principal = (AppPrincipal) authentication.getPrincipal();
            String uid = principal.getUid();
            User user = userService.getUserDetailsByUid(uid);
            LoggedInUserResponse data = LoggedInUserResponse.build(user);
            ApiResponse<LoggedInUserResponse> response = new ApiResponse<>(0, CommonConstant.REQUEST_SUCCESSFUL , data);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<LoggedInUserResponse> response = new ApiResponse<>(-1, CommonConstant.REQUEST_FAILED);
            return ResponseEntity.ok(response);
        }
    }
}

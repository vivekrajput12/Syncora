package com.collaboration.syncora.security;

import com.collaboration.syncora.models.User;
import com.collaboration.syncora.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailSerivceImp implements UserDetailsService {
    private UserRepo userRepo;
    @Override
    public UserDetailImpForJwt loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("Email not found"));
        return UserDetailImpForJwt.build(user);
    }
}

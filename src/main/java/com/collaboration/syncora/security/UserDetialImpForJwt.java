package com.collaboration.syncora.security;

import com.collaboration.syncora.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@Data
public class UserDetialImpForJwt implements UserDetails {

    private String uid;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    public UserDetialImpForJwt(String id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.uid = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetialImpForJwt build(User user){
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());
        return new UserDetialImpForJwt(
                user.getUid(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}

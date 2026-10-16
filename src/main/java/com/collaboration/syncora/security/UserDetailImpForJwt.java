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
public class UserDetailImpForJwt implements UserDetails {

    private Long uid;
    private String username;
    private String email;
    private String password;
    private String publicId;
    private Collection<? extends GrantedAuthority> authorities;
    public UserDetailImpForJwt(Long id, String username, String email, String password,String publicId, Collection<? extends GrantedAuthority> authorities) {
        this.uid = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.publicId = publicId;
    }

    public static UserDetailImpForJwt build(User user){
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());
        return new UserDetailImpForJwt(
                user.getUid(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.getPublicId(),
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

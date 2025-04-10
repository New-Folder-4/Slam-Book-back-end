package com.system.slam.web.security;

import com.system.slam.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public CustomUserDetails(Long id, String username,
                             Collection<? extends GrantedAuthority> authorities) {
        this.user = new User();
        this.user.setIdUser(id);
        this.user.setUserName(username);

        this.user.setSuperUser(authorities.stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
        this.user.setStaff(authorities.stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_STAFF")));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.isSuperUser()) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (user.isStaff()) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_STAFF"));
        } else {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    public Long getId() { return user.getIdUser(); }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}

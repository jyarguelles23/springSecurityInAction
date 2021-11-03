package com.viqsystems.chapter3userdetailsimplementations.AuxClasses;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//More Practical Implementation
public class SimpleUser implements UserDetails {
    /*
 Constructing a user with the User builder class
 * UserDetails u = User.withUsername("bill")
 .password("12345")
 .authorities("read", "write")
 .accountExpired(false)
 .disabled(true)
 .build();
 * */
    private final String username;
    private final String password;
    public SimpleUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

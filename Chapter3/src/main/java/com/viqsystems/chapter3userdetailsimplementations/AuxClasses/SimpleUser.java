package com.viqsystems.chapter3userdetailsimplementations.AuxClasses;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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
    private final String authority;
    public SimpleUser(String username, String password, String authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

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
        return true;
    }
}

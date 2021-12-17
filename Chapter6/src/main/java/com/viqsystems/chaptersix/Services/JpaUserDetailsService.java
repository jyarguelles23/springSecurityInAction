package com.viqsystems.chaptersix.Services;

import com.viqsystems.chaptersix.Entities.UserDef;
import com.viqsystems.chaptersix.Repositories.UserRepository;
import com.viqsystems.chaptersix.Utils.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository user;

    JpaUserDetailsService( UserRepository user)
    {
        this.user = user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       //Declares a supplier to create exception instances
        Supplier<UsernameNotFoundException> ex = () -> new UsernameNotFoundException("Problem during authentication!");

        UserDef u= user.findUserDefByUsername(username).orElseThrow(ex);

        return new CustomUserDetails(u); // Wraps the User instance with theCustomUserDetails decorator and returns it
    }
}

package com.group10.TalesOfWonder.security;

import com.group10.TalesOfWonder.entity.User;
import com.group10.TalesOfWonder.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class QAUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if (user != null)
            return new QAUserDetails(user);
        throw new UsernameNotFoundException("Could not find user with email " + email);
    }
}

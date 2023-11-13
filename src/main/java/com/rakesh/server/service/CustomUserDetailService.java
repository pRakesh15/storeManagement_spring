package com.rakesh.server.service;

import com.rakesh.server.model.CustomUserDetails;
import com.rakesh.server.model.User;
import com.rakesh.server.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user=userRepo.findUserByEmail(email);
        user.orElseThrow(()-> new UsernameNotFoundException("User Note found"));
        return user.map(CustomUserDetails::new).get();
    }
}

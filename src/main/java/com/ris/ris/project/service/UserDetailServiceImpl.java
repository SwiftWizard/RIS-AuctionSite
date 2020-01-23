package com.ris.ris.project.service;


import com.ris.ris.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository ur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = ur.findByUsername(username);
        if(userDetails != null){
            return userDetails;
        }else{
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

    }

    public UserDetails findUserById(Long userID){
        return ur.findById(userID).orElse(null);
    }
}

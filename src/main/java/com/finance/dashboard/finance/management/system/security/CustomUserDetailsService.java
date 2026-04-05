package com.finance.dashboard.finance.management.system.security;

import com.finance.dashboard.finance.management.system.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.finance.dashboard.finance.management.system.entities.User;
@Service
public class CustomUserDetailsService  implements UserDetailsService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new CustomUserDetails(user);
    }

}
/*Spring Security calls loadUserByUsername then
User fetched from DB then
Converted -> CustomUserDetails and
Role -> GrantedAuthority
Used in access control*/
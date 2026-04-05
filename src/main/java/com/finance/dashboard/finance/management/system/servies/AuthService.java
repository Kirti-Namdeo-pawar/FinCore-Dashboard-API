package com.finance.dashboard.finance.management.system.servies;

import com.finance.dashboard.finance.management.system.Repository.UserRepository;
import com.finance.dashboard.finance.management.system.dtos.LoginRequest;
import com.finance.dashboard.finance.management.system.dtos.LoginResponse;
import com.finance.dashboard.finance.management.system.dtos.RegisteRequestDto;
import com.finance.dashboard.finance.management.system.entities.RoleType;
import com.finance.dashboard.finance.management.system.entities.User;
import com.finance.dashboard.finance.management.system.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServiceInterface{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    public String register(RegisteRequestDto req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));


        if (req.getRole() == null) { // default role if not provided then
            user.setRole(RoleType.ROLE_VIEWER);
        } else {
            user.setRole(req.getRole());
        }

        user.setActive(true);  //saves user then encodes password then assigns role

        userRepo.save(user);

        return "User registered successfully";
    }

    // login user
    public LoginResponse login(LoginRequest req) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );

        String token = jwtUtil.generateToken(req.getUsername());

        return new LoginResponse(token);
    }
}

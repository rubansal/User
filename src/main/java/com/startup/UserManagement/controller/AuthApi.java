package com.startup.UserManagement.controller;

import com.startup.UserManagement.config.JwtTokenUtil;
import com.startup.UserManagement.dto.AuthRequest;
import com.startup.UserManagement.dto.RegisterRequest;
import com.startup.UserManagement.entity.User;
import com.startup.UserManagement.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/public")
@AllArgsConstructor
@Slf4j
public class AuthApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthRequest request){

        try{
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
            User user=(User) authentication.getPrincipal();
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwtTokenUtil.generateAccessToken(user.getUsername())).body(user);
        }catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest registerRequest){

        String username=userService.registerUser(registerRequest);
        return username;
    }
}

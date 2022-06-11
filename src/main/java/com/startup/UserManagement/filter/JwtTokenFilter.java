package com.startup.UserManagement.filter;

import com.startup.UserManagement.config.JwtTokenUtil;
import com.startup.UserManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    private final UserRepository userRepository;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String header=request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header==null || header.trim().length()==0 || !header.startsWith("Bearer ")){
            chain.doFilter(request,response);
            return;
        }

        final String token=header.split(" ")[1].trim();
        if(!jwtTokenUtil.validate(token)){
            chain.doFilter(request,response);
            return;
        }

        UserDetails userDetails=userDetailsService.loadUserByUsername(jwtTokenUtil.getUsername(token));

        UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,
                userDetails==null? new ArrayList<>() :userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);
    }
}

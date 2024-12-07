package com.security.usersecurity.security;


import com.security.usersecurity.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtility jwtUtility;

    @Autowired
    LoginService loginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(null==authHeader || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        String token = authHeader.substring(7);
        String username = jwtUtility.getExtractUserName(token);

        if(null!=username && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = loginService.loadUserByUsername(username);
            if(userDetails!=null && jwtUtility.validToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
    }
}

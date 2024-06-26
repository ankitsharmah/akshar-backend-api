package com.akshar_backend.config;

import com.akshar_backend.service.JwtService;
import com.akshar_backend.service.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailServiceImpl userDetailService;
    @Override
                    protected void doFilterInternal(
                           @NonNull HttpServletRequest request
                            ,@NonNull HttpServletResponse response
                            ,@NonNull FilterChain filterChain) throws ServletException, IOException {

                        String authHeader = request.getHeader("Authorization");
                        String jwtToken;
                        String username;

                        if (authHeader == null || !authHeader.startsWith("Bearer ")){
                            filterChain.doFilter(request,response);
                            return;
                        }

                        jwtToken = authHeader.substring(7);
                        username = jwtService.extractUserName(jwtToken);

                        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                            UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

                            if (jwtService.isTokenValid(jwtToken,userDetails)){
                                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                        userDetails,null,userDetails.getAuthorities()
                                );
                                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                                SecurityContextHolder.getContext().setAuthentication(authToken);
                            }

                        }

                filterChain.doFilter(request,response);

    }
}

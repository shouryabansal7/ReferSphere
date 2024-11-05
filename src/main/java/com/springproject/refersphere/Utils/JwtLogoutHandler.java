package com.springproject.refersphere.Utils;

import com.springproject.refersphere.service.JwtBlacklistService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtLogoutHandler implements LogoutHandler {

    private final JwtBlacklistService jwtBlacklistService;

    public JwtLogoutHandler(JwtBlacklistService jwtBlacklistService) {
        this.jwtBlacklistService = jwtBlacklistService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // Get the JWT token from the request (typically from the Authorization header)
        String jwtToken = request.getHeader("Authorization");
        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7); // Remove "Bearer " prefix
            // Optionally add the token to the blacklist (if you're using one)
            jwtBlacklistService.blacklistToken(jwtToken);
        }

        // Clear the authentication context
        SecurityContextHolder.clearContext();
    }
}


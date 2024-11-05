package com.springproject.refersphere.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class JwtBlacklistService {

    // Simple in-memory blacklist (for demonstration)
    private Set<String> blacklistedTokens = new HashSet<>();

    // Add token to blacklist
    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    // Check if a token is blacklisted
    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}

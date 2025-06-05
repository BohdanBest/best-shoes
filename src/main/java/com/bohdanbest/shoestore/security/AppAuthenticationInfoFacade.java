package com.bohdanbest.shoestore.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AppAuthenticationInfoFacade implements AuthenticationInfoFacade {

    @Override
    public boolean isAuthenticated() {
        Authentication auth = getAuthentication();
        return isNotAnonymousAuthentication(auth) && auth.isAuthenticated();
    }

    @Override
    public boolean isAdmin() {
        Authentication auth = getAuthentication();
        if (isNotAnonymousAuthentication(auth)) {
            return auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_" + Role.ADMIN.name()));
        }
        return false;
    }

    @Override
    public String getUsername() {
        Authentication auth = getAuthentication();
        if (isNotAnonymousAuthentication(auth)) {
            return auth.getName();
        }
        return "";
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private boolean isNotAnonymousAuthentication(Authentication authentication) {
        return !(authentication instanceof AnonymousAuthenticationToken);
    }
}
package com.bohdanbest.shoestore.security;

public interface AuthenticationInfoFacade {
    boolean isAuthenticated();
    boolean isAdmin();
    String getUsername();
}
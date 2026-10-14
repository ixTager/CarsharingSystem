package com.ixtager.carsharingsystem.enumerating;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {
    DISPATCHER,
    MECHANIC;

    public SimpleGrantedAuthority toAuthority() {
        return new SimpleGrantedAuthority("ROLE_" + this.name());
    }
}

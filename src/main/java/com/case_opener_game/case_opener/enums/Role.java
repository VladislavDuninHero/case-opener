package com.case_opener_game.case_opener.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum Role implements GrantedAuthority {
    USER("user"),
    ADMIN("admin");

    private final String name;

    @Override
    public String getAuthority() {
        return name();
    }
}

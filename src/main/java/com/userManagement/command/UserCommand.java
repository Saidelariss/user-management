package com.userManagement.command;

import com.userManagement.enums.UserRole;
import lombok.Getter;

@Getter
public class UserCommand {
    private final String username;
    private final String email;
    private final UserRole role;

    public UserCommand(String username, String email, UserRole role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }



}

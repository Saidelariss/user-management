package com.userManagement.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MessageCommand {
    private final String content;
    private final String senderId;
    private final String receiverId;
}

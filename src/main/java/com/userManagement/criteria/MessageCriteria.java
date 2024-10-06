package com.userManagement.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MessageCriteria {
    private final String senderId;
    private final String receiverId;
}

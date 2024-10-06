package com.userManagement.dto;

import com.userManagement.domain.Message;
import com.userManagement.domain.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class MessageDto {
    private final String content;
    private final LocalDateTime sendedAt;

    public MessageDto(Message message){
        content=message.getContent();
        sendedAt = message.getSendedAt();
    }
}

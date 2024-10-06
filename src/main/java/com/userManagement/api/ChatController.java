package com.userManagement.api;

import com.userManagement.command.MessageCommand;
import com.userManagement.criteria.MessageCriteria;
import com.userManagement.domain.Message;
import com.userManagement.domain.UserEntity;
import com.userManagement.dto.MessageDto;
import com.userManagement.repository.MessageRepository;
import com.userManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    private final UserRepository userRepository;
    private final MessageRepository repository;

    @PostMapping
    public ResponseEntity<Void> sendMessages(@RequestBody MessageCommand messageCommand){
        UserEntity sender = userRepository.findById(messageCommand.getSenderId()).orElseThrow(()-> new RuntimeException("sender not found"));
        UserEntity receiver = userRepository.findById(messageCommand.getReceiverId()).orElseThrow(()-> new RuntimeException("receiver not found"));
        Message message = new Message(null, messageCommand.getContent(),sender,receiver, LocalDateTime.now());
        repository.save(message);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<MessageDto> getMessagesBySenderId(MessageCriteria criteria){
        List<Message> messges = repository.findBySenderId(criteria.getSenderId(),criteria.getReceiverId());
        return messges.stream().map(MessageDto::new).toList();
    }
}

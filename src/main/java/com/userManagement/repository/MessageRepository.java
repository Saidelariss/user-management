package com.userManagement.repository;

import com.userManagement.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,String> {
    @Query("select m from Message m where m.sender.id = :senderId and m.receiver.id = :receiverId")
    List<Message> findBySenderId(String senderId,String receiverId);
}

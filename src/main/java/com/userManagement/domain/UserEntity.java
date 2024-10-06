package com.userManagement.domain;

import com.userManagement.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToMany(mappedBy = "sender")
    List<Message> sentMessages = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    List<Message> receivedMessages = new ArrayList<>();
}

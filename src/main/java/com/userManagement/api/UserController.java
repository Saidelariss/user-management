package com.userManagement.api;

import com.userManagement.command.UserCommand;
import com.userManagement.criteria.UserCriteria;
import com.userManagement.domain.UserEntity;
import com.userManagement.enums.UserRole;
import com.userManagement.repository.UserRepository;
import com.userManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;
    private final UserService userService;


    @PostMapping
    private ResponseEntity<Void> saveNewUser(@RequestBody UserCommand userCommand){
        log.info("begin creating a new user");
        UserEntity user = new UserEntity();
        user.setUsername(userCommand.getUsername());
        user.setEmail(userCommand.getEmail());
        user.setRole(userCommand.getRole());
        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<UserEntity> getUsers(){
        log.info("getting all users");
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id,@RequestBody UserCommand userCommand){
        UserEntity user = repository.findById(id).orElseThrow(()->new RuntimeException("user not found"));
        if(Objects.nonNull(userCommand.getUsername())){
            user.setUsername(userCommand.getUsername());
        }
        if(Objects.nonNull(userCommand.getEmail())){
            user.setEmail(userCommand.getEmail());
        }
        if(Objects.nonNull(userCommand.getRole())){
            user.setRole(userCommand.getRole());
        }

        repository.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        UserEntity user = repository.findById(id).orElseThrow(()->new RuntimeException("user not found"));
        repository.delete(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public UserEntity getUserDetailsById(@PathVariable String id){
        return repository.findById(id).orElseThrow(()->new RuntimeException("user not found"));
    }

    @GetMapping("search")
    public Page<UserEntity> getUsersByCriteria(Pageable pageable, UserCriteria userCriteria){
        System.out.println(userCriteria);
            return repository.findAllUsersByCriteria(userCriteria,pageable);
    }

    @GetMapping("query")
    public List<UserEntity> getUserByEmailAndUsername(@RequestParam String keyword){
            return userService.findUserByEmailAndUsername(keyword);
    }

}


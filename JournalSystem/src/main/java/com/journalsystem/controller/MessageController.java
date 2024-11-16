package com.journalsystem.controller;

import com.journalsystem.model.Message;
import com.journalsystem.model.User;
import com.journalsystem.model.*;
import com.journalsystem.dto.*;
import com.journalsystem.service.MessageService;
import com.journalsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Optional<Message> message = messageService.getMessageById(id);
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/sent/{userId}")
    public ResponseEntity<List<Message>> getMessagesBySender(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(value -> ResponseEntity.ok(messageService.getMessagesBySender(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/received/{userId}")
    public ResponseEntity<List<Message>> getMessagesByReceiver(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(value -> ResponseEntity.ok(messageService.getMessagesByReceiver(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<MessageDTO>> getMessagesForCurrentUser(Authentication authentication) {
        List<MessageDTO> messages = messageService.getMessagesForUser(authentication.getName());
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/send")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO, Authentication authentication) {
        messageService.sendMessage(messageDTO, authentication.getName());
        return ResponseEntity.ok("Message sent successfully");
    }
}

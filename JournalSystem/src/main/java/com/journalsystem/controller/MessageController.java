package com.journalsystem.controller;

import com.journalsystem.model.Message;
import com.journalsystem.model.User;
import com.journalsystem.model.*;
import com.journalsystem.dto.*;
import com.journalsystem.service.MessageService;
import com.journalsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/conversation")
    public ResponseEntity<List<MessageDTO>> getConversation(
            @RequestParam Long userId1,
            @RequestParam Long userId2,
            Authentication authentication) {
        User currentUser = userService.findUserByUsername(authentication.getName());

        // Kontrollera att användarna har tillåtelse att kommunicera
        if (currentUser.hasRole("PATIENT")) {
            if (!userService.isPractitioner(userId2)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Patients can only communicate with practitioners");
            }
        } else if (currentUser.hasRole("DOCTOR") || currentUser.hasRole("STAFF")) {
            if (!userService.isPatient(userId2)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Practitioners can only communicate with patients");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid user role");
        }

        // Hämta konversationen om kontrollen passerar
        List<Message> conversation = messageService.getConversation(userId1, userId2);
        return ResponseEntity.ok(conversation.stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList()));
    }


    @PostMapping("/send")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO, Authentication authentication) {
        messageService.sendMessage(messageDTO, authentication.getName());
        return ResponseEntity.ok("Message sent successfully");
    }
}

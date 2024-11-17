package com.journalsystem.service;

import com.journalsystem.dto.MessageDTO;
import com.journalsystem.model.Message;
import com.journalsystem.model.User;
import com.journalsystem.repository.MessageRepository;
import com.journalsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public List<Message> getMessagesBySender(User sender) {
        return messageRepository.findBySender(sender);
    }

    public List<Message> getMessagesByReceiver(User receiver) {
        return messageRepository.findByReceiver(receiver);
    }


    public List<MessageDTO> getMessagesForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return messageRepository.findByReceiver(user).stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
    }

    public void sendMessage(MessageDTO messageDTO, String senderUsername) {
        User sender = userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Sender not found"));
        User receiver = userRepository.findById(messageDTO.getReceiverId())
                .orElseThrow(() -> new UsernameNotFoundException("Receiver not found"));

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(messageDTO.getContent());
        message.setSentAt(new Date());
        messageRepository.save(message);
    }


    public List<Message> getConversation(Long userId1, Long userId2) {
        // Hämta meddelanden där userId1 är avsändare och userId2 är mottagare
        List<Message> messages1 = messageRepository.findBySenderIdAndReceiverIdOrderBySentAtAsc(userId1, userId2);

        // Hämta meddelanden där userId2 är avsändare och userId1 är mottagare
        List<Message> messages2 = messageRepository.findByReceiverIdAndSenderIdOrderBySentAtAsc(userId1, userId2);

        // Kombinera och returnera listan
        List<Message> allMessages = new ArrayList<>();
        allMessages.addAll(messages1);
        allMessages.addAll(messages2);

        // Sortera listan efter skickat-tid
        allMessages.sort((m1, m2) -> m1.getSentAt().compareTo(m2.getSentAt()));

        return allMessages;
    }
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}

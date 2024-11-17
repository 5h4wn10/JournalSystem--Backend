package com.journalsystem.repository;

import com.journalsystem.model.Message;
import com.journalsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySender(User sender);
    List<Message> findByReceiver(User receiver);

    List<Message> findBySenderIdAndReceiverIdOrderBySentAtAsc(Long senderId, Long receiverId);

    List<Message> findByReceiverIdAndSenderIdOrderBySentAtAsc(Long receiverId, Long senderId);
}

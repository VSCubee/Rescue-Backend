package com.rescue.vscube.Message;

import com.rescue.vscube.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByEvent(Event event);
}

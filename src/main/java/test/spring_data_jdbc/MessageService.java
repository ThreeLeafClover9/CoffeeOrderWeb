package test.spring_data_jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }
}

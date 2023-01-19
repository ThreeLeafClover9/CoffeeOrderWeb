package test.hello_world;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final MessageMapper messageMapper;

    @PostMapping
    public ResponseEntity postMessage(@Valid @RequestBody MessagePostDto messagePostDto) {
        Message message = messageMapper.messagePostDtoToMessage(messagePostDto);
        Message response = messageService.createMessage(message);
        MessageResponseDto messageResponseDto = messageMapper.messageToMessageResponseDto(response);
        return ResponseEntity.created(URI.create("/" + messageResponseDto.getMessageId())).body(messageResponseDto);
    }
}

package test.spring_data_jdbc;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    Message messagePostDtoToMessage(MessagePostDto messagePostDto);

    MessageResponseDto messageToMessageResponseDto(Message message);
}

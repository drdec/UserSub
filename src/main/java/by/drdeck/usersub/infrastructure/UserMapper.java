package by.drdeck.usersub.infrastructure;

import by.drdeck.usersub.dto.UserDto;
import by.drdeck.usersub.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto userDto);
    UserDto toDto(User user);
}

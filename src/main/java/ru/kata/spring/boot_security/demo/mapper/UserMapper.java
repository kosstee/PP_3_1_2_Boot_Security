package ru.kata.spring.boot_security.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    List<UserDTO> usersToUserDTOs(List<User> users);
}

package by.drdeck.usersub.service;

import by.drdeck.usersub.dto.UserDto;
import by.drdeck.usersub.infrastructure.UserMapper;
import by.drdeck.usersub.model.User;
import by.drdeck.usersub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, UserDto updatedUser) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());
        userRepository.save(user);

        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

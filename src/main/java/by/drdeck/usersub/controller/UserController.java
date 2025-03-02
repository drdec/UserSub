package by.drdeck.usersub.controller;

import by.drdeck.usersub.dto.UserDto;
import by.drdeck.usersub.model.User;
import by.drdeck.usersub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    public final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto user) {
        logger.info("создание пользователя: {}", user);
        User createdUser = userService.createUser(user);
        logger.info("Пользователь создан");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        logger.info("Возврат информации о пользователе {}", id);
        User user = userService.getUserById(id);

        if (user == null) {
            logger.warn("Пользователь с id {} не найден", id);
            return ResponseEntity.notFound().build();
        }

        logger.info("Пользователь с id {} найден", id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("Запрос на удаление пользователя с id {}", id);
        userService.deleteUser(id);
        logger.info("Пользователь с id {} удален", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        logger.info("Запрос на обновление пользователя id {}, {}", id, dto);
        User user = userService.updateUser(id, dto);

        if (user == null){
            logger.error("Пользователь не найден");
            return ResponseEntity.notFound().build();
        }

        logger.info("Пользователь с id {} обновлен" , id);
        return ResponseEntity.ok(user);
    }
}

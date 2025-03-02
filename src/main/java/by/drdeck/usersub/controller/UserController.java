package by.drdeck.usersub.controller;

import by.drdeck.usersub.dto.UserDto;
import by.drdeck.usersub.model.User;
import by.drdeck.usersub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    public final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        User user = userService.updateUser(id, dto);

        if (user == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }
}

package com.connectpdv.pdv.controller;

import com.connectpdv.pdv.mapper.UserMapper;
import com.connectpdv.pdv.service.UserService;
import com.util.commons.dto.UserDTO;
import com.util.commons.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper =  userMapper;
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserDTO>> allUsers() {
        try {
            List<User> users = userService.listALl();

            if (users == null || users.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            List<UserDTO> usersDto = userMapper.toList(users);
            return ResponseEntity.ok(usersDto);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/search-user/{id}")
    public ResponseEntity<UserDTO> searchUser(@PathVariable Long id) {
        try {
            User user = userService.findUserById(id);

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            UserDTO userDto = userMapper.toDto(user);
            return ResponseEntity.ok(userDto);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
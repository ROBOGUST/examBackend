package com.robogust.examBackend.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete_user/{id}")
    private void deleteUserById(@PathVariable("id") long id) {
        userService.delete(id);
    }

    @PutMapping("/update_user/{id}")
    private ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        return userService.update(user.getUsername(), user.getRole(), id);
    }
}


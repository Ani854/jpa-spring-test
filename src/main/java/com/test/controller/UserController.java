package com.test.controller;

import com.test.model.User;
import com.test.service.UserService;
import com.test.util.exception.DuplicateException;
import com.test.util.exception.NotFoundException;
import com.test.util.exception.TimeOutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable int id) throws NotFoundException {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> userList = userService.getAll();
        return ResponseEntity.ok(userList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public void save(@Valid @RequestBody User user) throws DuplicateException {
        userService.save(user);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        User user = userService.getByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/verify")
    public void verifyCode(@RequestParam("verificationCode") String verificationCode) throws NotFoundException, TimeOutException {
        userService.verifyUser(verificationCode);
    }

    @PostMapping("/reset")
    public void resetPassword(@RequestParam("email") String email) throws NotFoundException {
        userService.resetPassword(email);
    }

    @PostMapping("/new-password")

    public void changePassword(@RequestParam("token") String token, @RequestParam("password") String password) throws NotFoundException {
        userService.changePassword(token, password);
    }
}

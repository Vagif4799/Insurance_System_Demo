package com.insurance_system.controller;

import com.insurance_system.model.User;
import com.insurance_system.service.UserService;
import com.insurance_system.utilities.EmailUtil;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private EmailUtil emailUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserController(UserService userService, EmailUtil emailUtil) {
        this.userService = userService;
        this.emailUtil = emailUtil;
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        LOGGER.info("Inside getAllUsers() method");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        LOGGER.info("getUserById() is invoked by given id: " + id);
        return userService.getUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        LOGGER.info("New User Created in createUser() method: " + user);
        user.setPassword(encoder.encode(user.getPassword()));
        userService.createUser(user);
        emailUtil.sendEmail("vagif.dev@gmail.com", "Please, click to complete the registration", "http://localhost:8080/users?status=true");
    }

    @PutMapping("/{id}")
    public User edit_user(@RequestBody User user, @PathVariable("id") Long id) {
        LOGGER.info("edit_user() is invoked by User: {} and id: {}", user, id);
        return userService.editUser(user, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        LOGGER.info("deleteUser() is invoked by id: " + id);
    }

    @GetMapping("/{status}")
    public void completeRegister(@RequestBody User user,@PathVariable("status") boolean status) {
        LOGGER.info("The Authorization Link has sent to the user with the status of true");
        userService.completeRegister(user, status);
    }

}

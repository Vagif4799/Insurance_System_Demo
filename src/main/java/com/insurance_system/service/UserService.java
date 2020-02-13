package com.insurance_system.service;

import com.insurance_system.bean.NullAwareBeanUtilsBean;
import com.insurance_system.model.User;
import com.insurance_system.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User editUser(User user, Long id) {

        Optional<User> old_user = userRepository.findById(id);
        old_user.ifPresent(
                u -> {
                    NullAwareBeanUtilsBean bean = new NullAwareBeanUtilsBean();
                    try {
                        bean.copyProperties(u, user);
                        if (user.getUsername() != null) u.setUsername(user.getUsername());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    userRepository.save(u);
                });
        return getUserById(id).get();
    }

    public void deleteUser(Long id) {
         userRepository.deleteById(id);
    }


}

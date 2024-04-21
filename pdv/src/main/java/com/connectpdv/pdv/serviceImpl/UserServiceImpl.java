package com.connectpdv.pdv.serviceImpl;

import com.connectpdv.pdv.repository.UserRepository;
import com.connectpdv.pdv.service.UserService;
import com.util.commons.entity.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        user.setRegisterDate(Date.valueOf(LocalDate.now()).toLocalDate());

        if (userRepository.findByUserNameEquals(user.getUserName()) != null) {
            throw new IllegalArgumentException("User already exists");
        }

        if (userRepository.findByPersonIdEquals(user.getPerson().getId()) != null) {
            throw new IllegalArgumentException("Person already exists");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public List<User> listALl() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findUserBy(String userName) {
        return userRepository.findByUserNameEquals(userName);
    }
}

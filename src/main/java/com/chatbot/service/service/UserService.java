package com.chatbot.service.service;

import com.chatbot.service.enums.Status;
import com.chatbot.service.model.User.User;
import com.chatbot.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    public void disconnect(User user) {
        if (userRepository.findById(user.getNickname()) != null) {
            user.setStatus(Status.OFFLINE);
            userRepository.save(user);
        }
    }

    public List<User> findConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}

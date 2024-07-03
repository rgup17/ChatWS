package com.chatbot.service.controller;

import com.chatbot.service.model.User.User;
import com.chatbot.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    /**
     * When a client sends a msg to /user/add, this method is invoked
     * SendTo(/user/topic) specifies that return value of addUser is sent to /user/topic - subscribe to the topic - queue
     *
     * @param user has Payload annotation is to deserialize the Websocket msg into User object
     * @return User object, which is sent to "/user/topic". All clients subscribed to this topic will receive the User object
     */
    @MessageMapping("/user/add")
    @SendTo("/user/topic")
    public User addUser(@Payload User user) {
        service.saveUser(user);
        return user;
    }

    /**
     * disconnects the user and notifies to the queue that the user is disconnected
     *
     * @param user
     * @return
     */
    @MessageMapping("/user/disconnect")
    @SendTo("/user/topic")
    public User disconnect(@Payload User user) {
        service.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        return ResponseEntity.ok(service.findConnectedUsers());
    }

}

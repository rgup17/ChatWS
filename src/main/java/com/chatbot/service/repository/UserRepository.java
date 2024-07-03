package com.chatbot.service.repository;

import com.chatbot.service.enums.Status;
import com.chatbot.service.model.User.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAllByStatus(Status status);

}

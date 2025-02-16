package com.chatbot.service.model.User;

import com.chatbot.service.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class User {
    @Id
    private String nickname;
    private String fullName;
    private Status status;
}

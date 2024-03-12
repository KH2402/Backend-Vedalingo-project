package com.vedalingo.models.translation;

import lombok.Data;

import java.util.Collections;
import java.util.List;


@Data
public class ChatCompletionRequest {

    private final String model;
    private final List<ChatMessage> messages;

    public ChatCompletionRequest(String model,String prompt){
        this.model=model;
        this.messages= Collections.singletonList(new ChatMessage("user",prompt));
    }
}

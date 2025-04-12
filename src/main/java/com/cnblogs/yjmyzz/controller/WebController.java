package com.cnblogs.yjmyzz.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class WebController {

    @Autowired
    ChatClient chatClient;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring AI!";
    }

    @RequestMapping("/chat")
    public String chat(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    @RequestMapping(value = "/chat-stream",produces = "text/html;charset=utf-8")
    public Flux<String> chatStream(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .stream()
                .content();
    }

}
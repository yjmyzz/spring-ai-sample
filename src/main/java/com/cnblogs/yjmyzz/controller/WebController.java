package com.cnblogs.yjmyzz.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class WebController {

    @Autowired
    @Qualifier("ollamaClient")
    ChatClient ollamaClient;

    @Autowired
    @Qualifier("openaiClient")
    ChatClient openaiClient;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring AI!";
    }

    @RequestMapping("/ollama/chat")
    public String ollamaChat(String prompt) {
        return ollamaClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    @RequestMapping(value = "/ollama/chat-stream", produces = "text/html;charset=utf-8")
    public Flux<String> ollamaChatStream(String prompt) {
        return ollamaClient.prompt()
                .user(prompt)
                .stream()
                .content();
    }

    @RequestMapping("/openai/chat")
    public String openAIChat(String prompt) {
        return openaiClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    @RequestMapping(value = "/openai/chat-stream", produces = "text/html;charset=utf-8")
    public Flux<String> openAIChatStream(String prompt) {
        return openaiClient.prompt()
                .user(prompt)
                .stream()
                .content();
    }

}
package com.cnblogs.yjmyzz.controller;


import com.cnblogs.yjmyzz.advisor.ConsoleOutputAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

import static com.cnblogs.yjmyzz.consts.AppConstant.MAX_HISTORY_SESSION;

@RestController
@RequestMapping("/api")
public class WebController {


    @Autowired
    ChatClient chatClient;

    @Autowired
    ChatMemory chatMemory;

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

    @RequestMapping(value = "/chat-stream", produces = "text/html;charset=utf-8")
    public Flux<String> chatStream(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .stream()
                .content();
    }

    @GetMapping("/history")
    public List<Message> history(String conversationId) {
        return chatMemory.get(conversationId, MAX_HISTORY_SESSION);
    }

    @DeleteMapping("/history")
    public String delHistory(String conversationId) {
        chatMemory.clear(conversationId);
        return "清除成功";
    }

    @RequestMapping("/conversation")
    public String conversation(@RequestParam String conversationId, @RequestParam String prompt) {
        // 1. 存储用户消息
        chatMemory.add(conversationId, new UserMessage(prompt));
        // 2. 获取历史消息
        List<Message> history = chatMemory.get(conversationId, MAX_HISTORY_SESSION);
        // 3. 调用大模型
        return chatClient.prompt(new Prompt(history))
                .call()
                .content();

    }

    @RequestMapping(value = "/conversation-stream", produces = "text/html;charset=utf-8")
    public Flux<String> conversationStream(@RequestParam String conversationId, @RequestParam String prompt) {
        // 1. 当前新问题，扔到聊天上下文中
        chatMemory.add(conversationId, new UserMessage(prompt));
        // 2. 把历史消息全都取出来
        List<Message> history = chatMemory.get(conversationId, MAX_HISTORY_SESSION);
        // 3. 所有历史消息，扔给大模型
        return chatClient.prompt(new Prompt(history))
                .stream()
                .content();
    }
}
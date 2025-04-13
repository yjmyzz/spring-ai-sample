package com.cnblogs.yjmyzz;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.List;

@SpringBootApplication
public class SpringAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner run(ChatClient chatClient) {
//        return args -> {
//
//            String content = chatClient.prompt()
//                    .user("你叫什么名字，请用英文回答")
//                    .call()
//                    .content();
//
//            System.out.printf("%s%n", content);
//        };
//    }
}
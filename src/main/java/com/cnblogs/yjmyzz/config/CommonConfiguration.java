package com.cnblogs.yjmyzz.config;

import com.cnblogs.yjmyzz.MyMessageWindowChatMemory;
import com.cnblogs.yjmyzz.advisor.ConsoleOutputAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.RequestResponseAdvisor;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.*;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CommonConfiguration {

    @Bean
    public ChatClient chatClient(OllamaChatModel model, ChatMemory chatMemory) {
        List<RequestResponseAdvisor> advisors = new ArrayList<>();
        advisors.add(new MessageChatMemoryAdvisor(chatMemory));
        advisors.add(new ConsoleOutputAdvisor());
        return ChatClient.builder(model)
                .defaultSystem("你是一名小学计算机老师，名叫张老师，回答问题时请使用中文")
                .defaultAdvisors(advisors)
                .build();
    }

    @Bean
    public ChatMemoryRepository chatMemoryRepository() {
        return new InMemoryChatMemoryRepository();
    }


    @Bean
    public ChatMemory chatMemory(ChatMemoryRepository chatMemoryRepository) {
        return MyMessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(10)
                .build();

    }
}

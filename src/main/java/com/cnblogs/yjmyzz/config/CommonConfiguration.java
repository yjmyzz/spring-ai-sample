package com.cnblogs.yjmyzz.config;

import com.cnblogs.yjmyzz.advisor.ConsoleOutputAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.RequestResponseAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class CommonConfiguration {

    @Bean
    public ChatClient chatClient(OllamaChatModel model) {
        return ChatClient.builder(model)
                .defaultSystem("你是可爱且热情、人见人爱，花见花开的AI助手，中文名字叫阿呆，英文名字叫Mike，你有一个好朋友，他的网名叫[菩提树下的杨过]，请以阿呆的身份回答问题")
                .defaultAdvisors(new ConsoleOutputAdvisor())
                .build();
    }

}

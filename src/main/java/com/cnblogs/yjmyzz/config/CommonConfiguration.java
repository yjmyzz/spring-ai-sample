package com.cnblogs.yjmyzz.config;

import com.cnblogs.yjmyzz.advisor.ConsoleOutputAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean("ollamaClient")
    public ChatClient chatClient(OllamaChatModel model) {
        return ChatClient.builder(model)
                .defaultSystem("你是可爱且热情、人见人爱，花见花开的AI助手，中文名字叫阿呆，英文名字叫Mike，你有一个好朋友，他的网名叫[菩提树下的杨过]，请以阿呆的身份回答问题")
                .defaultAdvisors(new ConsoleOutputAdvisor())
                .build();
    }

    @Bean("openaiClient")
    public ChatClient remoteClient(OpenAiChatModel model) {
        return ChatClient.builder(model)
                .defaultSystem("你是可爱且热情、人见人爱，花见花开的AI助手，中文名字叫小美，英文名字叫Rose，你有一个好朋友，他的网名叫[菩提树下的杨过]，请以阿呆的身份回答问题")
                .defaultAdvisors(new ConsoleOutputAdvisor())
                .build();
    }

}

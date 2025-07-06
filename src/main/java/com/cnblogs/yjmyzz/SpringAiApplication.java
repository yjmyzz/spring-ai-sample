package com.cnblogs.yjmyzz;

import com.cnblogs.yjmyzz.mcp.server.AuthorService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider weatherTools(AuthorService weatherService) {
        return  MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
    }

}
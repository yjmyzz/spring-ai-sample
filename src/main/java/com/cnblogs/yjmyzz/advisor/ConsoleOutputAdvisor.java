package com.cnblogs.yjmyzz.advisor;

import org.springframework.ai.chat.client.AdvisedRequest;
import org.springframework.ai.chat.client.RequestResponseAdvisor;
import org.springframework.ai.chat.model.ChatResponse;

import java.util.Map;

public class ConsoleOutputAdvisor implements RequestResponseAdvisor {


    public AdvisedRequest adviseRequest(AdvisedRequest request, Map<String, Object> context) {
        System.out.printf("request=> %s%n", request.userText());
        return request;
    }

    public ChatResponse adviseResponse(ChatResponse response, Map<String, Object> context) {
        var output = response.getResults().getFirst().getOutput();
        if (output != null) {
            String content = output.getContent();
            int index = content.lastIndexOf("</think>");
            if (index != -1) {
                System.out.printf("response=> %s%n", content.substring(index + 10));
            } else {
                System.out.printf("response=> %s%n", content);
            }
        }
        return response;
    }

}

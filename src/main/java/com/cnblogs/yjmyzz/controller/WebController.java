package com.cnblogs.yjmyzz.controller;

import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.zhipuai.ZhiPuAiImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Base64;

@RestController
@RequestMapping("/api")
public class WebController {

    @Autowired
    private ZhiPuAiImageModel zhiPuAiImageModel;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring AI!";
    }

    @GetMapping("/image/url")
    public String genImageUrl(@RequestParam String prompt) {
        var options = ImageOptionsBuilder.builder().height(256).width(256).build();
        ImageResponse response = zhiPuAiImageModel.call(
                new ImagePrompt(prompt,
                        options));
        Image output = response.getResult().getOutput();
        return output.getUrl();
    }

    @GetMapping("/image")
    public ResponseEntity<ByteArrayResource> genImage(@RequestParam String prompt) {
        try {
            var options = ImageOptionsBuilder.builder().height(256).width(256).build();
            ImageResponse response = zhiPuAiImageModel.call(
                    new ImagePrompt(prompt, options));
            Image output = response.getResult().getOutput();
            
            // 获取图片URL
            String imageUrl = output.getUrl();
            
            // 下载图片数据
            URL url = URI.create(imageUrl).toURL();
            byte[] imageData = url.openStream().readAllBytes();
            
            // 创建ByteArrayResource
            ByteArrayResource resource = new ByteArrayResource(imageData);
            
            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentDispositionFormData("attachment", "generated-image.png");
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
                    
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/image/display")
    public ResponseEntity<ByteArrayResource> displayImage(@RequestParam String prompt) {
        try {
            var options = ImageOptionsBuilder.builder().height(256).width(256).build();
            ImageResponse response = zhiPuAiImageModel.call(
                    new ImagePrompt(prompt, options));
            Image output = response.getResult().getOutput();
            
            // 获取图片URL
            String imageUrl = output.getUrl();
            
            // 下载图片数据
            URL url = URI.create(imageUrl).toURL();
            byte[] imageData = url.openStream().readAllBytes();
            
            // 创建ByteArrayResource
            ByteArrayResource resource = new ByteArrayResource(imageData);
            
            // 设置响应头 - 直接在浏览器中显示
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
                    
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
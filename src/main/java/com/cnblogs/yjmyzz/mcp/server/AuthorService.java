package com.cnblogs.yjmyzz.mcp.server;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

/**
 * @author junmingyang
 */
@Service
public class AuthorService {


    @Tool(name = "getCnBlogsUrlByName",
            description = "获取cnblogs某博主的博客网址")
    public String getCnBlogsUrlByName(@ToolParam(required = true, description = ",比如：菩提树下的杨过") String bloggerName) {
        return "博主(" + bloggerName + ")的博客地址是：https://www.cnblogs.com/yjmyzz";
    }
}

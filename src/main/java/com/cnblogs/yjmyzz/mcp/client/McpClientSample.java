package com.cnblogs.yjmyzz.mcp.client;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema;

import java.util.Map;

/**
 * @author junmingyang
 */
public class McpClientSample {

    public static void main(String[] args) {
        //注：目前spring-ai的源码，并非提供设置http请求头的方式，下面的代码，无法设置Authorization请求头，请求将超时失败
        HttpClientSseClientTransport webFluxSseTransport = HttpClientSseClientTransport
                .builder("http://localhost:8080")
                .build();

        McpSyncClient mcpClient = McpClient.sync(webFluxSseTransport).build();

        McpSchema.InitializeResult initialize = mcpClient.initialize();
        System.out.println("initialize=>" + initialize);

        Object ping = mcpClient.ping();
        System.out.println("ping=>" + ping);

        McpSchema.ListToolsResult toolsList = mcpClient.listTools();

        System.out.println(toolsList);

        McpSchema.CallToolResult orderStatus = mcpClient.callTool(
                new McpSchema.CallToolRequest("queryOrderStatus",
                        Map.of("orderNo", "25070601")));
        System.out.println(orderStatus);

        mcpClient.closeGracefully();
    }

}

package com.cnblogs.yjmyzz.mcp.client;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.WebFluxSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

/**
 * @author junmingyang
 */
public class McpClientSample {

    public static void main(String[] args) {
        WebFluxSseClientTransport webFluxSseTransport = new WebFluxSseClientTransport(WebClient.builder().baseUrl("http://localhost:8080"));

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

package com.cnblogs.yjmyzz.mcp.client;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import org.junit.Test;

import java.util.Map;

/**
 * @author junmingyang
 */
public class McpClientSample {

    @Test
    public void testMcpClientSample() {

        ServerParameters stdioParams = ServerParameters.builder("java")
                .args("-jar", "D:\\code\\spring-ai-sample\\target\\spring-ai-0.0.1-SNAPSHOT.jar")
                .build();

        StdioClientTransport stdioTransport = new StdioClientTransport(stdioParams);

        McpSyncClient mcpClient = McpClient.sync(stdioTransport).build();

        mcpClient.initialize();

        McpSchema.ListToolsResult toolsList = mcpClient.listTools();

        System.out.println(toolsList);

        McpSchema.CallToolResult blogUrl = mcpClient.callTool(
                new McpSchema.CallToolRequest("queryOrderStatus",
                        Map.of("orderNo", "25070601")));
        System.out.println(blogUrl);


        mcpClient.closeGracefully();


    }
}

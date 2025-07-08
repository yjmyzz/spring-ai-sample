package com.cnblogs.yjmyzz.mcp.client;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Test
    public void testMcpClientSseSample() {

        HttpClientSseClientTransport transport = HttpClientSseClientTransport.builder("http://localhost:8080").build();
        McpSyncClient client = McpClient.sync(transport).build();

        client.initialize();
        System.out.println("是否已经初始化:" + client.isInitialized());

        McpSchema.ListToolsResult listToolsResult = client.listTools();
        List<McpSchema.Tool> tools = listToolsResult.tools();
        System.out.println("获取到的tools:" + tools.stream().map(McpSchema.Tool::name).collect(Collectors.joining(",")));
        for (McpSchema.Tool tool : tools) {
            McpSchema.JsonSchema jsonSchema = tool.inputSchema();
            System.out.println(jsonSchema);
            McpSchema.CallToolResult callToolResult = client.callTool(new McpSchema.CallToolRequest(tool.name(), Map.of("orderNo", "25070601")));

            System.out.println("获取到的结果为：==========");
            callToolResult.content().forEach(System.out::println);
        }


    }
}

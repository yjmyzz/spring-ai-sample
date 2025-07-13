# Spring AI MCP Server (WebMVC 实现) 示例项目

本项目是一个基于 [Spring AI](https://spring.io/projects/spring-ai) 框架的 MCP (Model Context Protocol) Server，采用 WebMVC 实现，演示如何通过 Spring AI 快速构建支持 MCP 协议的 AI 工具服务。

## 🚀 项目特性

- **MCP Server（WebMVC实现）**：支持 Model Context Protocol，基于 WebMVC 实现同步响应
- **Spring AI 集成**：利用 spring-ai 框架，简化 AI 工具开发
- **订单查询工具**：内置订单状态查询示例
- **RESTful API**：提供基础 Web API
- **MCP 客户端示例**：演示如何通过 MCP 协议调用服务
- **认证拦截器**：支持 Bearer Token 认证机制

## 🛠️ 技术栈

- Java 21
- Spring Boot 3.4.5
- Spring AI 1.1.0-SNAPSHOT
- Maven
- MCP (Model Context Protocol)
- WebMVC

## 📋 系统要求

- JDK 21 及以上
- Maven 3.6 及以上

## 🚀 快速开始

1. 克隆项目

```bash
git clone <repository-url>
cd spring-ai-sample
```

2. 构建并运行

```bash
mvn clean package
mvn spring-boot:run
```

应用默认启动在 `http://localhost:8080`

3. 测试 API

```bash
curl http://localhost:8080/api/hello
```

预期返回：`Hello, Spring AI!`

## 📁 项目结构

```
spring-ai-sample/
├── src/main/java/com/cnblogs/yjmyzz/
│   ├── SpringAiApplication.java          # 主启动类
│   ├── WebConfig.java                    # Web 配置类
│   ├── Interceptor.java                  # 认证拦截器
│   ├── controller/
│   │   └── WebController.java           # Web API 控制器
│   └── mcp/
│       ├── server/
│       │   └── OrderService.java        # MCP 工具服务（订单查询）
│       └── client/
│           └── McpClientSample.java     # MCP 客户端示例
├── src/main/resources/
│   └── application.yaml                 # 配置文件
├── pom.xml                              # Maven 配置
└── README.md                            # 项目说明
```

## 🔧 核心功能

### 1. MCP Server（WebMVC 实现）
- 支持基于 WebMVC 的同步响应
- 通过 Spring AI 框架注册自定义工具
- 支持工具、资源、提示和完成功能

### 2. 订单查询工具

`OrderService` 示例：
```java
@Tool(name = "queryOrderStatus", description = "根据订单号查询订单状态")
public String queryOrderStatus(@ToolParam(required = true, description = "订单号,格式为8位数字,比如：25070601") String orderNo) {
    // ... 订单状态逻辑 ...
}
```

支持的订单号：
- `25070601`：已发货
- `25070602`：已完成  
- `25070603`：已取消
- 其他：未知状态

### 3. MCP 客户端示例

`McpClientSample` 演示如何通过 MCP 协议调用服务端工具并获取结果，包括：
- 初始化连接
- Ping 测试
- 获取工具列表
- 调用订单查询工具

### 4. Web API
- `/api/hello`：健康检查接口

### 5. 认证机制
- 支持 Bearer Token 认证
- 有效 Token：`123456`、`234567`
- 受保护的端点：`/sse`、`/mcp/messages`

## ⚙️ 配置说明

实际配置文件 `src/main/resources/application.yaml`：

```yaml
spring:
  ai:
    mcp:
      server:
        name: webmvc-mcp-server
        version: 1.0.0
        type: SYNC
        instructions: "This server provides order status query tools"
        sse-message-endpoint: /mcp/messages
        capabilities:
          tool: true
          resource: true
          prompt: true
          completion: true

logging:
  level:
    root: INFO
  file:
    name: logs/mcp_demo.log
```

## 🔐 认证使用

### 调用受保护的 MCP 端点

```bash
# 使用有效 Token 调用
curl -H "Authorization: Bearer 123456" http://localhost:8080/mcp/messages

# 使用无效 Token 会被拒绝
curl -H "Authorization: Bearer invalid" http://localhost:8080/mcp/messages
```

### MCP 客户端认证

**注意：** 目前 `HttpClientSseClientTransport` 不支持直接设置 Authorization 请求头。认证需要通过以下方式实现：

1. **通过 MCP Host 进行认证**：在实际使用中，认证通常由 MCP Host（如 Claude Desktop、Cursor 等）处理
2. **临时禁用认证**：在开发测试阶段，可以临时移除拦截器配置
3. **使用其他 HTTP 客户端**：如果需要直接调用，可以使用标准的 HTTP 客户端库

当前示例代码中的注释说明了这个问题：

```java
//注：目前spring-ai的源码，并非提供设置http请求头的方式，下面的代码，无法设置Authorization请求头，请求将超时失败
HttpClientSseClientTransport webFluxSseTransport = HttpClientSseClientTransport
        .builder("http://localhost:8080")
        .build();
```

## 📝 开发说明

### 添加自定义 MCP 工具
1. 在 `mcp.server` 包下新建服务类
2. 用 `@Service` 注解标记类
3. 用 `@Tool` 注解标记方法
4. 用 `@ToolParam` 注解定义参数
5. 在主类注册工具（已通过 `ToolCallbackProvider` 自动注册）

### 扩展 Web API
- 在 `controller` 包下添加或扩展控制器类

### 修改认证逻辑
- 编辑 `Interceptor.java` 中的 `preHandle` 方法
- 在 `WebConfig.java` 中配置拦截器路径

## 🚨 注意事项

- 项目使用 Spring AI 1.1.0-SNAPSHOT 版本，需要配置 snapshot 仓库
- MCP 功能目前仅在 snapshot 版本中正常工作
- 认证拦截器会拦截 `/sse` 和 `/mcp/messages` 端点
- **认证限制**：`HttpClientSseClientTransport` 不支持直接设置 Authorization 请求头，需要通过 MCP Host 进行认证或临时禁用认证进行测试

## 👨‍💻 作者

- **菩提树下的杨过** - [博客园主页](https://www.cnblogs.com/yjmyzz)

## 🙏 致谢

- [Spring AI](https://spring.io/projects/spring-ai)
- [MCP](https://modelcontextprotocol.io/)

---
如有问题或建议，欢迎提交 Issue 或联系作者。 
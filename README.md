# Spring AI MCP Server (SSE 实现) 示例项目

本项目是一个基于 [Spring AI](https://spring.io/projects/spring-ai) 框架的 MCP (Model Context Protocol) Server，采用 SSE（Server-Sent Events）实现，演示如何通过 Spring AI 快速构建支持 MCP 协议的 AI 工具服务。

## 🚀 项目特性

- **MCP Server（SSE实现）**：支持 Model Context Protocol，基于 SSE 实现流式响应
- **Spring AI 集成**：利用 spring-ai 框架，简化 AI 工具开发
- **订单查询工具**：内置订单状态查询示例
- **RESTful API**：提供基础 Web API
- **MCP 客户端示例**：演示如何通过 MCP 协议调用服务

## 🛠️ 技术栈

- Java 21
- Spring Boot 3.4.4
- Spring AI 1.1.0
- Maven
- MCP (Model Context Protocol)
- SSE (Server-Sent Events)

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

### 1. MCP Server（SSE 实现）
- 支持基于 SSE 的流式响应，适合 AI 场景下的实时推送
- 通过 Spring AI 框架注册自定义工具

### 2. 订单查询工具

`OrderService` 示例：
```java
@Tool(name = "queryOrderStatus", description = "根据订单号查询订单状态")
public String queryOrderStatus(@ToolParam(required = true, description = "订单号,格式为8位数字") String orderNo) {
    // ... 订单状态逻辑 ...
}
```

### 3. MCP 客户端示例

`McpClientSample` 演示如何通过 MCP 协议调用服务端工具并获取结果。

### 4. Web API
- `/api/hello`：健康检查接口

## ⚙️ 配置说明

实际配置文件 `src/main/resources/application.yaml`：

```yaml
spring:
  ai:
    mcp:
      server:
        type: async

logging:
  level:
    root: INFO
  file:
    name: logs/mcp_demo.log
```

## 📝 开发说明

### 添加自定义 MCP 工具
1. 在 `mcp.server` 包下新建服务类
2. 用 `@Tool` 注解标记方法
3. 用 `@ToolParam` 注解定义参数
4. 在主类注册工具

### 扩展 Web API
- 在 `controller` 包下添加或扩展控制器类

## 👨‍💻 作者

- **菩提树下的杨过** - [博客园主页](https://www.cnblogs.com/yjmyzz)

## 🙏 致谢

- [Spring AI](https://spring.io/projects/spring-ai)
- [MCP](https://modelcontextprotocol.io/)

---
如有问题或建议，欢迎提交 Issue 或联系作者。 
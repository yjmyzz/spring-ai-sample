# Spring AI 示例项目

这是一个基于 Spring AI 框架的示例项目，演示了如何使用 Spring AI 的 MCP (Model Context Protocol) 功能来构建 AI 工具服务。

## 🚀 项目特性

- **Spring AI MCP Server**: 实现了基于 MCP 协议的 AI 工具服务
- **自定义工具**: 提供了获取博客园博主网址的自定义工具
- **RESTful API**: 提供简单的 Web API 接口
- **MCP 客户端示例**: 包含完整的 MCP 客户端使用示例
- **Spring Boot 3.4.4**: 基于最新的 Spring Boot 版本

## 🛠️ 技术栈

- **Java 21**
- **Spring Boot 3.4.4**
- **Spring AI 1.1.0**
- **Maven**
- **MCP (Model Context Protocol)**

## 📋 系统要求

- JDK 21 或更高版本
- Maven 3.6 或更高版本

## 🚀 快速开始

### 1. 克隆项目

```bash
git clone <repository-url>
cd spring-ai-sample
```

### 2. 构建项目

```bash
mvn clean compile
```

### 3. 运行应用

```bash
mvn spring-boot:run
```

应用将在 `http://localhost:8080` 启动。

### 4. 测试 API

访问测试接口：
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
│       │   └── AuthorService.java       # MCP 服务端工具实现
│       └── client/
│           └── McpClientSample.java     # MCP 客户端示例
├── src/main/resources/
│   └── application.yaml                 # 应用配置文件
├── pom.xml                              # Maven 配置文件
└── README.md                            # 项目说明文档
```

## 🔧 核心功能

### 1. MCP 服务端工具

`AuthorService` 类实现了一个自定义的 MCP 工具：

```java
@Tool(name = "getCnBlogsUrlByName",
      description = "获取cnblogs某博主的博客网址")
public String getCnBlogsUrlByName(@ToolParam(required = true, description = "博主名称") String bloggerName) {
    return "https://www.cnblogs.com/yjmyzz";
}
```

### 2. MCP 客户端示例

`McpClientSample` 类演示了如何：
- 连接到 MCP 服务器
- 列出可用工具
- 调用工具并获取结果

### 3. Web API

提供简单的 REST API 接口用于测试应用状态。

## ⚙️ 配置说明

### 应用配置 (application.yaml)

```yaml
server:
  port: 8080                    # 服务端口

spring:
  main:
    banner-mode: off            # 关闭启动横幅

logging:
  pattern:
    console:                    # 控制台日志格式
  level:
    root: TRACE                # 日志级别
  file:
    name: logs/mcp_demo.log    # 日志文件路径
```

## 🧪 运行测试

### 运行 MCP 客户端测试

```bash
mvn test -Dtest=McpClientSample#testMcpClientSample
```

### 构建 JAR 包

```bash
mvn clean package
```

生成的 JAR 文件位于 `target/spring-ai-0.0.1-SNAPSHOT.jar`

## 📝 开发说明

### 添加新的 MCP 工具

1. 在 `mcp.server` 包下创建新的服务类
2. 使用 `@Tool` 注解标记工具方法
3. 使用 `@ToolParam` 注解定义参数
4. 在 `SpringAiApplication` 中注册工具

示例：
```java
@Service
public class MyService {
    @Tool(name = "myTool", description = "工具描述")
    public String myTool(@ToolParam(required = true, description = "参数描述") String param) {
        // 工具实现逻辑
        return "结果";
    }
}
```

### 扩展 Web API

在 `controller` 包下添加新的控制器类或扩展现有的 `WebController`。

## 🔍 日志查看

应用运行时会生成日志文件：
- 控制台输出：实时查看应用状态
- 文件日志：`logs/mcp_demo.log`

## 🤝 贡献指南

1. Fork 项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 👨‍💻 作者

- **菩提树下的杨过** - [博客园主页](https://www.cnblogs.com/yjmyzz)

## 🙏 致谢

- [Spring AI](https://spring.io/projects/spring-ai) - Spring 官方 AI 框架
- [MCP](https://modelcontextprotocol.io/) - Model Context Protocol 规范

---

如有问题或建议，请提交 Issue 或联系作者。 
# Spring AI 示例项目

这是一个基于 Spring AI 框架的示例项目，集成了 Ollama 本地大语言模型，提供了聊天对话功能。

## 🚀 项目特性

- **Spring AI 集成**: 使用 Spring AI 框架简化 AI 应用开发
- **Ollama 支持**: 集成 Ollama 本地大语言模型服务
- **RESTful API**: 提供聊天对话的 REST API 接口
- **流式响应**: 支持流式聊天响应
- **自定义 Advisor**: 实现了请求响应拦截器，用于日志输出
- **中文 AI 助手**: 配置了中文 AI 助手"阿呆"

## 🛠️ 技术栈

- **Java 21**: 使用最新的 Java LTS 版本
- **Spring Boot 3.4.4**: 现代化的 Spring 框架
- **Spring AI 1.1.0**: Spring 官方 AI 框架
- **Ollama**: 本地大语言模型服务
- **Maven**: 项目构建工具

## 📋 系统要求

- Java 21 或更高版本
- Maven 3.6+
- Ollama 服务 (需要预先安装并运行)

## 🔧 安装和运行

### 1. 安装 Ollama

首先需要安装并启动 Ollama 服务：

```bash
# 下载并安装 Ollama (根据你的操作系统)
# Windows: https://ollama.ai/download
# macOS: brew install ollama
# Linux: curl -fsSL https://ollama.ai/install.sh | sh

# 启动 Ollama 服务
ollama serve

# 下载模型 (在另一个终端中)
ollama pull deepseek-r1:1.5b
```

### 2. 克隆项目

```bash
git clone <repository-url>
cd spring-ai-sample
```

### 3. 配置应用

编辑 `src/main/resources/application.yaml` 文件，确保 Ollama 服务地址正确：

```yaml
spring:
  ai:
    ollama:
      base-url: http://localhost:11434  # Ollama 服务地址
      chat:
        model: deepseek-r1:1.5b         # 使用的模型名称
```

### 4. 运行应用

```bash
# 使用 Maven 运行
mvn spring-boot:run

# 或者先编译再运行
mvn clean package
java -jar target/spring-ai-0.0.1-SNAPSHOT.jar
```

应用启动后，将在 `http://localhost:8080` 提供服务。

## 📚 API 接口文档

### 1. 健康检查

```http
GET /api/hello
```

**响应示例:**
```json
"Hello, Spring AI!"
```

### 2. 聊天对话

```http
GET /api/chat?prompt=你好，请介绍一下自己
```

**参数:**
- `prompt` (必需): 用户输入的对话内容

**响应示例:**
```json
"你好！我是阿呆，一个可爱且热情的AI助手。我的英文名字叫Mike，很高兴认识你！"
```

### 3. 流式聊天

```http
GET /api/chat-stream?prompt=请写一首关于春天的诗
```

**参数:**
- `prompt` (必需): 用户输入的对话内容

**响应:** 返回流式文本响应，实时显示 AI 回复内容

## 🏗️ 项目结构

```
spring-ai-sample/
├── src/main/java/com/cnblogs/yjmyzz/
│   ├── SpringAiApplication.java          # 主启动类
│   ├── controller/
│   │   └── WebController.java           # REST API 控制器
│   ├── config/
│   │   └── CommonConfiguration.java     # 应用配置
│   └── advisor/
│       └── ConsoleOutputAdvisor.java    # 请求响应拦截器
├── src/main/resources/
│   └── application.yaml                 # 应用配置文件
└── pom.xml                             # Maven 配置文件
```

## 🔍 核心组件说明

### ChatClient 配置

在 `CommonConfiguration.java` 中配置了 ChatClient：

- **默认系统提示**: 设置了 AI 助手的身份和性格
- **自定义 Advisor**: 集成了 `ConsoleOutputAdvisor` 用于日志输出

### ConsoleOutputAdvisor

实现了 `RequestResponseAdvisor` 接口，用于：

- 记录用户请求内容
- 处理 AI 响应输出
- 过滤和格式化响应内容

### WebController

提供了三个主要的 API 端点：

- `/api/hello`: 简单的健康检查
- `/api/chat`: 普通聊天对话
- `/api/chat-stream`: 流式聊天对话

## 🚀 开发指南

### 添加新的 AI 模型

1. 在 `application.yaml` 中修改模型配置
2. 确保 Ollama 中已下载对应模型

### 自定义 Advisor

创建新的 Advisor 类实现 `RequestResponseAdvisor` 接口：

```java
@Component
public class CustomAdvisor implements RequestResponseAdvisor {
    // 实现请求和响应处理逻辑
}
```

### 扩展 API 功能

在 `WebController` 中添加新的端点，或创建新的控制器类。

## 🐛 故障排除

### 常见问题

1. **Ollama 连接失败**
   - 确保 Ollama 服务正在运行
   - 检查 `application.yaml` 中的 `base-url` 配置
   - 验证端口 11434 是否可访问

2. **模型未找到**
   - 使用 `ollama list` 查看已安装的模型
   - 使用 `ollama pull <model-name>` 下载所需模型

3. **Java 版本问题**
   - 确保使用 Java 21 或更高版本
   - 检查 `JAVA_HOME` 环境变量设置

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 🤝 贡献

欢迎提交 Issue 和 Pull Request 来改进这个项目！

## 📞 联系方式

如有问题或建议，请通过以下方式联系：

- 项目 Issues: [GitHub Issues](https://github.com/your-repo/issues)
- 邮箱: your-email@example.com

---

**注意**: 本项目仅供学习和演示使用，请确保遵守相关法律法规和 AI 模型的使用条款。 
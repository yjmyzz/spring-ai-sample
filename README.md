# Spring AI 示例项目

这是一个基于 Spring AI 框架的示例项目，集成了 Ollama 本地大语言模型，提供了聊天对话功能，支持多轮对话记忆。

## 🚀 项目特性

- **Spring AI 集成**: 使用 Spring AI 框架简化 AI 应用开发
- **Ollama 支持**: 集成 Ollama 本地大语言模型服务
- **RESTful API**: 提供聊天对话的 REST API 接口
- **流式响应**: 支持流式聊天响应
- **多轮对话记忆**: 支持基于 conversationId 的多轮对话上下文记忆
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

### 2. 单轮聊天对话

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

### 4. 多轮对话记忆

```http
GET /api/conversation?conversationId=sid1&prompt=我的名字是张三
```

**参数:**
- `conversationId` (必需): 会话标识符，用于维护对话上下文
- `prompt` (必需): 用户输入的对话内容

**特点:**
- 支持多轮对话记忆，AI 能记住之前的对话内容
- 同一 conversationId 的对话会保持上下文连续性
- 不同 conversationId 的对话相互独立

**使用示例:**
```bash
# 第一轮对话
curl "http://localhost:8080/api/conversation?conversationId=sid1&prompt=我的名字是张三"

# 第二轮对话（AI 会记住你的名字）
curl "http://localhost:8080/api/conversation?conversationId=sid1&prompt=你还记得我的名字吗？"

# 第三轮对话（AI 会记住整个对话历史）
curl "http://localhost:8080/api/conversation?conversationId=sid1&prompt=我们之前聊了什么？"
```

### 5. 流式多轮对话

```http
GET /api/conversation-stream?conversationId=sid1&prompt=请写一个故事
```

**参数:**
- `conversationId` (必需): 会话标识符
- `prompt` (必需): 用户输入的对话内容

**响应:** 返回流式文本响应，支持多轮对话记忆

### 6. 查询对话历史

```http
GET /api/history?conversationId=sid1
```

**参数:**
- `conversationId` (必需): 会话标识符

**响应:** 返回指定会话的历史消息列表

### 7. 清除对话历史

```http
DELETE /api/history?conversationId=sid1
```

**参数:**
- `conversationId` (必需): 会话标识符

**响应:** 清除指定会话的所有历史记录

## 🏗️ 项目结构

```
spring-ai-sample/
├── src/main/java/com/cnblogs/yjmyzz/
│   ├── SpringAiApplication.java          # 主启动类
│   ├── controller/
│   │   └── WebController.java           # REST API 控制器
│   ├── config/
│   │   └── CommonConfiguration.java     # 应用配置
│   ├── advisor/
│   │   └── ConsoleOutputAdvisor.java    # 请求响应拦截器
│   └── consts/
│       └── AppConstant.java             # 应用常量定义
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

提供了多个 API 端点：

- `/api/hello`: 简单的健康检查
- `/api/chat`: 单轮聊天对话
- `/api/chat-stream`: 流式聊天对话
- `/api/conversation`: 多轮对话记忆
- `/api/conversation-stream`: 流式多轮对话
- `/api/history`: 查询对话历史
- `/api/history` (DELETE): 清除对话历史

### 多轮对话记忆实现

项目使用 Spring AI 的 ChatMemory 功能实现多轮对话：

- **InMemoryChatMemoryRepository**: 内存存储对话历史
- **MessageWindowChatMemory**: 消息窗口管理，限制历史消息数量
- **conversationId**: 会话标识符，用于区分不同对话

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

### 配置对话记忆参数

在 `AppConstant.java` 中可以调整：

```java
public static final int MAX_HISTORY_SESSION = 20; // 最大历史消息数量
```

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

4. **多轮对话记忆失效**
   - 确保每次请求使用相同的 conversationId
   - 检查 MAX_HISTORY_SESSION 设置是否合理
   - 验证 ChatMemory 配置是否正确

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
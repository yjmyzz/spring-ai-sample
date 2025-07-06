# Spring AI 示例项目

一个基于 Spring AI 框架的智能聊天应用示例，支持 Ollama 本地模型和 OpenAI 远程模型的集成。

## 🚀 项目特性

- **双模型支持**: 同时集成 Ollama 本地模型和 OpenAI 远程模型
- **流式响应**: 支持实时流式聊天响应
- **自定义助手**: 配置个性化的AI助手角色和系统提示
- **请求响应监控**: 通过 Advisor 模式监控和记录所有对话
- **RESTful API**: 提供简洁的 REST API 接口
- **Spring Boot 3.4.4**: 基于最新的 Spring Boot 版本

## 🛠️ 技术栈

- **Java 21**: 使用最新的 LTS 版本
- **Spring Boot 3.4.4**: 主框架
- **Spring AI 1.1.0**: AI 集成框架
- **Ollama**: 本地大语言模型服务
- **OpenAI API**: 远程AI服务
- **Maven**: 项目构建工具

## 📋 系统要求

- JDK 21 或更高版本
- Maven 3.6+ 
- Ollama 服务 (可选，用于本地模型)
- OpenAI API Key (可选，用于远程模型)

## 🚀 快速开始

### 1. 克隆项目

```bash
git clone <repository-url>
cd spring-ai-sample
```

### 2. 配置环境

#### 配置 Ollama (可选)

1. 安装并启动 Ollama 服务
2. 下载所需模型：
```bash
ollama pull deepseek-r1:1.5b
```

#### 配置 OpenAI (可选)

1. 获取 OpenAI API Key
2. 修改 `src/main/resources/application.yaml` 中的配置：

```yaml
spring:
  ai:
    openai:
      api-key: your-openai-api-key
      base-url: https://api.openai.com  # 或使用其他兼容的API端点
```

### 3. 运行项目

```bash
# 编译项目
mvn clean compile

# 运行应用
mvn spring-boot:run
```

应用将在 `http://localhost:8080` 启动。

## 📚 API 接口文档

### 基础接口

#### 1. 健康检查
```http
GET /api/hello
```
**响应**: `Hello, Spring AI!`

### Ollama 模型接口

#### 2. 普通聊天
```http
GET /api/ollama/chat?prompt=你好
```
**参数**:
- `prompt` (必需): 用户输入的问题

**响应**: AI 助手的回复文本

#### 3. 流式聊天
```http
GET /api/ollama/chat-stream?prompt=请介绍一下Spring AI
```
**参数**:
- `prompt` (必需): 用户输入的问题

**响应**: 实时流式文本响应

### OpenAI 模型接口

#### 4. 普通聊天
```http
GET /api/openai/chat?prompt=你好
```
**参数**:
- `prompt` (必需): 用户输入的问题

**响应**: AI 助手的回复文本

#### 5. 流式聊天
```http
GET /api/openai/chat-stream?prompt=请介绍一下Spring AI
```
**参数**:
- `prompt` (必需): 用户输入的问题

**响应**: 实时流式文本响应

## ⚙️ 配置说明

### 应用配置 (`application.yaml`)

```yaml
server:
  port: 8080

spring:
  ai:
    ollama:
      base-url: http://localhost:11434  # Ollama 服务地址
      chat:
        model: deepseek-r1:1.5b         # 使用的模型名称
    
    openai:
      api-key: your-api-key             # OpenAI API Key
      base-url: https://api.openai.com  # API 端点
      chat:
        options:
          model: gpt-3.5-turbo          # 使用的模型
          temperature: 0.7              # 温度参数
```

### AI 助手配置

项目配置了两个不同的 AI 助手：

1. **Ollama 助手 (阿呆/Mike)**: 使用本地模型，适合隐私敏感场景
2. **OpenAI 助手 (小美/Rose)**: 使用远程模型，功能更强大

## 🔧 项目结构

```
spring-ai-sample/
├── src/main/java/com/cnblogs/yjmyzz/
│   ├── SpringAiApplication.java          # 主启动类
│   ├── config/
│   │   └── CommonConfiguration.java      # AI 客户端配置
│   ├── controller/
│   │   └── WebController.java            # REST API 控制器
│   └── advisor/
│       └── ConsoleOutputAdvisor.java     # 请求响应监控
├── src/main/resources/
│   └── application.yaml                  # 应用配置文件
├── pom.xml                               # Maven 配置
└── README.md                             # 项目文档
```

## 🔍 核心组件

### 1. ConsoleOutputAdvisor
监控所有 AI 对话的请求和响应，在控制台输出完整的对话记录。

### 2. CommonConfiguration
配置两个独立的 ChatClient：
- `ollamaClient`: 连接本地 Ollama 服务
- `openaiClient`: 连接 OpenAI API

### 3. WebController
提供 RESTful API 接口，支持普通对话和流式对话。

## 🧪 测试示例

### 使用 curl 测试

```bash
# 测试 Ollama 聊天
curl "http://localhost:8080/api/ollama/chat?prompt=你好，请介绍一下自己"

# 测试 OpenAI 聊天
curl "http://localhost:8080/api/openai/chat?prompt=什么是Spring AI？"

# 测试流式响应
curl "http://localhost:8080/api/ollama/chat-stream?prompt=请写一首诗"
```

### 使用浏览器测试

直接在浏览器中访问：
- `http://localhost:8080/api/hello`
- `http://localhost:8080/api/ollama/chat?prompt=你好`
- `http://localhost:8080/api/openai/chat?prompt=你好`

## 🐛 故障排除

### 常见问题

1. **Ollama 连接失败**
   - 确保 Ollama 服务正在运行
   - 检查端口 11434 是否可访问
   - 确认模型已正确下载

2. **OpenAI API 错误**
   - 验证 API Key 是否正确
   - 检查网络连接
   - 确认 API 配额是否充足

3. **端口冲突**
   - 修改 `application.yaml` 中的 `server.port` 配置

## 📝 开发说明

### 添加新的 AI 模型

1. 在 `pom.xml` 中添加相应的 Spring AI 依赖
2. 在 `CommonConfiguration` 中配置新的 ChatClient
3. 在 `WebController` 中添加对应的 API 接口

### 自定义系统提示

修改 `CommonConfiguration.java` 中的 `defaultSystem` 配置：

```java
.defaultSystem("你的自定义系统提示")
```

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

## 📞 联系方式

如有问题或建议，请通过以下方式联系：
- 提交 GitHub Issue
- 发送邮件至项目维护者

---

**注意**: 使用 OpenAI API 需要有效的 API Key，请确保遵守 OpenAI 的使用条款和隐私政策。 
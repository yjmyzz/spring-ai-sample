# Spring AI 智谱 AI 图像生成示例项目

这是一个基于 Spring AI 框架的示例项目，演示了如何使用智谱 AI (ZhiPuAI) 的图像生成功能来构建 AI 图像服务。

## 🚀 项目特性

- **智谱 AI 图像生成**: 集成了智谱 AI 的图像生成模型
- **RESTful API**: 提供图像生成的 Web API 接口
- **多种输出格式**: 支持获取图片 URL 和直接在浏览器中显示图片
- **Spring Boot 3.4.4**: 基于最新的 Spring Boot 版本
- **Java 21**: 使用最新的 Java 特性

## 🛠️ 技术栈

- **Java 21**
- **Spring Boot 3.4.4**
- **Spring AI 1.1.0**
- **智谱 AI (ZhiPuAI)**
- **Maven**

## 📋 系统要求

- JDK 21 或更高版本
- Maven 3.6 或更高版本
- 智谱 AI API Key

## 🚀 快速开始

### 1. 克隆项目

```bash
git clone <repository-url>
cd spring-ai-sample
```

### 2. 配置 API Key

在 `src/main/resources/application.yaml` 中配置您的智谱 AI API Key：

```yaml
spring:
  ai:
    zhipuai:
      api-key: your_zhipuai_api_key_here
```

或者设置环境变量：
```bash
export zhipuai_api_key=your_zhipuai_api_key_here
```

### 3. 构建项目

```bash
mvn clean compile
```

### 4. 运行应用

```bash
mvn spring-boot:run
```

应用将在 `http://localhost:8080` 启动。

### 5. 测试 API

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
│   └── controller/
│       └── WebController.java           # Web API 控制器
├── src/main/resources/
│   └── application.yaml                 # 应用配置文件
├── pom.xml                              # Maven 配置文件
└── README.md                            # 项目说明文档
```

## 🔧 核心功能

### 1. 图像生成 API

项目提供了三个主要的图像生成接口：

#### 获取图片 URL
```http
GET /api/image/url?prompt=一只可爱的小猫
```

返回智谱 AI 生成的图片 URL，格式为 JSON 字符串。

#### 在浏览器中显示图片
```http
GET /api/image/display?prompt=一只可爱的小猫
```

直接在浏览器中显示生成的图片，适合在网页中嵌入。

#### 应用状态检查
```http
GET /api/hello
```

返回应用状态信息。

### 2. 图像生成配置

默认的图像生成参数：
- **尺寸**: 256x256 像素
- **格式**: PNG
- **模型**: 智谱 AI 图像生成模型

## ⚙️ 配置说明

### 应用配置 (application.yaml)

```yaml
server:
  port: 8080                    # 服务端口

spring:
  ai:
    zhipuai:
      api-key: ${zhipuai_api_key}  # 智谱 AI API Key

logging:
  level:
    root: DEBUG                # 日志级别
  file:
    name: logs/mcp_demo.log    # 日志文件路径
```

### Maven 依赖

主要依赖包括：
- `spring-ai-starter-model-zhipuai` - Spring AI 智谱 AI 启动器
- `spring-boot-starter-web` - Spring Boot Web 启动器
- `spring-boot-starter-test` - 测试依赖

## 🧪 使用示例

### 使用 curl 测试

1. **获取图片 URL**：
```bash
curl "http://localhost:8080/api/image/url?prompt=一只可爱的小猫"
```

2. **在浏览器中查看图片**：
```bash
curl "http://localhost:8080/api/image/display?prompt=一只可爱的小猫" --output image.png
```

3. **直接在浏览器中打开**：
在浏览器中访问：
```
http://localhost:8080/api/image/display?prompt=一只可爱的小猫
```

### 使用 JavaScript 调用

```javascript
// 获取图片 URL
fetch('/api/image/url?prompt=一只可爱的小猫')
  .then(response => response.text())
  .then(url => {
    console.log('图片 URL:', url);
    // 在页面中显示图片
    document.getElementById('image').src = url;
  });

// 直接显示图片
document.getElementById('image').src = '/api/image/display?prompt=一只可爱的小猫';
```

## 📝 开发说明

### 扩展图像生成功能

在 `WebController` 中可以轻松扩展更多功能：

1. **添加更多图像尺寸选项**：
```java
@GetMapping("/image/custom")
public ResponseEntity<ByteArrayResource> generateCustomImage(
    @RequestParam String prompt,
    @RequestParam(defaultValue = "256") int width,
    @RequestParam(defaultValue = "256") int height) {
    // 实现自定义尺寸的图像生成
}
```

2. **添加图像格式选择**：
```java
@GetMapping("/image/format")
public ResponseEntity<ByteArrayResource> generateImageWithFormat(
    @RequestParam String prompt,
    @RequestParam(defaultValue = "PNG") String format) {
    // 实现不同格式的图像生成
}
```

### 错误处理

项目包含基本的错误处理：
- 网络连接错误
- API 调用失败
- 参数验证错误

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
- [智谱 AI](https://open.bigmodel.cn/) - 智谱 AI 开放平台

## ⚠️ 注意事项

1. **API Key 安全**: 请妥善保管您的智谱 AI API Key，不要将其提交到版本控制系统
2. **使用限制**: 请遵守智谱 AI 的使用条款和限制
3. **网络连接**: 确保应用能够访问智谱 AI 的 API 服务

---

如有问题或建议，请提交 Issue 或联系作者。 
# Health 接口规格文档

## 1. 核心目标

- 开发一个健康检查接口，返回项目名称、版本号、当前服务器时间及运行状态，用于监控和运维确认服务是否正常运行。

## 2. 业务规则

- 接口路径：GET /api/health
- 无需请求参数
- 返回 JSON，包含 code、msg、data 三个字段
- code 固定为 200，表示成功
- msg 固定为 "success"
- data 为包含以下字段的对象：
  - name：应用名称，取自 `spring.application.name`
  - version：应用版本号，取自 `app.version`
  - env：当前激活的环境标识（dev / test / prod）
  - status：固定为 "UP"，表示服务运行正常
  - serverTime：当前服务器时间，格式为 ISO 8601（如 `2026-03-05T12:33:04`）

## 3. 技术约束

- 使用 Spring Boot 3.x
- 使用 Java 17
- 支持多环境配置（dev / test / prod），通过 `spring.profiles.active` 切换
- 返回类型使用统一包装类 `ResultVO<Map<String, Object>>`

## 4. 输入输出

### 4.1 输入

- 无请求体，无查询参数

### 4.2 输出

- 成功示例：

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "name": "springboot-hello",
    "version": "0.0.1-SNAPSHOT",
    "env": "dev",
    "status": "UP",
    "serverTime": "2026-03-05T12:33:04"
  }
}
```

## 5. 验收标准

- 项目能成功启动
- 访问 /api/health 返回 HTTP 状态码 200
- 返回 JSON 结构中包含 code、msg、data 三个字段
- data.status 值为 "UP"
- data.serverTime 为非空字符串且格式符合 ISO 8601
- data.name 和 data.version 与配置文件一致

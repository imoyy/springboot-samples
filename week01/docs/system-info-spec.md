# System Info 接口规格文档

## 1. 核心目标

- 开发一系列系统信息接口，返回应用信息、安全环境变量及 JVM 运行指标，用于开发调试和运维监控。

## 2. 业务规则

### 2.1 GET /api/system/info — 应用信息

- 无需请求参数
- data 为包含以下字段的对象：
  - name：应用名称
  - version：应用版本号
  - env：当前激活的环境标识
  - description：环境描述，取自 `app.description`
  - javaVersion：JVM 版本，取自 `java.version` 系统属性
  - javaVendor：JVM 供应商，取自 `java.vendor` 系统属性
  - springBootVersion：Spring Boot 版本号（由 jar 包 Manifest 读取，开发期可能为 null）

### 2.2 GET /api/system/env — 安全环境变量

- 无需请求参数
- 只暴露以下安全字段，不得泄露数据库密码、密钥等敏感信息：
  - `os.name`、`os.version`、`os.arch`
  - `java.version`、`java.home`
  - `user.timezone`、`file.encoding`
  - `JAVA_HOME`（操作系统环境变量，若存在）

### 2.3 GET /api/system/metrics — JVM 指标

- 无需请求参数
- data 为包含以下字段的对象：
  - heap.used / heap.committed / heap.max：堆内存使用情况（单位：字节）
  - nonHeap.used / nonHeap.committed：非堆内存使用情况（单位：字节）
  - threadCount：当前活跃线程数
  - peakThreadCount：历史峰值线程数
  - availableProcessors：可用 CPU 核数

所有接口均返回 JSON，包含 code、msg、data 三个字段，code 固定为 200，msg 固定为 "success"。

## 3. 技术约束

- 使用 Spring Boot 3.x
- 使用 Java 17
- 支持多环境配置（dev / test / prod）
- 返回类型使用统一包装类 `ResultVO<Map<String, Object>>`
- 使用 `java.lang.management.ManagementFactory` 获取 JVM 指标

## 4. 输入输出

### 4.1 输入

- 无请求体，无查询参数

### 4.2 输出

- GET /api/system/info 成功示例：

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "name": "springboot-hello",
    "version": "0.0.1-SNAPSHOT",
    "env": "dev",
    "description": "开发环境",
    "javaVersion": "17.0.10",
    "javaVendor": "Eclipse Adoptium",
    "springBootVersion": null
  }
}
```

- GET /api/system/env 成功示例：

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "os.name": "Linux",
    "os.version": "5.15.0",
    "os.arch": "amd64",
    "java.version": "17.0.10",
    "java.home": "/usr/lib/jvm/java-17",
    "user.timezone": "Asia/Shanghai",
    "file.encoding": "UTF-8"
  }
}
```

- GET /api/system/metrics 成功示例：

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "heap": {
      "used": 52428800,
      "committed": 104857600,
      "max": 2147483648
    },
    "nonHeap": {
      "used": 41943040,
      "committed": 45088768
    },
    "threadCount": 18,
    "peakThreadCount": 21,
    "availableProcessors": 4
  }
}
```

## 5. 验收标准

- 访问 /api/system/info 返回 HTTP 状态码 200，data 包含 name、version、javaVersion 字段
- 访问 /api/system/env 返回 HTTP 状态码 200，data 中不包含任何密码或密钥字段
- 访问 /api/system/metrics 返回 HTTP 状态码 200，data.heap.used 为正整数，data.threadCount 为正整数
- 访问不存在的路径返回统一错误格式（code: 404）

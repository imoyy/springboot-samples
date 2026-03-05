package com.hxc.springboothello.controller;

import com.hxc.springboothello.model.ResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
public class SystemController {

  @Value("${spring.application.name}")
  private String appName;

  @Value("${app.version}")
  private String appVersion;

  @Value("${app.env}")
  private String appEnv;

  @Value("${app.description}")
  private String appDescription;

  /**
   * GET /api/system/info — 应用信息
   */
  @GetMapping("/info")
  public ResultVO<Map<String, Object>> info() {
    Map<String, Object> data = new LinkedHashMap<>();
    data.put("name", appName);
    data.put("version", appVersion);
    data.put("env", appEnv);
    data.put("description", appDescription);
    data.put("javaVersion", System.getProperty("java.version"));
    data.put("javaVendor", System.getProperty("java.vendor"));
    data.put("springBootVersion", org.springframework.boot.SpringApplication.class
      .getPackage().getImplementationVersion());
    return ResultVO.success(data);
  }

  /**
   * GET /api/system/env — 安全的环境信息
   */
  @GetMapping("/env")
  public ResultVO<Map<String, Object>> env() {
    Map<String, Object> data = new LinkedHashMap<>();
    List<String> safeKeys = List.of(
      "os.name", "os.version", "os.arch",
      "java.version", "java.home",
      "user.timezone", "file.encoding"
    );
    for (String key : safeKeys) {
      String value = System.getProperty(key);
      if (value != null) {
        data.put(key, value);
      }
    }
    String javaHome = System.getenv("JAVA_HOME");
    if (javaHome != null) {
      data.put("JAVA_HOME", javaHome);
    }
    return ResultVO.success(data);
  }
}

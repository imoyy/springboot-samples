package com.hxc.springboothello.controller;

import com.hxc.springboothello.model.ResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

  @Value("${spring.application.name}")
  private String appName;

  @Value("${app.version}")
  private String appVersion;

  @Value("${app.env}")
  private String appEnv;

  @GetMapping("/health")
  public ResultVO<Map<String, Object>> health() {
    Map<String, Object> info = new LinkedHashMap<>();
    info.put("name", appName);
    info.put("version", appVersion);
    info.put("env", appEnv);
    info.put("status", "UP");
    info.put("serverTime", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    return ResultVO.success(info);
  }
}

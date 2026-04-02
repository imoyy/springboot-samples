package com.hxc.springbootweek03.controller;

import com.hxc.springbootweek03.config.AppConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {
    private final AppConfig appConfig;

    public ConfigurationController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping("/info")
    public Map<String, String> getConfigurationMap() {
        return Map.of("version", appConfig.getVersion(), "description", appConfig.getDescription());
    }
}

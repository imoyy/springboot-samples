package com.hxc.springbootweek10.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class WeatherUpdateService {

    private final RestTemplate restTemplate;

    private String cachedWeather;

    public WeatherUpdateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCachedWeather() {
        return cachedWeather;
    }

    @Scheduled(fixedRate = 300000)
    public void updateWeather() {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=39.9042&longitude=116.4074&current_weather=true";
        try {
            JsonNode root = restTemplate.getForObject(url, JsonNode.class);
            JsonNode weather = root.get("current_weather");
            double temperature = weather.get("temperature").asDouble();
            double windSpeed = weather.get("windspeed").asDouble();
            cachedWeather = String.format("[%s] 北京 - 温度: %.1f°C, 风速: %.1f km/h",
                    LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")),
                    temperature, windSpeed);
            System.out.println("天气已更新: " + cachedWeather);
        } catch (Exception e) {
            System.err.println("获取天气数据失败: " + e.getMessage());
        }
    }
}

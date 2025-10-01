package com.weather.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weather.entity.WeatherSearchHistory;

@Service
public interface WeatherService {
   WeatherSearchHistory fetchWeather(String city);
   WeatherSearchHistory getWeatherById(Long id);
   List<WeatherSearchHistory> getAllWeather();
   WeatherSearchHistory updateWeather(Long id,WeatherSearchHistory e);
   WeatherSearchHistory partialUpdate(Long id,WeatherSearchHistory e);
   void deleteWeatherHistory(Long id);
}

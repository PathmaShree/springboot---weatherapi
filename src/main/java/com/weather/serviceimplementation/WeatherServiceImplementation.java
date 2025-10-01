package com.weather.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import com.weather.entity.WeatherSearchHistory;
import com.weather.repository.WeatherSearchHistoryRepository;
import com.weather.service.WeatherService;
import org.json.JSONObject;       
import org.json.JSONArray;        


@Component
public class WeatherServiceImplementation implements WeatherService {

	private final WeatherSearchHistoryRepository repo;
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Value("${weather.api.key}")
	private String apiKey;
	
	public WeatherServiceImplementation(WeatherSearchHistoryRepository repo) {
		this.repo = repo;
	}

	@Override
	public WeatherSearchHistory fetchWeather(String city) {
		// TODO Auto-generated method stub
	    String url = "https://api.openweathermap.org/data/2.5/weather?q=" 
                + city + "&appid=" + apiKey + "&units=metric";
	    
		try {
		String response = restTemplate.getForObject(url,String.class);
		  JSONObject json = new JSONObject(response);
          String temp = json.getJSONObject("main").get("temp").toString() + "°C";
          String desc = json.getJSONArray("weather").getJSONObject(0).getString("description");
          
          WeatherSearchHistory history = new WeatherSearchHistory();
          history.setCityName(city);
          history.setTemperature(temp);
          history.setWeatherDesc(desc);
          return repo.save(history);
		}
		catch (Exception e) {
            throw new RuntimeException("Failed to fetch weather for city: " + city, e);
        }
	}

	@Override
	public WeatherSearchHistory getWeatherById(Long id) {
		// TODO Auto-generated method stub
		Optional<WeatherSearchHistory> byId = repo.findById(id);
		if(byId.isPresent()) return byId.get();
		else  throw new RuntimeException("Weather record not found for ID: " + id);
	}

	@Override
	public List<WeatherSearchHistory> getAllWeather() {
		// TODO Auto-generated method stub
		List<WeatherSearchHistory> getAll = repo.findAll();
		if(getAll.isEmpty()) throw new RuntimeException("No records found");
		return getAll;
	
	}

	@Override
	public WeatherSearchHistory updateWeather(Long id, WeatherSearchHistory e) {
		// TODO Auto-generated method stub
		Optional<WeatherSearchHistory> byId = repo.findById(id);
		if(byId.isEmpty()) throw new RuntimeException("Weather record not found for ID: " + id);
		WeatherSearchHistory weather = null;
		if(byId.isPresent()) {
			weather = byId.get();
			weather.setCityName(e.getCityName());
			weather.setTemperature(e.getTemperature());
			weather.setWeatherDesc(e.getWeatherDesc());
		}
		
				
		return repo.save(weather);
	}

	@Override
	public WeatherSearchHistory partialUpdate(Long id, WeatherSearchHistory e) {
		// TODO Auto-generated method stub
		Optional<WeatherSearchHistory> byId = repo.findById(id);
		if(byId.isEmpty()) throw new RuntimeException("Weather record not found for ID: " + id);
		WeatherSearchHistory weather = null;
		if(byId.isPresent()) {
			weather = byId.get();
			if(e.getCityName()!= null) weather.setCityName(e.getCityName());
			if(e.getTemperature()!= null) weather.setTemperature(e.getTemperature());
			if(e.getWeatherDesc() != null) weather.setWeatherDesc(e.getWeatherDesc());
		}
		return repo.save(weather);
	}

	@Override
	public void deleteWeatherHistory(Long id) {
		// TODO Auto-generated method stub
		if(!repo.existsById(id)) throw new RuntimeException("Weather record not found for ID: " + id);
		repo.deleteById(id);
	}
	
	
}

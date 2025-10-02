package com.weather.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.entity.WeatherSearchHistory;
import com.weather.serviceimplementation.WeatherServiceImplementation;

@RestController
@RequestMapping("/weatherapi")
public class WeatherController {
    private final WeatherServiceImplementation i;
    
    WeatherController(WeatherServiceImplementation i){
    	this.i= i;
    }
    
    @GetMapping("/fetch")
    public ResponseEntity<?> fetchWeather(@RequestParam String city) {
    	try {
    		WeatherSearchHistory fetchWeather = i.fetchWeather(city);
    		return new ResponseEntity<WeatherSearchHistory>(fetchWeather,HttpStatus.OK);
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    	}
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> getAll(WeatherSearchHistory en){
    	try {
    	List<WeatherSearchHistory> allWeather = i.getAllWeather();
    	return new ResponseEntity<List<WeatherSearchHistory>>(allWeather,HttpStatus.OK);
    }
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    		
    	}
    }
    
    
    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
    	try {
    	WeatherSearchHistory weatherById = i.getWeatherById(id);
    	return new ResponseEntity<WeatherSearchHistory>(weatherById,HttpStatus.OK);
    }
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    	}
    }
    

    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAll(@PathVariable Long id,@RequestBody WeatherSearchHistory entity){
    	try {
    		WeatherSearchHistory updateWeather = i.updateWeather(id, entity);
    		return new ResponseEntity<WeatherSearchHistory>(updateWeather,HttpStatus.OK);
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    	}
    	
    	}
    @PatchMapping("/partialUpdate/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long id,@RequestBody WeatherSearchHistory entity){
    	try {
    		WeatherSearchHistory partialUpdate = i.partialUpdate(id, entity);
    		return new ResponseEntity<WeatherSearchHistory>(partialUpdate,HttpStatus.OK);
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    	}
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHistory(@PathVariable Long id){
    	try {
    		i.deleteWeatherHistory(id);
    		return new ResponseEntity<>("Deleted record with ID : "+id,HttpStatus.NO_CONTENT);
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    	}
    }
    	
    
}

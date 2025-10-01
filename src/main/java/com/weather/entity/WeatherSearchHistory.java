package com.weather.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "WEATHER_SEARCH_HISTORY")
public class WeatherSearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(name = "CITY_NAME", nullable = false, length = 100)
    private String cityName;
    
    @Column(name = "TEMPERATURE", length = 50)
    private String temperature;
    
    @Column(name = "WEATHER_DESC", length = 255)
    private String weatherDesc;
    
    @Column(name = "SEARCHED_AT", insertable = false, updatable = false)
    private LocalDateTime searchedAt;
}

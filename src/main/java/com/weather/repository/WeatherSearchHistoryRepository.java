package com.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weather.entity.WeatherSearchHistory;

@Repository
public interface WeatherSearchHistoryRepository extends JpaRepository<WeatherSearchHistory,Long> {

}

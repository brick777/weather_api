package net.andrzejczak.weather.services;

import net.andrzejczak.weather.dao.Forecast;
import net.andrzejczak.weather.dto.ForecastDto;

import java.util.List;

public interface ForecastService {
    List<ForecastDto> getAllHistoryList(String[] windDirection);
    ForecastDto insertNewForecast(ForecastDto forecastDto);
    void deleteForecast(long id);
}

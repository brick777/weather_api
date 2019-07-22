package net.andrzejczak.weather.services;

import lombok.Setter;
import net.andrzejczak.weather.dao.Forecast;
import net.andrzejczak.weather.dto.ForecastDto;
import net.andrzejczak.weather.repository.ForecastRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.List;

public class DbForecastService implements ForecastService {
    @Autowired
    private ForecastRepository forecastRepository;

    @Autowired
    @Setter
    private ModelMapper modelMapper;

    @Override
    public List<ForecastDto> getAllHistoryList(final String[] windDirection) {
        Type listType = new TypeToken<List<ForecastDto>>() {
        }.getType();
        return modelMapper.map(getForecastEntityList(windDirection), listType);
    }

    private List<Forecast> getForecastEntityList(final String[] windDirection) {
        return forecastRepository.findAllByWindDirectionsOrderByDateDesc(windDirection);
    }

    @Override
    public ForecastDto insertNewForecast(final ForecastDto forecastDto) {
        Forecast forecast = new Forecast();
        forecast.setDate(forecastDto.getDate());
        forecast.setTemperature(forecastDto.getTemperature());
        forecast.setWindDirection(forecastDto.getWindDirection());

        forecastRepository.save(forecast);

        return modelMapper.map(forecast, ForecastDto.class);

    }

    @Override
    public void deleteForecast(final long id) {
        forecastRepository.deleteById(id);
    }
}

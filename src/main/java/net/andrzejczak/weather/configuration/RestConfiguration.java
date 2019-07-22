package net.andrzejczak.weather.configuration;

import net.andrzejczak.weather.services.DbForecastService;
import net.andrzejczak.weather.services.ForecastService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfiguration {
    @Bean
    public ForecastService createForecastService(){
        return new DbForecastService();
    }
}

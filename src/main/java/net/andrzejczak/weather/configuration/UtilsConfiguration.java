package net.andrzejczak.weather.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfiguration {
    @Bean
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }
}

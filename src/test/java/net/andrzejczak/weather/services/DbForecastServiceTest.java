package net.andrzejczak.weather.services;

import net.andrzejczak.weather.dao.Forecast;
import net.andrzejczak.weather.dto.ForecastDto;
import net.andrzejczak.weather.repository.ForecastRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbForecastServiceTest {

    public static final LocalDate DATE_TEST = LocalDate.now();
    public static final int TEMPERATURE_TEST = 15;
    public static final String WIND_DIRECTION_TEST = "WEST";
    public static final long ID_TEST = 1L;

    @Mock
    private ForecastRepository forecastRepository;

    private ModelMapper modelMapper;

    @InjectMocks
    private DbForecastService forecastService;

    @Before
    public void setUp() {
        modelMapper = new ModelMapper();
        forecastService.setModelMapper(modelMapper);
    }

    @Test
    public void testGetAllHistoryList() {
        String[] testArray = new String[] {"WEST", "EAST"};

        Forecast forecast = new Forecast();
        forecast.setId(ID_TEST);
        forecast.setDate(DATE_TEST);
        forecast.setTemperature(TEMPERATURE_TEST);
        forecast.setWindDirection(WIND_DIRECTION_TEST);

        when(forecastRepository.findAllByWindDirectionsOrderByDateDesc(any())).thenReturn(Arrays.asList(forecast));

        List<ForecastDto> forecastDtoList = forecastService.getAllHistoryList(testArray);

        verify(forecastRepository).findAllByWindDirectionsOrderByDateDesc(any());
        assertThat(forecastDtoList.size()).isEqualTo(1);
        assertThat(forecastDtoList.get(0).getWindDirection()).isEqualTo(WIND_DIRECTION_TEST);
        assertThat(forecastDtoList.get(0).getTemperature()).isEqualTo(TEMPERATURE_TEST);
        assertThat(forecastDtoList.get(0).getDate()).isEqualTo(DATE_TEST);

    }

    @Test
    public void testInsertNewForecast() {
        ForecastDto forecastDto = getForecastDto();

        ForecastDto result = forecastService.insertNewForecast(forecastDto);

        verify(forecastRepository).save(any(Forecast.class));
        assertThat(result.getWindDirection()).isEqualTo(WIND_DIRECTION_TEST);
        assertThat(result.getDate()).isEqualTo(DATE_TEST);
        assertThat(result.getTemperature()).isEqualTo(TEMPERATURE_TEST);
    }

    @Test
    public void testDeleteForecast() {
        forecastService.deleteForecast(ID_TEST);

        verify(forecastRepository).deleteById(ID_TEST);
    }

    private ForecastDto getForecastDto() {
        ForecastDto forecastDto = ForecastDto.builder()
            .date(DATE_TEST)
            .temperature(TEMPERATURE_TEST)
            .windDirection(WIND_DIRECTION_TEST)
            .build();

        return forecastDto;
    }
}

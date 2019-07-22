package net.andrzejczak.weather.contollers;

import lombok.extern.slf4j.Slf4j;
import net.andrzejczak.weather.dto.ForecastDto;
import net.andrzejczak.weather.services.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ForecastRestController {

    @Autowired
    private ForecastService forecastService;

    @RequestMapping(value = "/forecasts", method = RequestMethod.GET)
    public ResponseEntity<List<ForecastDto>> getForecastsList(@RequestParam(value = "windDirection", required = false) String[] windDirection) {
        return new ResponseEntity<>(forecastService.getAllHistoryList(windDirection), HttpStatus.OK);
    }

    @RequestMapping(value = "/forecasts/management/authentication", method = RequestMethod.POST)
    public ResponseEntity<Void> checkCallWithAuthentication() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/forecasts/management", method = RequestMethod.PUT)
    public ResponseEntity<ForecastDto> insertForecast(@RequestBody final ForecastDto forecastDto) {
        ForecastDto result = forecastService.insertNewForecast(forecastDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/forecasts/management/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeForecast(@PathVariable("id") final long id) {
        forecastService.deleteForecast(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

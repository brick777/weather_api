package net.andrzejczak.weather.dao;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private int temperature;
    @Column(name="winddirection")
    private String windDirection;
}

package net.andrzejczak.weather.repository;

import net.andrzejczak.weather.dao.Forecast;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {
    @Query(value = "SELECT f FROM Forecast f WHERE f.windDirection IN :directions Order by f.date DESC")
    List<Forecast> findAllByWindDirectionsOrderByDateDesc(@Param("directions") String[] directions);

    void deleteById(long id);
}

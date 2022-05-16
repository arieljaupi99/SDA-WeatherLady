package com.gr007.weatherapp;

import bean.WeatherResponse;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@Entity
@Table(name = "weather_parameters")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class WeatherParametersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer weather_id;
    @Column
    private Double temperature;
    @Column
    private Double pressure;
    @Column
    private Double humidity;
    @Column
    private Date date_time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "location_id")
    private LocationEntity locationEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wind_id", referencedColumnName = "id")
    private WindEntity windEntity;


    public WeatherParametersEntity(WeatherResponse weatherResponse) {
        this.temperature = weatherResponse.getMain().getTemp();
        this.pressure = weatherResponse.getMain().getPressure();
        this.humidity = weatherResponse.getMain().getHumidity();
        long milliSecondTimeStamp = MILLISECONDS.convert(weatherResponse.getDt(), SECONDS);
        this.date_time = new Date(milliSecondTimeStamp);
        this.windEntity = new WindEntity(weatherResponse);

    }
    public WeatherParametersEntity(WeatherResponse weatherResponse, LocationEntity locationEntity ) {
        this(weatherResponse);
        this.locationEntity = locationEntity;
    }
}
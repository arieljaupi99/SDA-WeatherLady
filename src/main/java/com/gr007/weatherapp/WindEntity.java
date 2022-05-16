package com.gr007.weatherapp;

import bean.WeatherResponse;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "wind")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class WindEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private Integer direction;
    @Column
    private Double speed;
    @OneToOne (mappedBy = "windEntity")
    @ToString.Exclude
    private WeatherParametersEntity weatherParametersEntity;

    public WindEntity(WeatherResponse weatherResponse) {
        this.direction = weatherResponse.getWind().getDeg();
        this.speed = weatherResponse.getWind().getSpeed();
    }

}
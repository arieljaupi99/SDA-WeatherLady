package com.gr007.weatherapp;

import bean.WeatherResponse;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LocationEntity {
    //    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
//    @Column(name = "id", updatable = false, nullable = false)
//    private UUID id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private Double latitude;
    @Column
    private Double longitude;
    @Column
    private String region;
    @Column
    private String contry_name;

    @OneToMany(mappedBy = "locationEntity",orphanRemoval= true )
    @ToString.Exclude
    private List<WeatherParametersEntity> weatherParametersEntityList;

    public LocationEntity(WeatherResponse weatherResponse) {
        this.latitude = weatherResponse.getCoord().getLon();
        this.longitude = weatherResponse.getCoord().getLat();
        this.region = weatherResponse.getName();
        this.contry_name = weatherResponse.getSys().getCountry();
    }
}
package service;

import com.gr007.weatherapp.LocationEntity;
import com.gr007.weatherapp.WeatherParametersEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class LocationService {
    private EntityManager entityManager;

    public LocationService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<WeatherParametersEntity> findByName(String name) {
        return entityManager.createQuery("SELECT wpe FROM WeatherParametersEntity wpe, WindEntity WHERE wpe.locationEntity.region = :name", WeatherParametersEntity.class)
                .setParameter("name", name)
                .getResultList();
    }

    public void deleteById (int id) {
        entityManager.createQuery("delete from LocationEntity le where le.id = :id", LocationEntity.class )
                .setParameter("id",id).executeUpdate();
        entityManager.refresh(LocationEntity.class);
    }

    public void delete (Integer id){


        try {
            LocationEntity a = entityManager.find(LocationEntity.class, id);


            entityManager.getTransaction().begin();
            entityManager.remove(a);
            entityManager.getTransaction().commit();
        } catch (IllegalArgumentException e) {
            System.out.println("id e vendosur nuk egziston");
            e.printStackTrace();
        }

    }

    public void selectAll() {
        List<WeatherParametersEntity> list = entityManager.createQuery(" FROM WeatherParametersEntity wpe ",WeatherParametersEntity.class)
                .getResultList();
        System.out.println(list+"\n");
    }
}

import bean.WeatherResponse;
import com.gr007.weatherapp.LocationEntity;
import com.gr007.weatherapp.WeatherParametersEntity;
import com.gr007.weatherapp.WindEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.LocationService;
import service.WeatherService;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    private static final String DATABASE = "weather.xml";


    public static void main(String[] args) throws IOException {

        SessionFactory sessionFactory = new Configuration()
                .configure(DATABASE)
                .buildSessionFactory();
        WeatherResponse response = new WeatherService().getLiveWeatherValues("Tirana");
        //System.out.println(response.toString());
        WeatherService weatherService = new WeatherService();
        weatherService.persistWeather(new WeatherParametersEntity(response, new LocationEntity(response)), sessionFactory);

        EntityManager entityManager = sessionFactory.createEntityManager();
        LocationService locationService = new LocationService(entityManager);
        //System.out.println(locationService.findByName("Tirana"));
        //locationService.delete(2);
        /*locationService.selectAll();*/

        Scanner scanner = new Scanner(System.in);
        boolean exit = true;


        while (exit) {
            System.out.println("Weather App");
            System.out.println("----------------");
            System.out.println("See Your DataBase: Press 1");
            System.out.println("Add to database: Press 2");
            System.out.println("Delete from database: Press 3");
            System.out.println("Exit App: Press 4");
            System.out.println("Select your choice");
            Integer choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    locationService.selectAll();
                    break;
                case 2:
                    System.out.println("What city do you want to add");
                    String name = scanner.next();


                    response = new WeatherService().getLiveWeatherValues(name);
                    weatherService.persistWeather(new WeatherParametersEntity(response, new LocationEntity(response)), sessionFactory);

                    entityManager = sessionFactory.createEntityManager();
                    locationService = new LocationService(entityManager);

                    break;

                case 3:
                    System.out.println("Delete from database with Ciyt Id");
                    int id = scanner.nextInt();
                    locationService.delete(id);
                    break;
                case 4:
                    exit = false;
                    break;
                default:
                    System.out.println("Your choice is not aviable");
            }
        }

    }


}

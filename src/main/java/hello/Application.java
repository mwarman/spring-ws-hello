package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hello.service.WeatherService;
import hello.wsdl.GetCityForecastByZIPResponse;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    CommandLineRunner lookup(WeatherService weatherService) {
        return args -> {
            String zipCode = "94304";

            if (args.length > 0) {
                zipCode = args[0];
            }

            GetCityForecastByZIPResponse response = weatherService
                    .getForecastByZip(zipCode);

            weatherService.printForecast(response);
        };
    }

}

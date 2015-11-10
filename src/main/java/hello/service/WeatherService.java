package hello.service;

import hello.wsdl.GetCityForecastByZIPResponse;

public interface WeatherService {

    GetCityForecastByZIPResponse getForecastByZip(String zipCode);

    void printForecast(GetCityForecastByZIPResponse response);

}

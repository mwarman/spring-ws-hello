package hello.service;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.wsdl.Forecast;
import hello.wsdl.ForecastReturn;
import hello.wsdl.GetCityForecastByZIPResponse;
import hello.wsdl.Temp;

@Service
public class WeatherServiceBean implements WeatherService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WeatherClient webServiceClient;

    @Override
    public GetCityForecastByZIPResponse getForecastByZip(String zipCode) {
        logger.info("> getForecastByZip zip:{}", zipCode);

        GetCityForecastByZIPResponse response = webServiceClient
                .getCityForecastByZip(zipCode);

        logger.info("< getForecastByZip zip:{}", zipCode);
        return response;
    }

    @Override
    public void printForecast(GetCityForecastByZIPResponse response) {
        logger.info("> printForecast");

        ForecastReturn forecastReturn = response
                .getGetCityForecastByZIPResult();

        if (forecastReturn.isSuccess()) {
            logger.info("Forecast for " + forecastReturn.getCity() + ", "
                    + forecastReturn.getState());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            for (Forecast forecast : forecastReturn.getForecastResult()
                    .getForecast()) {

                Temp temperature = forecast.getTemperatures();

                logger.info(String.format("%s %s %s°-%s°",
                        format.format(forecast.getDate().toGregorianCalendar()
                                .getTime()),
                        forecast.getDesciption(), temperature.getMorningLow(),
                        temperature.getDaytimeHigh()));
                logger.info("");
            }
        } else {
            logger.info("No forecast received");
        }

        logger.info("< printForecast");
    }

}

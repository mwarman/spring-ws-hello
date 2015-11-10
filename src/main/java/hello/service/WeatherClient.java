package hello.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import hello.wsdl.GetCityForecastByZIP;
import hello.wsdl.GetCityForecastByZIPResponse;

public class WeatherClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory
            .getLogger(WeatherClient.class);

    GetCityForecastByZIPResponse getCityForecastByZip(String zipCode) {

        GetCityForecastByZIP request = new GetCityForecastByZIP();
        request.setZIP(zipCode);

        log.info("Requesting forecast for " + zipCode);

        GetCityForecastByZIPResponse response = (GetCityForecastByZIPResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://wsf.cdyne.com/WeatherWS/Weather.asmx", request,
                        new SoapActionCallback(
                                "http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP"));

        return response;
    }

}

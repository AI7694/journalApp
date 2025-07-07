package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Api.response.WeatherResponse;
import net.engineeringdigest.journalApp.Cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static net.engineeringdigest.journalApp.Cache.AppCache.APP_CACHE;

@Service
public class WeatherService
{

@Value("${weather.api.key}")
    private   String apikey ;

    private static final String API ="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {
        try {
            System.out.println("Cache at runtime: " + appCache.APP_CACHE);

            String Template = appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString());
 if(Template==null){
     System.err.println(("ERROR:WEATHER API NOT FOUND"));
     return null;
 }




     String finalAPI = Template.replace("<city>", city).replace("<apikey>", apikey);

            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();

            if(body==null||body.getCurrent() ==null){
                System.out.println("Weather API limt might have been exceeded o");
                return null;
            }


            return body;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}

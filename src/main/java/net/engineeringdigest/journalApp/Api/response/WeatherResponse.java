package net.engineeringdigest.journalApp.Api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    public static class Current {

        @JsonProperty("observation_time")
        private String observationTime;

        private int temperature;

        @JsonProperty("weather_code")
        private int weatherCode;

        @JsonProperty("wind_degree")
        private int windDegree;

        @JsonProperty("wind_dir")
        private String windDir;

        private int feelslike;


        @Override
        public String toString() {
            return "Current{feelslike=" + feelslike + ", temperature=" + temperature + ", windDir='" + windDir + "'}";
        }
    }

}




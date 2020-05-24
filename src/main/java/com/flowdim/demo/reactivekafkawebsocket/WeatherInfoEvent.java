package com.flowdim.demo.reactivekafkawebsocket;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherInfoEvent {


     private long stationId;
     private int temperature;

     WeatherInfoEvent (){

     }
     WeatherInfoEvent(long stationId, int temperature) {
          this.stationId = stationId;
          this.temperature = temperature;
     }

     @Override
     public String toString() {
          return "WeatherInfoEvent{" +
                  "stationId=" + stationId +
                  ", temperature=" + temperature +
                  '}';
     }


     public static void main(String[] args) throws Exception{
          System.out.println(new ObjectMapper().writeValueAsString(new WeatherInfoEvent(12, 1)));
     }
     // Getter and Setter methods
}
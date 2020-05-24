package com.flowdim.demo.reactivekafkawebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController // (1) Spring MVC annotation
public class WeatherInfoController {

     @Autowired
     private WeatherInfoEventProcessor processor;

     private Flux<WeatherInfoEvent> bridge;

     public WeatherInfoController() {
          // (3) Broadcast to several subscribers
          this.bridge = createBridge().publish().autoConnect().cache(10).log();
     }

     // (1) Spring MVC annotation
     @GetMapping(value = "/weather", produces = "text/event-stream;charset=UTF-8")
     @CrossOrigin
     public Flux<WeatherInfoEvent> getWeatherInfo() {
          return bridge;
     }

    @GetMapping(value = "/isActive")
    public String active() {
        return "ISACTIVE";
    }

     private Flux<WeatherInfoEvent> createBridge() {
          Flux<WeatherInfoEvent> bridge = Flux.create(sink -> { // (2)
               processor.register(new WeatherInfoEventListener() {

                   @Override
                   public void processComplete() {
                       sink.complete();
                   }

                   @Override
                   public void onData(WeatherInfoEvent data) {
                       sink.next(data);
                   }
                });
          });
          return bridge;
     }
}
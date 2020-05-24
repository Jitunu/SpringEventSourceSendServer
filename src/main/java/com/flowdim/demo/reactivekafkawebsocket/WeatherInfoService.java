package com.flowdim.demo.reactivekafkawebsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.util.Random;

@Service
public class WeatherInfoService {
     private final Logger logger = LoggerFactory.getLogger(getClass());

     @Autowired
     private KafkaTemplate<String, WeatherInfoEvent> kafkaTemplate;

     public ListenableFuture<SendResult<String, WeatherInfoEvent>> sendMessage(String topic, WeatherInfoEvent message) {
          logger.info(String.format("#### -> Producing message -> %s", message));
          return this.kafkaTemplate.send(topic, message);
     }

     @Scheduled(fixedDelay = 5000)
     public void getWeatherInfoJob() throws IOException {
          logger.info("generate fake weather event");
          // fake event
          WeatherInfoEvent event = new WeatherInfoEvent(new Random().nextLong(), new Random().nextInt());
          sendMessage("KAFA_STP", event);
     }
}
package com.flowdim.demo.reactivekafkawebsocket;

import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class WeatherInfoEventProcessor {

     private final Logger logger = LoggerFactory.getLogger(getClass());

     private WeatherInfoEventListener listener;
//     private final ThreadLocal<ConsumerSeekCallback> seekCallBack = new ThreadLocal<>();

     public void register(WeatherInfoEventListener listener) {
          this.listener = listener;
     }

     public void onEvent(WeatherInfoEvent event) {
          if (listener != null) {
               listener.onData(event);
          }
      }

     public void onComplete() {
         if (listener != null) {
              listener.processComplete();
         }
     }

     @KafkaListener(topics = "KAFA_STP", groupId = "group_id")
     public void consume(WeatherInfoEvent message) throws IOException {
//          this.seekCallBack.get().seek("KAFA_STP", 1, 1);
          logger.info(String.format("#### -> Consumed message -> %s", message));
          onEvent(message);
     }

//     @Override
//     public void registerSeekCallback(ConsumerSeekCallback consumerSeekCallback) {
//          this.seekCallBack.set(consumerSeekCallback);
//     }
//
//     @Override
//     public void onPartitionsAssigned(Map<TopicPartition, Long> map, ConsumerSeekCallback consumerSeekCallback) {
//
//     }
//
//     @Override
//     public void onIdleContainer(Map<TopicPartition, Long> map, ConsumerSeekCallback consumerSeekCallback) {
//
//     }
}
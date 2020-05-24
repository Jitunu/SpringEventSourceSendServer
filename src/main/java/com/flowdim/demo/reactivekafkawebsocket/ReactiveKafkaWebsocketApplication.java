package com.flowdim.demo.reactivekafkawebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.flowdim.demo.reactivekafkawebsocket"})
public class ReactiveKafkaWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveKafkaWebsocketApplication.class, args);
    }

}

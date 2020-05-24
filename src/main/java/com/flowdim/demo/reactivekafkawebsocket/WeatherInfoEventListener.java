package com.flowdim.demo.reactivekafkawebsocket;



public interface WeatherInfoEventListener {
    void onData(WeatherInfoEvent event);
    void processComplete();
}
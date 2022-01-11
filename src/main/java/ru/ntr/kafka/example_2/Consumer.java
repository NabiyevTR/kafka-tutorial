package ru.ntr.kafka.example_2;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-ex2.properties")
@Slf4j
public class Consumer {


    public static void main(String[] args) {
        SpringApplication.run(Consumer.class, args);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("ex2_topic")
                .partitions(2)
                .replicas(1)
                .build();
    }

    @KafkaListener(topics = "ex2_topic")
    public void listen(String msg) {
        log.info("Message received: " + msg);
    }

}
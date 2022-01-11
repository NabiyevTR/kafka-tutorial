package ru.ntr.kafka.example_2;

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
public class Consumer {


    public static void main(String[] args) {
        SpringApplication.run(Consumer.class, args);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @KafkaListener(topics = "topic1")
    public void listen(String in) {
        System.out.println(in);
    }

}
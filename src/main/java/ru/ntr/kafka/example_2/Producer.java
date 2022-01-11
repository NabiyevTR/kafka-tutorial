package ru.ntr.kafka.example_2;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@Profile("ex2")
public class Producer {

    public static void main(String[] args) {
        SpringApplication.run(Producer.class, args);
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> template) {
        return args -> {
            template.send("topic1", "test");
        };
    }
}

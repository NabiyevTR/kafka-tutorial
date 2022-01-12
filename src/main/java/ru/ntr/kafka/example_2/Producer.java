package ru.ntr.kafka.example_2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Producer implements CommandLineRunner {

    private final KafkaTemplate<String, String> template;

    public static void main(String[] args) {
        SpringApplication.run(Producer.class).close();
    }

    @Override
    public void run(String... args) throws Exception {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {

                String message = reader.readLine();

                if (message.equalsIgnoreCase("exit")) break;

                ListenableFuture<SendResult<String, String>> future = template.send("ex2_topic", message);
                future.addCallback(new ListenableFutureCallback<>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        log.warn("Error during sending message");
                    }

                    @Override
                    public void onSuccess(SendResult<String, String> result) {
                        log.info("Message was sent: " + message);
                    }
                });
            }
        }
    }
}

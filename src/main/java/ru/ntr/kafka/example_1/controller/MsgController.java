package ru.ntr.kafka.example_1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("msg")
@RequiredArgsConstructor
public class MsgController {

    private final KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping
    public void sendOrder(String msgId, String msg) throws ExecutionException, InterruptedException {
        kafkaTemplate.send("msg", msgId, msg).get();
    }


}

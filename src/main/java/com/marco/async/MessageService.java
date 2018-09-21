package com.marco.async;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    public void processMessages() {
        List<String> messages = Arrays.asList("Message1", "Message2", "Message3", "Message4", "Message5","Message6");
        logger.info("Messages retrieved correctly");
        logger.info("Starting queueing process");
        Supplier<String> heavySending = () -> {
            try {
                Thread.sleep(messages.size() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "All messages sent after " + messages.size() + " seconds";
        };

        Consumer<String> resultHeavySending = (results) -> {
            logger.info("Result from sending" + results);
        };

        CompletableFuture.supplyAsync(heavySending).thenAccept(resultHeavySending);
        logger.info("Finished general process");

    }
}

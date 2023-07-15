package com.wtt.consumer.service;

import com.wtt.consumer.model.PairRandomNumbers;
import com.wtt.consumer.utils.JsonParser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
class RabbitMqListener {

    private final Calculator calculator;

    public RabbitMqListener(Calculator calculator) {
        this.calculator = calculator;
    }

    @RabbitListener(queues = "ADD_QUEUE")
    public void listenerForNumbersToAdd(Message message) {
        PairRandomNumbers pair = JsonParser.mapToObject(new String(message.getBody()), PairRandomNumbers.class);
        System.out.println(pair + " provided to Calculator!");
        calculator.add(pair);
    }

    @RabbitListener(queues = "SUBTRACT_QUEUE")
    public void listenerForNumbersToSubtract(Message message) {
        PairRandomNumbers pair = JsonParser.mapToObject(new String(message.getBody()), PairRandomNumbers.class);
        System.out.println(pair + " provided to Calculator!");
        calculator.subtract(pair);
    }

    @RabbitListener(queues = "DIVISION_QUEUE")
    public void listenerForNumbersToDivision(Message message) {
        PairRandomNumbers pair = JsonParser.mapToObject(new String(message.getBody()), PairRandomNumbers.class);
        System.out.println(pair + " provided to Calculator!");
        calculator.divide(pair);
    }

    @RabbitListener(queues = "MULTIPLICATION_QUEUE")
    public void listenerForNumbersToMultiplication(Message message) {
        PairRandomNumbers pair = JsonParser.mapToObject(new String(message.getBody()), PairRandomNumbers.class);
        System.out.println(pair + " provided to Calculator!");
        calculator.multiplication(pair);
    }
}

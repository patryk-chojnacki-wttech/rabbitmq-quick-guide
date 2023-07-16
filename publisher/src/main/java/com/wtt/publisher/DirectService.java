package com.wtt.publisher;

import com.wtt.publisher.utils.JsonParser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class DirectService {


    private final Scanner scanner;
    private RabbitTemplate rabbitTemplate;

    public DirectService(RabbitTemplate rabbitTemplate) {
        scanner = new Scanner(System.in);
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * This method is used to send the pair of random numbers to the RabbitMq.
     * The method will ask the user to enter a number between 1 and 4.
     * If the user enters number betwen 1-4, the program will send the pair of random numbers to the specific queue.
     * If the user enters Q, the program will be terminated.
     * The method start after the Spring application is ready.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void runLogic() {
        System.out.println("Direct Service is running...");
        while (true) {
            System.out.println("Please enter a number between 1 and 4 to select the operation you want to perform on random numbers: \n" +
                    "1-Add, 2-Subtract, 3-Multiply, 4-Divide, Q-Quit");
            String userInput = scanner.nextLine();
            switch (userInput.toUpperCase()) {
                case "1":
                    sendToAdd(new PairRandomNumbers());
                    break;
                case "2":
                    sendToSubtract(new PairRandomNumbers());
                    break;
                case "3":
                    sendToMultiplication(new PairRandomNumbers());
                    break;
                case "4":
                    sendToDivide(new PairRandomNumbers());
                    break;
                case "Q":
                    System.out.println("Good bye! The application is terminated");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    break;
            }
        }
    }

    /**
     * The following method is used to send a pair of random numbers to RabbitMq to perform the add calculation
     * PairRandomNumbers is a pair of random numbers which is randomly generated in the class constructor
     * PairNumber is mapped to Json format using JsonParser class
     * The message is sent to RabbitMq using the RabbitTemplate
     * The first parameter is the exchange name, which is the name of the exchange that will handle message
     * If exchange name is empty, the message will be sent to the default exchange
     * The second parameter is the routing key, by this key the message will be routed to the specific queue
     * If the routing key not match any queue, the message will be discarded
     * The third parameter is the new RabbitMq message which have some metadata and the body of the message
     * The Body of the message is the pair of random numbers in Json format transformed to bytes
     */
    public void sendToAdd(PairRandomNumbers pairNumbers) {
        System.out.println(String.format("%s is sent to Calculator Service with routing key: %s to add values.", pairNumbers.toString(), RabbitMqDirectConfiguration.ADD_QUEUE_NAME));
        String pairNumberJson = JsonParser.mapToJson(pairNumbers);
        rabbitTemplate.send(RabbitMqDirectConfiguration.MATH_EXCHANGE, RabbitMqDirectConfiguration.ADD_QUEUE_NAME, new Message(pairNumberJson.getBytes()));
    }

    /**
     * The following method is used to send a pair of random numbers to RabbitMq to perform the subtract calculation
     * Parameters logic is the same as the previous method
     */
    public void sendToSubtract(PairRandomNumbers pairNumbers) {
        System.out.println(String.format("%s is sent to Calculator Service with routing key: %s to subtract values", pairNumbers.toString(), RabbitMqDirectConfiguration.SUBTRACT_QUEUE_NAME));
        String pairNumberJson = JsonParser.mapToJson(pairNumbers);
        rabbitTemplate.send(RabbitMqDirectConfiguration.MATH_EXCHANGE, RabbitMqDirectConfiguration.SUBTRACT_QUEUE_NAME, new Message(pairNumberJson.getBytes()));
    }

    /**
     * The following method is used to send a pair of random numbers to RabbitMq to perform the divide calculation
     * Parameters logic is the same as at the previous method
     */
    public void sendToDivide(PairRandomNumbers pairNumbers) {
        System.out.println(String.format("%s is sent to Calculator Service with routing key: %s to divide values.", pairNumbers.toString(), RabbitMqDirectConfiguration.DIVIDE_QUEUE_NAME));
        String pairNumberJson = JsonParser.mapToJson(pairNumbers);
        rabbitTemplate.send(RabbitMqDirectConfiguration.MATH_EXCHANGE, RabbitMqDirectConfiguration.DIVIDE_QUEUE_NAME, new Message(pairNumberJson.getBytes()));
    }

    /**
     * The following method is used to send a pair of random numbers to RabbitMq to perform the multiplicative calculation
     * Parameters logic is the same as at the previous method
     */
    public void sendToMultiplication(PairRandomNumbers pairNumbers) {
        System.out.println(String.format("%s is sent to Calculator Service with routing key: %s to multiply values.", pairNumbers.toString(), RabbitMqDirectConfiguration.MULTIPLICATION_QUEUE_NAME));
        String pairNumberJson = JsonParser.mapToJson(pairNumbers);
        rabbitTemplate.send(RabbitMqDirectConfiguration.MATH_EXCHANGE, RabbitMqDirectConfiguration.MULTIPLICATION_QUEUE_NAME, new Message(pairNumberJson.getBytes()));
    }
}
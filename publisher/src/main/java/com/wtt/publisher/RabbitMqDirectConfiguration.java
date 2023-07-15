package com.wtt.publisher;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqDirectConfiguration {
    public final static String ADD_QUEUE_NAME = "ADD_QUEUE";
    public final static String SUBTRACT_QUEUE_NAME = "SUBTRACT_QUEUE";
    public final static String MULTIPLICATION_QUEUE_NAME = "MULTIPLICATION_QUEUE";
    public final static String DIVIDE_QUEUE_NAME = "DIVISION_QUEUE";
    public final static String MATH_EXCHANGE = "MATH_EXCHANGE";

    /**
     * This bean create a queue with the name ADD_QUEUE_NAME
     * At this queue will be send all the messages that are related to the addition operation
     * .nonDurable property is set to true, so the queue will not survive a broker restart
     * .autoDelete property is set to true, so the queue will be deleted when
     * there are no more consumers subscribed to it
     * Below additional properties are set to true because we want to
     * create a temporary queue for guide purposes
     */
    @Bean
    Queue queueAdd() {
        return QueueBuilder.nonDurable(ADD_QUEUE_NAME)
                .autoDelete()
                .build();
    }

    /**
     * This bean create a queue with the name SUBTRACT_QUEUE_NAME
     * At this queue will be send all the messages that are related to the subtraction operation
     * Like above additional properties are set to create temporary queues
     */
    @Bean
    Queue queueSubtract() {
        return QueueBuilder.nonDurable(SUBTRACT_QUEUE_NAME)
                .autoDelete()
                .build();
    }

    /**
     * This bean create a queue with the name MULTIPLICATION_QUEUE_NAME
     * At this queue will be send all the messages that are related to the multiplication operation
     * Like above additional properties are set to create temporary queues
     */
    @Bean
    Queue queueMultiplication() {
        return QueueBuilder.nonDurable(MULTIPLICATION_QUEUE_NAME)
                .autoDelete()
                .build();
    }

    /**
     * This bean create a queue with the name DIVIDE_QUEUE_NAME
     * At this queue will be send all the messages that are related to the division operation
     * Like above additional properties are set to create temporary queues
     * =
     */
    @Bean
    Queue queueDivide() {
        return QueueBuilder.nonDurable(DIVIDE_QUEUE_NAME)
                .autoDelete()
                .build();
    }

    /**
     * This bean create a direct exchange with the name MATH_EXCHANGE
     * This exchange will be used to route messages to the queues
     * autoDelete property is set to true, so the exchange will be deleted when
     * there are no more queues bound to it
     */
    @Bean
    DirectExchange directMathExchange() {
        return ExchangeBuilder.directExchange(MATH_EXCHANGE)
                .autoDelete()
                .build();
    }

    /**
     * This bean create a binding between the ADD_QUEUE_NAME queue and the MATH_EXCHANGE exchange
     * The routing key is the same as the queue name
     */
    @Bean
    Binding bindAddQueueToExchange() {
        return BindingBuilder
                .bind(queueAdd())
                .to(directMathExchange())
                .withQueueName();
    }

    /**
     * This bean create a binding between the SUBTRACT_QUEUE_NAME queue and the MATH_EXCHANGE exchange
     * The routing key is the same as the queue name
     */
    @Bean
    Binding bindSubtractQueueToExchange() {
        return BindingBuilder
                .bind(queueSubtract())
                .to(directMathExchange())
                .withQueueName();
    }

    /**
     * This bean create a binding between the MULTIPLICATION_QUEUE_NAME queue and the MATH_EXCHANGE exchange
     * The routing key is the same as the queue name
     */
    @Bean
    Binding bindMultiplicationQueueToExchange() {
        return BindingBuilder
                .bind(queueMultiplication())
                .to(directMathExchange())
                .withQueueName();
    }

    /**
     * This bean create a binding between the DIVIDE_QUEUE_NAME queue and the MATH_EXCHANGE exchange
     * The routing key is the same as the queue name
     */
    @Bean
    Binding bindDivideQueueToExchange() {
        return BindingBuilder
                .bind(queueDivide())
                .to(directMathExchange())
                .withQueueName();
    }
}
package com.example.rabbit.rabbit_demo.service;

import com.example.rabbit.rabbit_demo.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderPublisherService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${mq.order.fanout.exchange}")
    private String fanoutExchange;

    public void sendOrderToAll(OrderDTO orderDTO){
        rabbitTemplate.convertAndSend(fanoutExchange, "", orderDTO);
    }
}
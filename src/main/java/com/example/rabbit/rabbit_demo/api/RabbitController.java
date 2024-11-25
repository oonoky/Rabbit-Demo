package com.example.rabbit.rabbit_demo.api;

import com.example.rabbit.rabbit_demo.dto.OrderDTO;
import com.example.rabbit.rabbit_demo.service.OrderPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class RabbitController {

    private final OrderPublisherService orderPublisher;

    @PostMapping("/{region}")
    public ResponseEntity<String> createOrder(@PathVariable String region, @RequestBody OrderDTO orderDTO) {
        // Установка региона в объект DTO
        orderDTO.setRegion(region);

        // Отправка заказа
        orderPublisher.sendOrder(orderDTO);

        // Возвращение ответа с указанием региона
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Order sent to region: " + region);
    }
}

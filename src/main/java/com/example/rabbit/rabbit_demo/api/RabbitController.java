package com.example.rabbit.rabbit_demo.api;

import com.example.rabbit.rabbit_demo.dto.Message;
import com.example.rabbit.rabbit_demo.dto.OrderDTO;
import com.example.rabbit.rabbit_demo.service.MessageSender;
import com.example.rabbit.rabbit_demo.service.OrderPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rabbit")
@RequiredArgsConstructor
public class RabbitController {

    private final MessageSender messageSender;
    private final OrderPublisherService orderPublisher;

    @PostMapping(value = "/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message){
        try{
            messageSender.sendMessage(message);
            return new ResponseEntity<>("Message send successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Failed send message", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/send-to-all")
    public ResponseEntity<String> sendNotification(@RequestBody OrderDTO orderDTO){
        try{
            orderPublisher.sendOrderToAll(orderDTO);
            return new ResponseEntity<>("Order sent Successfully!", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to send to all", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/send-message/{departmentName}")
    public ResponseEntity<String> sendMessage(@PathVariable(name = "departmentName") String departmentName,
                                              @RequestBody Message message){
        try{
            messageSender.sendData(message, departmentName);
            return new ResponseEntity<>("Message Sent Successfully!", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to send message to topic", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
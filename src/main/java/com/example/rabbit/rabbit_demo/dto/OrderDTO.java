package com.example.rabbit.rabbit_demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {

    private String restaurant;
    private String courier;
    private List<String> foods;
    private String region;
    private String status;
}
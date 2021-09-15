package com.example.springboot_rabbitmq.config;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.io.Serializable;

@AllArgsConstructor
@Data
public class User  implements Serializable {
    private Integer id;

    private String name;
}

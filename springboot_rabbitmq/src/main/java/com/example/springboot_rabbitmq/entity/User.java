package com.example.springboot_rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.io.Serializable;

@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
public class User  implements Serializable {
    private Integer id;

    private String name;
}

package com.train.train.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String from;
    private String to;
    private String user;
    private String email;
    private double price;
    private String seat;
    private String section;
}

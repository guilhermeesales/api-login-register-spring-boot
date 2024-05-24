package com.guilhermesales.apiloginregistro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultError {
    private int code;
    private String message;
    
}

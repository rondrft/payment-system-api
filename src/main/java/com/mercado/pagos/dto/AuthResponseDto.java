package com.mercado.pagos.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class AuthResponseDto {

    private String name;
    private String email;
    private String token;
    private BigDecimal balance;

}

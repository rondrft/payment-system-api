package com.mercado.pagos.dto;

import com.mercado.pagos.model.TransactionStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TransferResponseDto {

    private UUID transactionId;
    private String senderName;
    private String receiverName;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime createdAt;

}

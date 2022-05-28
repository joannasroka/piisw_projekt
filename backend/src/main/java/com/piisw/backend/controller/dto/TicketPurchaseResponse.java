package com.piisw.backend.controller.dto;

import com.piisw.backend.entity.ticket.TicketPrice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TicketPurchaseResponse {
    @Schema(required = true)
    private Long id;

    @Schema(required = true)
    private TicketResponse ticketResponse;

    @Schema(required = true)
    private TicketPrice price;

    @Schema(required = true)
    private LocalDateTime dateOfPurchase;

    @Schema(required = true)
    private boolean isValid;

    private LocalDateTime dateTimeOfValidation;

    private LocalDateTime validityStartDateTime;
}

package com.piisw.backend.controller.dto.ticket_purchase;

import com.piisw.backend.controller.dto.ticket.TicketResponse;
import com.piisw.backend.entity.ticket.TicketPrice;
import com.piisw.backend.entity.ticket_purchase.TicketPurchaseStatus;
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
    private TicketResponse ticket;

    @Schema(required = true)
    private TicketPrice price;

    @Schema(required = true)
    private LocalDateTime dateOfPurchase;

    @Schema(required = true)
    private TicketPurchaseStatus ticketPurchaseStatus;

    private LocalDateTime dateTimeOfValidation;

    private LocalDateTime validityStartDateTime;
}

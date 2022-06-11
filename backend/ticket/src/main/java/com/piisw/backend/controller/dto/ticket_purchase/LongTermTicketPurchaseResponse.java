package com.piisw.backend.controller.dto.ticket_purchase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LongTermTicketPurchaseResponse extends TicketPurchaseResponse {
    private LocalDateTime validityStartDateTime;

    private LocalDateTime validityEndDateTime;
}

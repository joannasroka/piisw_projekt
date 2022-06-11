package com.piisw.backend.controller.dto.ticket_purchase;

import com.piisw.backend.entity.ticket.TicketPrice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketPurchaseRequest {
    @NotNull(message = "error.cannotBeBlank")
    @Schema(required = true)
    private Long ticketId;

    @NotNull(message = "error.cannotBeBlank")
    @Schema(required = true)
    private TicketPrice ticketPrice;

    private LocalDate validityStartDate;
}
package com.piisw.backend.controller.dto.ticket;

import com.piisw.backend.entity.ticket.TicketType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TicketResponse {
    @Schema(required = true)
    private Long id;

    @Schema(required = true)
    private BigDecimal normalPrice;

    @Schema(required = true)
    private BigDecimal reducedPrice;

    @Schema(required = true)
    private String name;

    @Schema(required = true)
    private TicketType ticketType;
}

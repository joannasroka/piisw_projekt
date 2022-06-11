package com.piisw.backend.mapper;

import com.piisw.backend.configuration.mapper.MapStructConfig;
import com.piisw.backend.controller.dto.ticket.LongTimeTicketResponse;
import com.piisw.backend.controller.dto.ticket.ShortTimeTicketResponse;
import com.piisw.backend.controller.dto.ticket.TicketResponse;
import com.piisw.backend.entity.ticket.LongTermTicket;
import com.piisw.backend.entity.ticket.ShortTermTicket;
import com.piisw.backend.entity.ticket.SingleTicket;
import com.piisw.backend.entity.ticket.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(config = MapStructConfig.class)
public interface TicketMapper {
    String TICKET_TO_TICKET_RESPONSE = "TicketToTicketResponse";

    @Named(TICKET_TO_TICKET_RESPONSE)
    TicketResponse mapToTicketResponse(Ticket ticket);

    @Named(TICKET_TO_TICKET_RESPONSE)
    TicketResponse mapToTicketResponse(SingleTicket ticket);

    @Named(TICKET_TO_TICKET_RESPONSE)
    ShortTimeTicketResponse mapToTicketResponse(ShortTermTicket ticket);

    @Named(TICKET_TO_TICKET_RESPONSE)
    LongTimeTicketResponse mapToTicketResponse(LongTermTicket ticket);
}

package com.piisw.backend.mapper;

import com.piisw.backend.configuration.mapper.MapStructConfig;
import com.piisw.backend.controller.dto.TicketResponse;
import com.piisw.backend.entity.ticket.SingleTicket;
import com.piisw.backend.entity.ticket.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(config = MapStructConfig.class)
public interface TicketMapper {
    public static final String TICKET_TO_TICKET_RESPONSE = "TicketToTicketResponse";

    @Named(TICKET_TO_TICKET_RESPONSE)
    public TicketResponse mapToTicketResponse(Ticket ticket);

    public TicketResponse mapToTicketResponse(SingleTicket ticket);
}

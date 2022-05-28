package com.piisw.backend.mapper;

import com.piisw.backend.configuration.mapper.MapStructConfig;
import com.piisw.backend.controller.dto.TicketResponse;
import com.piisw.backend.entity.ticket.Ticket;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface TicketMapper {

    TicketResponse mapToTicketResponse(Ticket ticket);
}

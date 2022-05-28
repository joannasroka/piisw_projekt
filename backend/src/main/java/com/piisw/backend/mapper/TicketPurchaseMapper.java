package com.piisw.backend.mapper;

import com.piisw.backend.configuration.mapper.MapStructConfig;
import com.piisw.backend.controller.dto.TicketPurchaseResponse;
import com.piisw.backend.entity.ticket_purchase.TicketPurchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.piisw.backend.mapper.TicketMapper.TICKET_TO_TICKET_RESPONSE;

@Mapper(config = MapStructConfig.class, uses = TicketMapper.class)
public interface TicketPurchaseMapper {

    @Mapping(target = "ticketResponse", source = "ticket", qualifiedByName = TICKET_TO_TICKET_RESPONSE)
    public TicketPurchaseResponse mapToTicketPurchaseResponse(TicketPurchase ticketPurchase);
}

package com.piisw.backend.mapper;

import com.piisw.backend.configuration.mapper.MapStructConfig;
import com.piisw.backend.controller.dto.ticket_purchase.LongTermTicketPurchaseResponse;
import com.piisw.backend.controller.dto.ticket_purchase.SingleShortTermTicketPurchaseResponse;
import com.piisw.backend.entity.ticket_purchase.LongTermTicketPurchase;
import com.piisw.backend.entity.ticket_purchase.ShortTermTicketPurchase;
import com.piisw.backend.entity.ticket_purchase.SingleTicketPurchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.piisw.backend.mapper.TicketMapper.TICKET_TO_TICKET_RESPONSE;

@Mapper(config = MapStructConfig.class, uses = TicketMapper.class)
public interface TicketPurchaseMapper {

    @Mapping(target = "ticket", source = "ticket", qualifiedByName = TICKET_TO_TICKET_RESPONSE)
    SingleShortTermTicketPurchaseResponse mapToTicketPurchaseResponse(SingleTicketPurchase ticketPurchase);

    @Mapping(target = "ticket", source = "ticket", qualifiedByName = TICKET_TO_TICKET_RESPONSE)
    SingleShortTermTicketPurchaseResponse mapToTicketPurchaseResponse(ShortTermTicketPurchase ticketPurchase);

    @Mapping(target = "ticket", source = "ticket", qualifiedByName = TICKET_TO_TICKET_RESPONSE)
    LongTermTicketPurchaseResponse mapToTicketPurchaseResponse(LongTermTicketPurchase ticketPurchase);
}

package com.piisw.backend.service.ticket_purchase;

import com.piisw.backend.controller.dto.TicketPurchaseRequest;
import com.piisw.backend.controller.dto.TicketPurchaseResponse;
import com.piisw.backend.entity.ticket.Ticket;
import com.piisw.backend.entity.ticket.TicketPrice;
import com.piisw.backend.entity.ticket_purchase.LongTermTicketPurchase;
import com.piisw.backend.entity.ticket_purchase.ShortTermTicketPurchase;
import com.piisw.backend.entity.ticket_purchase.SingleTicketPurchase;
import com.piisw.backend.entity.ticket_purchase.TicketPurchase;
import com.piisw.backend.entity.user.Passenger;
import com.piisw.backend.mapper.TicketPurchaseMapper;
import com.piisw.backend.repository.PassengerRepository;
import com.piisw.backend.repository.TicketPurchaseRepository;
import com.piisw.backend.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketPurchaseService {
    private final TicketPurchaseRepository ticketPurchaseRepository;
    private final TicketRepository ticketRepository;
    private final PassengerRepository passengerRepository;
    private final TicketPurchaseMapper ticketPurchaseMapper;

    public TicketPurchaseResponse purchaseTicket(TicketPurchaseRequest ticketPurchaseRequest) {
        Passenger passenger = passengerRepository.getById(ticketPurchaseRequest.getPassengerId());
        Ticket ticket = ticketRepository.getById(ticketPurchaseRequest.getTicketId());
        TicketPrice ticketPrice = ticketPurchaseRequest.getTicketPrice();

        TicketPurchase ticketPurchase;

        switch (ticket.getTicketType()) {
            case SINGLE -> ticketPurchase = new SingleTicketPurchase(passenger, ticket, ticketPrice);
            case SHORT_TERM -> ticketPurchase = new ShortTermTicketPurchase(passenger, ticket, ticketPrice);
            case LONG_TERM -> ticketPurchase = new LongTermTicketPurchase(passenger, ticket, ticketPrice);
            default -> throw new IllegalStateException("Unexpected value: " + ticket.getTicketType());
        }

        ticketPurchase = ticketPurchaseRepository.save(ticketPurchase);

        return ticketPurchaseMapper.mapToTicketPurchaseResponse(ticketPurchase);
    }
}

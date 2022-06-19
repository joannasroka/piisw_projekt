package com.piisw.backend.service.ticket_purchase;

import com.piisw.backend.controller.dto.ticket_purchase.TicketPurchaseRequest;
import com.piisw.backend.controller.dto.ticket_purchase.TicketPurchaseResponse;
import com.piisw.backend.entity.ticket.Ticket;
import com.piisw.backend.entity.ticket.TicketPrice;
import com.piisw.backend.entity.ticket_purchase.LongTermTicketPurchase;
import com.piisw.backend.entity.ticket_purchase.ShortTermTicketPurchase;
import com.piisw.backend.entity.ticket_purchase.SingleTicketPurchase;
import com.piisw.backend.entity.ticket_purchase.TicketPurchase;
import com.piisw.backend.entity.user.Passenger;
import com.piisw.backend.exception.InvalidLongTermTicketPurchaseException;
import com.piisw.backend.exception.ValidityStartDatePassedException;
import com.piisw.backend.mapper.TicketPurchaseMapper;
import com.piisw.backend.repository.PassengerRepository;
import com.piisw.backend.repository.TicketPurchaseRepository;
import com.piisw.backend.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketPurchaseService {
    private final TicketPurchaseRepository ticketPurchaseRepository;
    private final TicketRepository ticketRepository;
    private final PassengerRepository passengerRepository;
    private final TicketPurchaseMapper ticketPurchaseMapper;

    public TicketPurchaseResponse purchaseTicket(TicketPurchaseRequest ticketPurchaseRequest,
                                                 Long currentPassengerId) {
        Passenger passenger = passengerRepository.getById(currentPassengerId);
        Ticket ticket = ticketRepository.findByGlobalById(ticketPurchaseRequest.getGlobalId());
        TicketPrice ticketPrice = ticketPurchaseRequest.getTicketPrice();

        TicketPurchase ticketPurchase;

        switch (ticket.getTicketType()) {
            case SINGLE -> ticketPurchase = new SingleTicketPurchase(passenger, ticket, ticketPrice);
            case SHORT_TERM -> ticketPurchase = new ShortTermTicketPurchase(passenger, ticket, ticketPrice);
            case LONG_TERM -> {
                validatePurchaseOfLongTermTicket(ticketPurchaseRequest);
                ticketPurchase = new LongTermTicketPurchase(passenger, ticket,
                        ticketPrice, ticketPurchaseRequest.getValidityStartDate().atStartOfDay());
            }
            default -> throw new IllegalStateException("Unexpected value: " + ticket.getTicketType());
        }

        ticketPurchase = ticketPurchaseRepository.save(ticketPurchase);

        return ticketPurchaseMapper.mapToTicketPurchaseResponse(ticketPurchase);
    }

    @Transactional(readOnly = true)
    public List<TicketPurchaseResponse> getAllMyTickets(Long currentPassengerId) {
        Passenger passenger = passengerRepository.getById(currentPassengerId);
        List<TicketPurchase> ticketPurchases = ticketPurchaseRepository.findAllByPassenger(passenger);

        return ticketPurchases.stream()
                .map(ticketPurchaseMapper::mapToTicketPurchaseResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TicketPurchaseResponse getTicketPurchaseByGlobalId(UUID ticketPurchaseGlobalId) {
        TicketPurchase ticketPurchase = ticketPurchaseRepository.findByGlobalById(ticketPurchaseGlobalId);
        return ticketPurchaseMapper.mapToTicketPurchaseResponse(ticketPurchase);
    }

    private void validatePurchaseOfLongTermTicket(TicketPurchaseRequest ticketPurchaseRequest) {
        if (ticketPurchaseRequest.getValidityStartDate() == null)
            throw new InvalidLongTermTicketPurchaseException();
        if (ticketPurchaseRequest.getValidityStartDate().isBefore(LocalDate.now()))
            throw new ValidityStartDatePassedException();
    }
}

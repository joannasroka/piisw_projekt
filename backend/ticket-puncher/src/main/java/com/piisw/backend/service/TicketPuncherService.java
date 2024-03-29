package com.piisw.backend.service;

import com.piisw.backend.controller.dto.ticket_purchase.TicketPurchaseResponse;
import com.piisw.backend.entity.ticket.ShortTermTicket;
import com.piisw.backend.entity.ticket_purchase.*;
import com.piisw.backend.entity.user.Passenger;
import com.piisw.backend.exception.InvalidTicketCannotBePunchedException;
import com.piisw.backend.exception.LongTermTicketCannotBePunchedException;
import com.piisw.backend.exception.TicketIsAlreadyActiveException;
import com.piisw.backend.mapper.TicketPurchaseMapper;
import com.piisw.backend.repository.PassengerRepository;
import com.piisw.backend.repository.TicketPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class TicketPuncherService {
    private final TicketPurchaseRepository ticketPurchaseRepository;
    private final PassengerRepository passengerRepository;
    private final TicketPurchaseMapper ticketPurchaseMapper;

    public TicketPurchaseResponse punchPurchasedTicket(UUID ticketPurchaseGlobalId,
                                                       Long currentPassengerId) {
        Passenger passenger = passengerRepository.getById(currentPassengerId);
        TicketPurchase ticketPurchase = ticketPurchaseRepository
                .findByGlobalIdAndPassenger(ticketPurchaseGlobalId, passenger);

        validateTicketPunching(ticketPurchase);
        punchTicket(ticketPurchase);

        return ticketPurchaseMapper.mapToTicketPurchaseResponse(ticketPurchase);
    }

    private void validateTicketPunching(TicketPurchase ticketPurchase) {
        if (ticketPurchase.getTicketPurchaseStatus().equals(TicketPurchaseStatus.INVALID)) {
            throw new InvalidTicketCannotBePunchedException();
        }

        if (ticketPurchase.getTicketPurchaseStatus().equals(TicketPurchaseStatus.ACTIVE)) {
            throw new TicketIsAlreadyActiveException();
        }

        if (ticketPurchase instanceof LongTermTicketPurchase) {
            throw new LongTermTicketCannotBePunchedException();
        }
    }

    private void punchTicket(TicketPurchase ticketPurchase) {
        if (ticketPurchase instanceof SingleTicketPurchase) {
            ((SingleTicketPurchase) ticketPurchase).setDateTimeOfValidation(LocalDateTime.now());
        }

        if (ticketPurchase instanceof ShortTermTicketPurchase) {
            LocalDateTime dateTimeOfValidation = LocalDateTime.now();
            ShortTermTicket ticket = (ShortTermTicket) ticketPurchase.getTicket();
            ((ShortTermTicketPurchase) ticketPurchase).setDateTimeOfValidation(dateTimeOfValidation);
            ((ShortTermTicketPurchase) ticketPurchase).setValidityEndDateTime(dateTimeOfValidation.plusHours(ticket.getHours()).plusMinutes(ticket.getMinutes()));
        }
    }
}

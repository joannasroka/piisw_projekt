package com.piisw.backend.service.ticket;

import com.piisw.backend.controller.dto.ticket.TicketResponse;
import com.piisw.backend.entity.ticket.LongTermTicket;
import com.piisw.backend.entity.ticket.ShortTermTicket;
import com.piisw.backend.entity.ticket.SingleTicket;
import com.piisw.backend.entity.ticket.Ticket;
import com.piisw.backend.mapper.TicketMapper;
import com.piisw.backend.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public List<TicketResponse> getAll() {
        return ticketRepository.findAll().stream()
                .map(this::mapToTicketResponse)
                .collect(Collectors.toList());
    }

    public TicketResponse getByGlobalId(UUID ticketGlobalId) {
        Ticket ticket = ticketRepository.findByGlobalById(ticketGlobalId);
        return mapToTicketResponse(ticket);
    }

    private TicketResponse mapToTicketResponse(Ticket ticket) {
        if (ticket instanceof SingleTicket) {
            return ticketMapper.mapToTicketResponse((SingleTicket) ticket);
        }
        if (ticket instanceof ShortTermTicket) {
            return ticketMapper.mapToTicketResponse((ShortTermTicket) ticket);
        }
        if (ticket instanceof LongTermTicket) {
            return ticketMapper.mapToTicketResponse((LongTermTicket) ticket);
        }
        return null;
    }
}

package com.piisw.backend.service.ticket;

import com.piisw.backend.controller.dto.TicketResponse;
import com.piisw.backend.entity.ticket.Ticket;
import com.piisw.backend.mapper.TicketMapper;
import com.piisw.backend.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public TicketResponse getById(Long ticketId) {
        return ticketMapper.mapToTicketResponse(ticketRepository.getById(ticketId));
    }
}

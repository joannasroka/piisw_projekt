package com.piisw.backend.controller.ticket;

import com.piisw.backend.controller.BaseController;
import com.piisw.backend.controller.dto.ticket.TicketResponse;
import com.piisw.backend.service.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.piisw.backend.security.AuthorizationConstants.HAS_ANY_USER_ROLE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/tickets")
public class TicketController extends BaseController {
    private final TicketService ticketService;

    @PreAuthorize(HAS_ANY_USER_ROLE)
    @GetMapping
    public List<TicketResponse> getAllTickets() {
        return ticketService.getAll();
    }

    @PreAuthorize(HAS_ANY_USER_ROLE)
    @GetMapping("/{ticketGlobalId}")
    public TicketResponse getTicketById(@PathVariable UUID ticketGlobalId) {
        return ticketService.getByGlobalId(ticketGlobalId);
    }
}

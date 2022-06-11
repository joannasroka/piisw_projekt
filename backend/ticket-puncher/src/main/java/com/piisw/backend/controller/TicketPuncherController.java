package com.piisw.backend.controller;

import com.piisw.backend.controller.dto.ticket_purchase.TicketPurchaseResponse;
import com.piisw.backend.security.AuthenticationContextService;
import com.piisw.backend.service.TicketPuncherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.piisw.backend.security.AuthorizationConstants.HAS_ROLE_PASSENGER;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/ticket-punchers")
public class TicketPuncherController extends BaseController {
    private final TicketPuncherService ticketPuncherService;
    private final AuthenticationContextService authenticationContextService;

    @PreAuthorize(HAS_ROLE_PASSENGER)
    @PutMapping("/punch")
    public TicketPurchaseResponse punchTicket(@RequestParam Long ticketPurchaseId) {
        Long currentPassengerId = authenticationContextService.getCurrentUserId();
        return ticketPuncherService.punchPurchasedTicket(ticketPurchaseId, currentPassengerId);
    }
}

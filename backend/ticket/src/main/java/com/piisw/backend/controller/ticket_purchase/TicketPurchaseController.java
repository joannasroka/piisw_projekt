package com.piisw.backend.controller.ticket_purchase;

import com.piisw.backend.controller.BaseController;
import com.piisw.backend.controller.dto.TicketPurchaseRequest;
import com.piisw.backend.controller.dto.TicketPurchaseResponse;
import com.piisw.backend.security.AuthenticationContextService;
import com.piisw.backend.service.ticket_purchase.TicketPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.piisw.backend.security.AuthorizationConstants.HAS_ROLE_PASSENGER;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/purchase-tickets")
public class TicketPurchaseController extends BaseController {
    private final TicketPurchaseService ticketPurchaseService;
    private final AuthenticationContextService authenticationContextService;

    @PreAuthorize(HAS_ROLE_PASSENGER)
    @PostMapping
    @ResponseStatus(CREATED)
    public TicketPurchaseResponse purchaseTicket(@RequestBody @Valid TicketPurchaseRequest ticketPurchaseRequest) {
        Long currentPassengerId = authenticationContextService.getCurrentUserId();
        return ticketPurchaseService.purchaseTicket(ticketPurchaseRequest, currentPassengerId);
    }

    @PreAuthorize(HAS_ROLE_PASSENGER)
    @GetMapping
    public List<TicketPurchaseResponse> getAllMineTickets() {
        Long currentPassengerId = authenticationContextService.getCurrentUserId();
        return ticketPurchaseService.getAllMyTickets(currentPassengerId);
    }
}

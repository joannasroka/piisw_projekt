package com.piisw.backend.repository;

import com.piisw.backend.entity.ticket_purchase.TicketPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPurchaseRepository extends JpaRepository<TicketPurchase, Long> {
}

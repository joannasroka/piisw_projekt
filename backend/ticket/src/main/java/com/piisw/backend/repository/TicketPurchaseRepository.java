package com.piisw.backend.repository;

import com.piisw.backend.entity.ticket_purchase.TicketPurchase;
import com.piisw.backend.entity.user.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketPurchaseRepository extends JpaRepository<TicketPurchase, Long> {

    List<TicketPurchase> findAllByPassenger(Passenger passenger);
}

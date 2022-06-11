package com.piisw.backend.repository;

import com.piisw.backend.entity.ticket_purchase.TicketPurchase;
import com.piisw.backend.entity.user.Passenger;
import com.piisw.backend.exception.DatabaseEntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketPurchaseRepository extends JpaRepository<TicketPurchase, Long> {

    List<TicketPurchase> findAllByPassenger(Passenger passenger);

    Optional<TicketPurchase> getByIdAndPassenger(Long id, Passenger passenger);

    default TicketPurchase findByIdAndPassenger(Long id, Passenger passenger) {
        return getByIdAndPassenger(id, passenger).orElseThrow(DatabaseEntityNotFoundException::new);
    }

    default TicketPurchase getById(Long id) {
        return findById(id).orElseThrow(DatabaseEntityNotFoundException::new);
    }
}

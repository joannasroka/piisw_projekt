package com.piisw.backend.repository;

import com.piisw.backend.entity.ticket_purchase.TicketPurchase;
import com.piisw.backend.entity.user.Passenger;
import com.piisw.backend.exception.DatabaseEntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketPurchaseRepository extends JpaRepository<TicketPurchase, Long> {

    List<TicketPurchase> findAllByPassenger(Passenger passenger);

    Optional<TicketPurchase> getByGlobalIdAndPassenger(UUID globalId, Passenger passenger);

    default TicketPurchase findByGlobalIdAndPassenger(UUID globalId, Passenger passenger) {
        return getByGlobalIdAndPassenger(globalId, passenger)
                .orElseThrow(DatabaseEntityNotFoundException::new);
    }

    Optional<TicketPurchase> getByGlobalId(UUID globalId);

    default TicketPurchase findByGlobalById(UUID globalId) {
        return getByGlobalId(globalId).orElseThrow(DatabaseEntityNotFoundException::new);
    }
}

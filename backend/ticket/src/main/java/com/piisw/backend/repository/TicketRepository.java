package com.piisw.backend.repository;

import com.piisw.backend.entity.ticket.Ticket;
import com.piisw.backend.exception.DatabaseEntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> getByGlobalId(UUID globalId);

    default Ticket findByGlobalById(UUID globalId) {
        return getByGlobalId(globalId).orElseThrow(DatabaseEntityNotFoundException::new);
    }

}

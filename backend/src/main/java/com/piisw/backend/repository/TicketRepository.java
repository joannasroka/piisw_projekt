package com.piisw.backend.repository;

import com.piisw.backend.entity.ticket.Ticket;
import com.piisw.backend.exception.DatabaseEntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    default Ticket getById(Long id) {
        return findById(id).orElseThrow(DatabaseEntityNotFoundException::new);
    }

}

package com.piisw.backend.entity.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "long_term_tickets")
@DiscriminatorValue("LONG_TERM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LongTermTicket extends Ticket {
    @Column(name = "days", nullable = false)
    private int days;

    @Override
    public TicketType getType() {
        return TicketType.LONG_TERM;
    }
}

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
@Table(name = "short_term_tickets")
@DiscriminatorValue("SHORT_TERM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShortTermTicket extends Ticket {
    @Column(name = "minutes", nullable = false)
    private int minutes;

    @Column(name = "minutes", nullable = false)
    private int hours;

    @Override
    public TicketType getType() {
        return TicketType.SHORT_TERM;
    }
}
package com.piisw.backend.entity.ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "short_term_tickets")
@DiscriminatorValue("SHORT_TERM")
@Getter
@Setter
@NoArgsConstructor
public class ShortTermTicket extends Ticket {
    @Column(name = "minutes", nullable = false)
    private int minutes;

    @Column(name = "hours", nullable = false)
    private int hours;

    public ShortTermTicket(BigDecimal normalPrice, BigDecimal reducedPrice, String name, int minutes, int hours) {
        super(normalPrice, reducedPrice, name);
        this.minutes = minutes;
        this.hours = hours;
    }

    @Override
    public TicketType getTicketType() {
        return TicketType.SHORT_TERM;
    }
}
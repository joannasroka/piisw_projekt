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
@Table(name = "long_term_tickets")
@DiscriminatorValue("LONG_TERM")
@Getter
@Setter
@NoArgsConstructor
public class LongTermTicket extends Ticket {
    @Column(name = "days", nullable = false)
    private int days;

    public LongTermTicket(BigDecimal normalPrice, BigDecimal reducedPrice, String name, int days) {
        super(normalPrice, reducedPrice, name);
        this.days = days;
    }

    @Override
    public TicketType getTicketType() {
        return TicketType.LONG_TERM;
    }
}

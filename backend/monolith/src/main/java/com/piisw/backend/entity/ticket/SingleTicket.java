package com.piisw.backend.entity.ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "single_tickets")
@DiscriminatorValue("SINGLE")
@Getter
@Setter
@NoArgsConstructor
public class SingleTicket extends Ticket {

    public SingleTicket(BigDecimal normalPrice, BigDecimal reducedPrice, String name) {
        super(normalPrice, reducedPrice, name);
    }

    @Override
    public TicketType getTicketType() {
        return TicketType.SINGLE;
    }
}

package com.piisw.backend.entity.ticket;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "single_tickets")
@DiscriminatorValue("SINGLE")
@Getter
@Setter
public class SingleTicket extends Ticket {
    @Override
    public TicketType getType() {
        return TicketType.SINGLE;
    }
}

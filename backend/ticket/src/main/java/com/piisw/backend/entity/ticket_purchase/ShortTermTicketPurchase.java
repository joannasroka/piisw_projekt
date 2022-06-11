package com.piisw.backend.entity.ticket_purchase;

import com.piisw.backend.entity.ticket.ShortTermTicket;
import com.piisw.backend.entity.ticket.Ticket;
import com.piisw.backend.entity.ticket.TicketPrice;
import com.piisw.backend.entity.ticket.TicketType;
import com.piisw.backend.entity.user.Passenger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "short_term_tickets_purchase")
@DiscriminatorValue("SHORT_TERM")
@Getter
@Setter
@NoArgsConstructor
public class ShortTermTicketPurchase extends TicketPurchase {

    @Column(name = "date_time_of_validation")
    private LocalDateTime dateTimeOfValidation;

    public ShortTermTicketPurchase(Passenger passenger, Ticket ticket, TicketPrice ticketPrice) {
        super(passenger, ticket, ticketPrice, LocalDateTime.now());
    }

    @Override
    public TicketType getTicketType() {
        return TicketType.SHORT_TERM;
    }

    @Override
    public boolean isValid() {
        if (dateTimeOfValidation == null) return true;

        ShortTermTicket ticket = (ShortTermTicket) getTicket();
        LocalDateTime validityEndDateTime = dateTimeOfValidation.plusHours(ticket.getHours()).plusMinutes(ticket.getMinutes());

        return !validityEndDateTime.isBefore(LocalDateTime.now());
    }
}
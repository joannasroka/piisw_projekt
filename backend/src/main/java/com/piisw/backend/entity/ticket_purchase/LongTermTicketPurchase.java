package com.piisw.backend.entity.ticket_purchase;

import com.piisw.backend.entity.ticket.LongTermTicket;
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
@Table(name = "long_term_tickets_purchase")
@DiscriminatorValue("LONG_TERM")
@Getter
@Setter
@NoArgsConstructor
public class LongTermTicketPurchase extends TicketPurchase {
    @Column(name = "validity_start_date_time")
    private LocalDateTime validityStartDateTime;

    public LongTermTicketPurchase(Passenger passenger, Ticket ticket, TicketPrice ticketPrice) {
        super(passenger, ticket, ticketPrice, LocalDateTime.now());
    }

    @Override
    public TicketType getTicketType() {
        return TicketType.LONG_TERM;
    }

    @Override
    public boolean isValid() {
        LocalDateTime validityEndDateTime = validityStartDateTime.plusDays(((LongTermTicket) getTicket()).getDays());
        return validityStartDateTime.isAfter(LocalDateTime.now())
                || !validityEndDateTime.isBefore(LocalDateTime.now());

    }
}

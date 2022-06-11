package com.piisw.backend.entity.ticket_purchase;

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
@Table(name = "single_tickets_purchase")
@DiscriminatorValue("SINGLE")
@Getter
@Setter
@NoArgsConstructor
public class SingleTicketPurchase extends TicketPurchase {
    public static final int MAX_TRAVEL_TIME_IN_HOURS = 3;

    @Column(name = "date_time_of_validation")
    private LocalDateTime dateTimeOfValidation;

    public SingleTicketPurchase(Passenger passenger, Ticket ticket, TicketPrice ticketPrice) {
        super(passenger, ticket, ticketPrice, LocalDateTime.now());
    }

    @Override
    public TicketType getTicketType() {
        return TicketType.SINGLE;
    }

    @Override
    public TicketPurchaseStatus getTicketPurchaseStatus() {
        if (dateTimeOfValidation == null) return TicketPurchaseStatus.INACTIVE;

        LocalDateTime validityEndDateTime = dateTimeOfValidation.plusHours(MAX_TRAVEL_TIME_IN_HOURS);

        if (!validityEndDateTime.isBefore(LocalDateTime.now())) return TicketPurchaseStatus.ACTIVE;
        return TicketPurchaseStatus.INVALID;
    }
}

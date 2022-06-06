package com.piisw.backend.entity.ticket_purchase;

import com.piisw.backend.entity.BaseEntity;
import com.piisw.backend.entity.ticket.Ticket;
import com.piisw.backend.entity.ticket.TicketPrice;
import com.piisw.backend.entity.ticket.TicketType;
import com.piisw.backend.entity.user.Passenger;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets_purchase")
@DiscriminatorColumn(name = "ticket_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public abstract class TicketPurchase extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @Column(name = "price", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private TicketPrice price;

    @Column(name = "date_time_of_purchase", nullable = false)
    private LocalDateTime dateOfPurchase = LocalDateTime.now();

    public abstract TicketType getTicketType();

    public abstract boolean isValid();
}

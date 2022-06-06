package com.piisw.backend.entity.ticket;

import com.piisw.backend.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tickets")
@DiscriminatorColumn(name = "ticket_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public abstract class Ticket extends BaseEntity {
    @Column(name = "normal_price", nullable = false)
    private BigDecimal normalPrice;

    @Column(name = "reduced_price", nullable = false)
    private BigDecimal reducedPrice;

    @Column(name = "name", length = 300, unique = true)
    private String name;

    public abstract TicketType getTicketType();
}

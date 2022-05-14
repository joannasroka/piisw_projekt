package com.piisw.backend.entity.ticket;

import com.piisw.backend.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public abstract class Ticket extends BaseEntity {
    @Column(name = "normal_price", nullable = false)
    BigDecimal normalPrice;

    @Column(name = "reduced_price", nullable = false)
    BigDecimal reducedPrice;

    @Column(name = "description", length = 300)
    String description;

    public abstract TicketType getType();
}

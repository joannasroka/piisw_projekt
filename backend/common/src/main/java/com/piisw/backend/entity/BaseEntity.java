package com.piisw.backend.entity;

import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@Getter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator", sequenceName = "id_sequence", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @Type(type = "uuid-char")
    @Column(name = "global_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private UUID globalId = UUID.randomUUID();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

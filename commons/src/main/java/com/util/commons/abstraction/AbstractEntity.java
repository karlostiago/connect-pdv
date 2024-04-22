package com.util.commons.abstraction;

import com.util.commons.annotation.ExcludedCoverage;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
@ExcludedCoverage
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity abstractEntity = (AbstractEntity) o;
        return id.equals(abstractEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

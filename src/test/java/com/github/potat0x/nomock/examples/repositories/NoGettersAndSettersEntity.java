package com.github.potat0x.nomock.examples.repositories;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class NoGettersAndSettersEntity {

    @Id
    Integer id;

    public NoGettersAndSettersEntity(Integer id) {
        this.id = id;
    }

    protected NoGettersAndSettersEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NoGettersAndSettersEntity that = (NoGettersAndSettersEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.github.potat0x.nomock.examples.repositories;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class UuidExampleEntity {

    @Id
    private UUID id;

    private String name;

    public UuidExampleEntity(String name) {
        this.name = name;
    }

    protected UuidExampleEntity() {
    }

    public UUID getId() {
        return id;
    }

    public UuidExampleEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UuidExampleEntity that = (UuidExampleEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UuidExampleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

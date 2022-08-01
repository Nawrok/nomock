package com.github.potat0x.nomock;

import java.util.UUID;

public final class IdGenerators {

    public static IdGenerator<Long> longGenerator() {
        return new IdGenerator<>(1L, x -> x + 1L);
    }

    public static IdGenerator<UUID> uuidGenerator() {
        return new IdGenerator<>(UUID::randomUUID);
    }

    private IdGenerators() {
    }
}

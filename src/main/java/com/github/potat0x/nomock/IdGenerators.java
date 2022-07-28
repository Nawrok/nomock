package com.github.potat0x.nomock;

import java.util.UUID;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public final class IdGenerators {
    public static final Supplier<UUID> UUID_GENERATOR = UUID::randomUUID;
    public static final UnaryOperator<Long> LONG_GENERATOR = x -> x + 1L;

    private IdGenerators() {
    }
}

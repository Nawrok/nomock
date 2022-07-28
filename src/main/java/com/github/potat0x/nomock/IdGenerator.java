package com.github.potat0x.nomock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

final class IdGenerator<ID> {

    private final AtomicReference<ID> nextId;
    private final UnaryOperator<ID> generator;

    IdGenerator(ID startId, UnaryOperator<ID> generator) {
        this.nextId = new AtomicReference<>(startId);
        this.generator = generator;
    }

    IdGenerator(Supplier<ID> generator) {
        this.nextId = new AtomicReference<>(generator.get());
        this.generator = id -> generator.get();
    }

    ID nextId() {
        return nextId.getAndUpdate(generator);
    }

}

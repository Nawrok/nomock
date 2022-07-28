package com.github.potat0x.nomock;

class InMemoryRepositoryException extends RuntimeException {

    public InMemoryRepositoryException(Throwable cause) {
        super(cause);
    }

    public InMemoryRepositoryException(String message) {
        super(message);
    }

}

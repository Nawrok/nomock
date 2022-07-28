package com.github.potat0x.nomock.examples;

import com.github.potat0x.nomock.examples.bookapp.BookEntity;
import com.github.potat0x.nomock.examples.repositories.InMemoryJpaBookRepository;
import com.github.potat0x.nomock.examples.repositories.InMemoryUuidExampleEntityRepository;
import com.github.potat0x.nomock.examples.repositories.JpaBookRepository;
import com.github.potat0x.nomock.examples.repositories.UuidExampleEntity;
import com.github.potat0x.nomock.examples.repositories.UuidExampleEntityRepository;

public final class InMemoryRepositoriesExamples {
    /*
    This example demonstrates how to create in-memory implementations for your JPA repositories
     */

    public static void main(String[] args) {
        example1();
        System.out.println();
        example2();
    }

    private static void example1() {
        System.out.println("Example 1: entity with Long identifier");
        // 1. Create in-memory repository class
        // check out JpaBookRepository.java and InMemoryJpaBookRepository.java

        // 2. Create in-memory repository instance
        JpaBookRepository repository = new InMemoryJpaBookRepository();

        // 3. Use it
        BookEntity book1 = repository.save(new BookEntity("First book"));
        System.out.println(repository.findById(book1.getId()));

        BookEntity book2 = repository.save(new BookEntity("Second book"));
        System.out.println(repository.findById(book2.getId()));

        book2.setName("Second book updated");
        repository.save(book2);
        System.out.println(repository.findById(book2.getId()));
    }

    private static void example2() {
        System.out.println("Example 2: entity with UUID identifier");
        // 1. Create in-memory repository class
        // check out UuidExampleEntityRepository.java and InMemoryUuidExampleEntityRepository.java

        // 2. Create in-memory repository instance
        UuidExampleEntityRepository repository = new InMemoryUuidExampleEntityRepository();

        // 3. Use it
        UuidExampleEntity entity1 = repository.save(new UuidExampleEntity("First entity"));
        System.out.println(repository.findById(entity1.getId()));

        UuidExampleEntity entity2 = repository.save(new UuidExampleEntity("Second entity"));
        System.out.println(repository.findById(entity2.getId()));

        entity2.setName("Second entity updated");
        repository.save(entity2);
        System.out.println(repository.findById(entity2.getId()));
    }
}

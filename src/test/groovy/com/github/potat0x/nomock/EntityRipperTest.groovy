package com.github.potat0x.nomock

import com.github.potat0x.nomock.examples.bookapp.BookEntity
import com.github.potat0x.nomock.examples.repositories.NoGettersAndSettersEntity
import spock.lang.Shared
import spock.lang.Specification

class EntityRipperTest extends Specification {

    @Shared
    def entityRipper = new EntityRipper<>()

    def "Should detect if object has specified @Id annotated field"() {
        expect:
            entityRipper.getEntityId(object) == expectedResult

        where:
            object                              | expectedResult
            "test"                              | Optional.empty()
            new BookEntity(null)                | Optional.empty()
            new BookEntity(123L)                | Optional.of(123L)
            new NoGettersAndSettersEntity(null) | Optional.empty()
            new NoGettersAndSettersEntity(123)  | Optional.of(123)
    }

    def "Should set value to @Id annotated field"() {
        when:
            entityRipper.setEntityId(entity, newId)

        then:
            entity == expectedEntity

        where:
            entity                              | newId | expectedEntity
            new BookEntity(null)                | 123L  | new BookEntity(123L)
            new BookEntity(99)                  | 22L   | new BookEntity(22L)
            new NoGettersAndSettersEntity(null) | 123   | new NoGettersAndSettersEntity(123)
            new NoGettersAndSettersEntity(99)   | 22    | new NoGettersAndSettersEntity(22)
    }

    def "Should throw custom exception while attempting to assign id to object with no @Id annotated field"() {
        when:
            entityRipper.setEntityId("this is not entity", 123L)

        then:
            thrown InMemoryRepositoryException
    }

}

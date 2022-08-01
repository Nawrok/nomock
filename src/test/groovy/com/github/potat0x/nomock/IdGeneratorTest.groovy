package com.github.potat0x.nomock

import spock.lang.Specification

class IdGeneratorTest extends Specification {

    def "Should generate Long identifiers"() {
        given:
            def idGenerator = IdGenerators.longGenerator()

        expect:
            idGenerator.nextId() == 1L
            idGenerator.nextId() == 2L
            idGenerator.nextId() == 3L
    }

    def "Should generate UUID identifiers"() {
        given:
            def idGenerator = IdGenerators.uuidGenerator()

        expect:
            [idGenerator.nextId(), idGenerator.nextId(), idGenerator.nextId()].size() == 3
    }

}

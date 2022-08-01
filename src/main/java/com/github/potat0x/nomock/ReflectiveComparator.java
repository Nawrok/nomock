package com.github.potat0x.nomock;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.NullHandling;

import java.util.List;

final class ReflectiveComparator {
    private ReflectiveComparator() {
    }

    public static int compareObjectsByMultipleFields(Object object1, Object object2, List<Sort.Order> orders) {
        int cmp = 0;
        for (Sort.Order order : orders) {
            Object obj1Field = EntityRipper.getFieldValue(object1, order.getProperty());
            Object obj2Field = EntityRipper.getFieldValue(object2, order.getProperty());

            if (obj1Field == null && obj2Field == null) {
                continue;
            } else if (obj1Field == null || obj2Field == null) {
                return compareFieldsIfOneIsNull(order, obj1Field, obj2Field);
            }

            boolean isFieldString = EntityRipper.checkIfFieldIsString(object2, order.getProperty());
            cmp = compareNonNullFields(order, obj1Field, obj2Field, isFieldString);

            if (cmp != 0) {
                return cmp;
            }
        }
        return cmp;
    }

    private static Integer compareFieldsIfOneIsNull(Sort.Order order, Object obj1Field, Object obj2Field) {
        assertThatExactlyOneObjectIsNull(obj1Field, obj2Field);

        final int nullOrdering = getNullOrdering(order);

        int cmp = 1;
        if (obj1Field == null) {
            cmp = order.isAscending() ? -1 : 1;
        }

        if (obj2Field == null) {
            cmp = order.isAscending() ? 1 : -1;
        }
        return cmp * nullOrdering;
    }

    @SuppressWarnings("unchecked")
    private static int compareNonNullFields(Sort.Order order, Object obj1Field, Object obj2Field,
                                            boolean comparedObjectsAreStringType) {
        assertNotNull(obj1Field, obj2Field);

        int cmp;
        if (comparedObjectsAreStringType && order.isIgnoreCase()) {
            if (order.isAscending()) {
                cmp = ((String) obj1Field).compareToIgnoreCase((String) obj2Field);
            } else {
                cmp = ((String) obj2Field).compareToIgnoreCase((String) obj1Field);
            }
        } else {
            if (order.isAscending()) {
                cmp = ((Comparable<Object>) obj1Field).compareTo(obj2Field);
            } else {
                cmp = ((Comparable<Object>) obj2Field).compareTo(obj1Field);
            }
        }
        return cmp;
    }

    private static int getNullOrdering(Sort.Order order) {
        NullHandling nullHandling = order.isAscending() ? NullHandling.NULLS_LAST : NullHandling.NULLS_FIRST;
        return order.getNullHandling() == nullHandling ? -1 : 1;
    }

    private static void assertNotNull(Object object1, Object object2) {
        if (object1 == null || object2 == null) {
            throw new InMemoryRepositoryException("Both objects should not be null");
        }
    }

    private static void assertThatExactlyOneObjectIsNull(Object object1, Object object2) {
        if ((object1 == null && object2 == null) || (object1 != null && object2 != null)) {
            throw new InMemoryRepositoryException("Exactly one object should be null");
        }
    }
}

package com.github.potat0x.nomock;

import org.springframework.util.ReflectionUtils;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

final class EntityRipper<T, ID> {

    public void setEntityId(T entity, ID id) {
        Field field = getFieldAnnotatedAsId(entity)
                .orElseThrow(() -> new InMemoryRepositoryException("@Id field not found"));
        ReflectionUtils.setField(field, entity, id);
    }

    @SuppressWarnings("unchecked")
    public Optional<ID> getEntityId(T entity) {
        return getFieldAnnotatedAsId(entity)
                .map(field -> (ID) ReflectionUtils.getField(field, entity));
    }

    static Object getFieldValue(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            return ReflectionUtils.getField(getAccessibleField(field), object);
        } catch (Exception e) {
            throw new InMemoryRepositoryException(e);
        }
    }

    static boolean checkIfFieldIsString(Object object, String fieldName) {
        try {
            return object.getClass().getDeclaredField(fieldName).getType().equals(String.class);
        } catch (Exception e) {
            throw new InMemoryRepositoryException(e);
        }
    }

    private Optional<Field> getFieldAnnotatedAsId(Object entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .map(EntityRipper::getAccessibleField)
                .findFirst();
    }

    private static Field getAccessibleField(Field field) {
        field.setAccessible(true);
        return field;
    }

}

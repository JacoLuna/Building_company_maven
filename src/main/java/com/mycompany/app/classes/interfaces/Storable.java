package com.mycompany.app.classes.interfaces;

public interface Storable<T> {
    void save(T object, T[] objectArray);
    void delete(T object, T[] objectArray);
}

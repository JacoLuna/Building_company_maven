package com.mycompany.app.classes.interfaces;

public interface JsonStorable<T> {
    T readJsonFile(String path);
}

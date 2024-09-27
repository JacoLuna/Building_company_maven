package com.mycompany.app.classes.interfaces;

import com.mycompany.app.classes.People.Person;

import java.util.List;

public interface IHasFilter {
    <T>List<T> filterPerson(String attribute, Object value);
}

package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findAll(String stringFilter);
    long count();
    void delete(Contact contact);
    void save(Contact contact);
    void populateTestData();
}

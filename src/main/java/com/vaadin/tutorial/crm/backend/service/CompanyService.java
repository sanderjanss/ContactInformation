package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Company;

import java.util.List;
import java.util.Map;

public interface CompanyService {

    List<Company> findAll();
    Map<String, Integer> getStats();
}

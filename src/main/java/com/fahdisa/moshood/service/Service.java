package com.fahdisa.moshood.service;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class Service {

    @PersistenceContext
    private EntityManager entityManager;

}

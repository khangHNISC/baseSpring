package com.example.flyway.core;

import com.example.flyway.reconcile.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional("coreTM")
public class CustomCupRepo {

    @PersistenceContext(unitName = "coreEMF")
    private EntityManager entityManager;

    public void saveCustomer() {
        entityManager.persist(new Customer("khang", "23"));
    }
}

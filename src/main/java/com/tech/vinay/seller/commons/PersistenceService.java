package com.tech.vinay.seller.commons;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class PersistenceService {

    private final EntityManager entityManager;

    // Constructor Injection
    @Autowired
    public PersistenceService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Method to save an entity
    public <T> T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    // Method to find an entity by ID
    public <T, ID> T findById(Class<T> entityClass, ID id) {
        return entityManager.find(entityClass, id);
    }

    // Method to delete an entity
    public <T>  void delete(T entity){
         entityManager.remove(entity);
    }

}

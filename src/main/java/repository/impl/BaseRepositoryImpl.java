/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.impl;

/**
 *
 * @author emerik
 */
import javax.persistence.EntityManager;

/**
 * Base class to derive when implementing a repository
 */
public abstract class BaseRepositoryImpl {

    protected final EntityManager entityManager;

    /**
     * Build a base repository
     * @param entityManager the entity manager
     */
    protected BaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

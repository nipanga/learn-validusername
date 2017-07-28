package com.learn.springboot.validusername.services;

/**
 * Base Service Interface.
 * 
 * Provides all basic methods for the concrete Services to implement.
 * 
 * @author felipe
 *
 */
public interface BusinessService<MODEL, DTO> {

    /**
     * Persists the {@code model} on the database.
     * 
     * @param model
     *            the object to be persisted on the database
     */
    MODEL save(final MODEL model);
}

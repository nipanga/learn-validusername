package com.learn.springboot.validusername.validators;

import com.learn.springboot.validusername.exceptions.UserValidationException;

/**
 * The contract that every Model Validator should obey
 * 
 * @author felipe
 *
 */
public interface Validator<T> {

    /**
     * Validates the given {@code obj}
     * 
     * @param obj
     *            the Object to be validated
     */
    void validate(final T obj) throws UserValidationException;
}

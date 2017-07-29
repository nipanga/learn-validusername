package com.learn.springboot.validusername.validators;

import java.util.Collection;

import com.learn.springboot.validusername.exceptions.UserValidationException;

/**
 * Validates a object according to the injected {@link Validator}s
 * 
 * @author felipe
 *
 * @param <T>
 *            the String to be validated
 */
public class AbstractBaseValidator<T> implements Validator<T> {

    private Collection<Validator<T>> validators;


    /**
     * Validates the {@code obj}
     */
    public void validate(T obj) throws UserValidationException {
	for (Validator<T> v : getValidators()) {
	    v.validate(obj);
	}
    }


    protected Collection<Validator<T>> getValidators() {
	return validators;
    }


    public void setValidators(Collection<Validator<T>> validators) {
	this.validators = validators;
    }
}

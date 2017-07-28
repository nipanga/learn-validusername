package com.learn.springboot.validusername.validators.impl;

import org.apache.commons.lang3.StringUtils;

import com.learn.springboot.validusername.exceptions.UserValidationException;
import com.learn.springboot.validusername.exceptions.UsernameLengthException;
import com.learn.springboot.validusername.models.UserModel;
import com.learn.springboot.validusername.validators.Validator;

/**
 * Validates a {@link UserModel}
 * 
 * @author felipe
 *
 */
public class UsernameValidator implements Validator<String> {

    private final int MIN_CHAR_LENGTH = 6;


    /**
     * Validates the given {@code username}
     */
    public void validate(String username) throws UserValidationException {
	if (username == null) {
	    throw new IllegalArgumentException("User cannot be null");
	}
	validateEmptyAttributes(username);
	validateUsernameLength(username);
    }


    /**
     * Validate if the given username has no null properties
     * 
     * @param username
     *            the object to be validates
     */
    private void validateEmptyAttributes(String username) throws UserValidationException {
	if (StringUtils.isBlank(username)) {
	    throw new UsernameLengthException("username cannot be empty.");
	}
    }


    /**
     * Validates if the username has at least {@link #MIN_CHAR_LENGTH}
     * 
     * @param username
     *            the object to be validates
     */
    private void validateUsernameLength(String username) throws UserValidationException {
	if (StringUtils.length(username) < 6) {
	    throw new UsernameLengthException("username must be at least 6 chars long.");
	}
    }
}

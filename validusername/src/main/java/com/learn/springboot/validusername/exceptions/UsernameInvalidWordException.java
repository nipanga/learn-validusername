package com.learn.springboot.validusername.exceptions;

/**
 * 
 * @author felipe
 *
 */
public class UsernameInvalidWordException extends UserValidationException {

    /**
     * 
     */
    public UsernameInvalidWordException() {
        super();
    }


    /**
     * 
     */
    public UsernameInvalidWordException(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * 
     */
    public UsernameInvalidWordException(String message) {
        super(message);
    }


    /**
     * 
     */
    public UsernameInvalidWordException(Throwable cause) {
        super(cause);
    }
}

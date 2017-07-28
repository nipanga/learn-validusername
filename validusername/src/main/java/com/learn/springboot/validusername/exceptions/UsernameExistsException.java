package com.learn.springboot.validusername.exceptions;

/**
 * 
 * @author felipe
 *
 */
public class UsernameExistsException extends UserValidationException {

    /**
     * 
     */
    public UsernameExistsException() {
        super();
    }


    /*
     * 
     */
    public UsernameExistsException(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * 
     * @param message
     */
    public UsernameExistsException(String message) {
        super(message);
    }


    /**
     * 
     * @param cause
     */
    public UsernameExistsException(Throwable cause) {
        super(cause);
    }
}

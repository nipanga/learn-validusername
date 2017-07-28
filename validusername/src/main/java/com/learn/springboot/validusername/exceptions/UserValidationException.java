package com.learn.springboot.validusername.exceptions;

/**
 * 
 * @author felipe
 *
 */
public class UserValidationException extends Exception {

    /**
     * 
     */
    public UserValidationException() {
        super();
    }


    /**
     * 
     * @param message
     */
    public UserValidationException(String message) {
        super(message);
    }


    /**
     * 
     * @param message
     * @param cause
     */
    public UserValidationException(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * 
     * @param cause
     */
    public UserValidationException(Throwable cause) {
        super(cause);
    }
}

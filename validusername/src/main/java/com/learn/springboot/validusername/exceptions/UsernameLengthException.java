package com.learn.springboot.validusername.exceptions;

/**
 * 
 * @author felipe
 *
 */
public class UsernameLengthException extends UserValidationException {

    /**
     * 
     */
    public UsernameLengthException() {
        super();
    }


    /**
     * 
     * @param message
     * @param cause
     */
    public UsernameLengthException(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * 
     * @param message
     */
    public UsernameLengthException(String message) {
        super(message);
    }


    /**
     * 
     * @param cause
     */
    public UsernameLengthException(Throwable cause) {
        super(cause);
    }
}

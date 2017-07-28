package com.learn.springboot.validusername.strategies;

/**
 * Defines a contract for username generation
 * 
 * @author felipe
 *
 */
public interface GenerateUsernameStrategy {

    /**
     * Generates a username
     * 
     * @param input
     *            the base input to generate a username
     * @return a new username
     */
    String generate(final String input);
}

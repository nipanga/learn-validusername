package com.learn.springboot.validusername.strategies.impl;

import com.learn.springboot.validusername.strategies.GenerateUsernameStrategy;

/**
 * Generates a new username concatenating the same username as a suffix
 * 
 * @author felipe
 *
 */
public class DuplicateUsernameGenerateUsernameStrategy implements GenerateUsernameStrategy {

    /**
     * Generates a username concatenating the same username as a suffix
     * 
     * @param input
     *            the base input to generate a username
     * @return a new username duplicated
     */
    @Override
    public String generate(String input) {
	return input + input;
    }
}

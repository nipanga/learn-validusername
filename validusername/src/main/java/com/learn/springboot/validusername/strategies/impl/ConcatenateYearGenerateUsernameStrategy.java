package com.learn.springboot.validusername.strategies.impl;

import java.util.GregorianCalendar;

import com.learn.springboot.validusername.strategies.GenerateUsernameStrategy;

/**
 * Generates a new username concatenating the currrent year as a suffix
 * 
 * @author felipe
 *
 */
public class ConcatenateYearGenerateUsernameStrategy implements GenerateUsernameStrategy {

    /**
     * Generates a username concatenating the current year as a suffix
     * 
     * @param input
     *            the base input to generate a username
     * @return a new username followed by the current year
     */
    @Override
    public String generate(String input) {
	return input
	        + String.valueOf((GregorianCalendar.getInstance().get(GregorianCalendar.YEAR)));
    }
}

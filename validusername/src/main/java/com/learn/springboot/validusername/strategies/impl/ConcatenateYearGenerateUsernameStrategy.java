package com.learn.springboot.validusername.strategies.impl;

import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;

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
        final String result = input + GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
        return StringUtils.rightPad(input, 12, input);
    }
}

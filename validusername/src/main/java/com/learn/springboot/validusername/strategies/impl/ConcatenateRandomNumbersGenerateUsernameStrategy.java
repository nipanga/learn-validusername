package com.learn.springboot.validusername.strategies.impl;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.learn.springboot.validusername.strategies.GenerateUsernameStrategy;

/**
 * Generates a new username concatenating a random number as a suffix
 * 
 * @author felipe
 *
 */
public class ConcatenateRandomNumbersGenerateUsernameStrategy implements GenerateUsernameStrategy {

    /**
     * Generates a username concatenating a random number as a suffix
     * 
     * @param input
     *            the base input to generate a username
     * @return a new username followed by a random number
     */
    @Override
    public String generate(String input) {
        String randomNumber = String.valueOf(new Random().nextInt(99999));
        final String result = (input + randomNumber);
        return StringUtils.rightPad(result, 7, result);
    }
}

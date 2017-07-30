package com.learn.springboot.validusername.strategies.impl;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.learn.springboot.validusername.strategies.GenerateUsernameStrategy;

/**
 * Generates a username concatenating a sequence of numbers
 * 
 * @author felipe
 *
 */
public class ConcatenateSequentialNumbersGenerateUsernameStrategy
        implements GenerateUsernameStrategy {

    /**
     * Generates a username concatenating a sequence of numbers, starting on 1.
     * 
     * @param input
     *            the base input to generate a username
     * @return a new username followed by a sequence os numbers
     */
    @Override
    public String generate(String input) {
        final int stopAt = Math.abs(6 - input.length());
        StringBuffer numberSequence = new StringBuffer();
        for (int i = 1; i < stopAt; i++) {
            numberSequence.append(String.valueOf(i));
        }
        final String result = (input + numberSequence.toString());
        return StringUtils.rightPad(result, 12, result);
    }
}

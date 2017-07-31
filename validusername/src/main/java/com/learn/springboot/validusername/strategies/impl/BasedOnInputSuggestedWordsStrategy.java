package com.learn.springboot.validusername.strategies.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.learn.springboot.validusername.strategies.RestrictedWordsStrategy;
import com.learn.springboot.validusername.strategies.SuggestedWordsStrategy;

/**
 * 
 * @author felipe
 *
 */
public class BasedOnInputSuggestedWordsStrategy implements SuggestedWordsStrategy {

    private RestrictedWordsStrategy restrictedWordsStrategy;


    /**
     * Suggests a list of mocked words
     * 
     * @return a list of mocked words
     */
    @Override
    public Collection<String> getWords() {
        return Arrays.asList("user");
    }


    /**
     * Removes restricted words from input
     * 
     * @return input without restricted words
     */
    @Override
    public String getWord(String input) {
        List<String> restrictedWords = getRestrictedWordsStrategy().getRestrictedWords(input);
        String sanitizedInput = input;
        for (String word : restrictedWords) {
            ;
            sanitizedInput = StringUtils.replaceIgnoreCase(sanitizedInput, word, "");
        }
        return StringUtils.isEmpty(sanitizedInput) ? "user" : sanitizedInput;
    }


    protected RestrictedWordsStrategy getRestrictedWordsStrategy() {
        return restrictedWordsStrategy;
    }


    public void setRestrictedWordsStrategy(RestrictedWordsStrategy restrictedWordsStrategy) {
        this.restrictedWordsStrategy = restrictedWordsStrategy;
    }
}

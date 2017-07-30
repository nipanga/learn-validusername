package com.learn.springboot.validusername.strategies.impl;

import java.util.Arrays;
import java.util.Collection;

import com.learn.springboot.validusername.strategies.SuggestedWordsStrategy;

/**
 * 
 * @author felipe
 *
 */
public class RandomSuggestionSuggestedWordsStrategy implements SuggestedWordsStrategy {

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
     * Suggests a mock word
     * 
     * @return a mocked word
     */
    @Override
    public String getWord(String input) {
        return "user";
    }
}

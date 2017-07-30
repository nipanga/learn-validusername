package com.learn.springboot.validusername.strategies;

import java.util.Collection;

/**
 * Retrieves a suggested word
 * 
 * @author felipe
 *
 */
public interface SuggestedWordsStrategy {

    /**
     * Retrieves a list of suggested words
     * 
     * @return a list of suggested words
     */
    Collection<String> getWords();


    /**
     * Retrieve a single suggested word.
     * 
     * @param input
     *            a base word to retrieve a suggestion. Case it's null, returns a
     *            word that is not based on the input
     * @return
     */
    String getWord(final String input);
}

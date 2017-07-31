package com.learn.springboot.validusername.strategies;

import java.util.Collection;
import java.util.List;

/**
 * Provides methods to retrieve suggested words
 * 
 * @author felipe
 *
 */
public interface RestrictedWordsStrategy {

    /**
     * Returns a list of words
     * 
     * @return Collection<String>
     */
    Collection<String> getWords();


    /**
     * Gets the restricted words inside input
     * 
     * @param input
     *            the input to search for restricted words
     * 
     * @return list of restricted words inside input
     */
    List<String> getRestrictedWords(final String input);
}

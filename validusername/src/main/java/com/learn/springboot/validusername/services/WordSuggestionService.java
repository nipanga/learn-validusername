package com.learn.springboot.validusername.services;

import java.util.List;

/**
 * Provides informations about any given word.
 * 
 * @author felipe
 *
 */
public interface WordSuggestionService {

    /**
     * Suggests a list of words
     * 
     * @param input
     *            a base word to give suggestions
     * @return a list of suggested words
     */
    List<String> suggestWords(final String input);


    /**
     * Verifies if {@code input} is a restricted word
     * 
     * @param input
     *            the word to be verified
     * @return {@link Boolean#TRUE} if the given {@code input} is not allowed,
     *         {@link Boolean#FALSE} otherwise
     */
    boolean isRestricted(final String input);
}

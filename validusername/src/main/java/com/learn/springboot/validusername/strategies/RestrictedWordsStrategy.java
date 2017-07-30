package com.learn.springboot.validusername.strategies;

import java.util.Collection;

/**
 * Provides methods to retrieve suggested words
 * 
 * @author felipe
 *
 */
public interface RestrictedWordsStrategy {

    /**
     * 
     * @return
     */
    Collection<String> getWords();
}

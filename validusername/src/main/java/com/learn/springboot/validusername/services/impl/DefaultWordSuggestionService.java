package com.learn.springboot.validusername.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

import com.learn.springboot.validusername.ProjectConstants;
import com.learn.springboot.validusername.services.WordSuggestionService;
import com.learn.springboot.validusername.strategies.GenerateUsernameStrategy;
import com.learn.springboot.validusername.strategies.RestrictedWordsStrategy;
import com.learn.springboot.validusername.strategies.SuggestedWordsStrategy;

/**
 * Default implementation of {@link WordSuggestionService}
 * 
 * @author felipe
 *
 */
public class DefaultWordSuggestionService implements WordSuggestionService {

    private Environment environment;
    private List<GenerateUsernameStrategy> generateUsernameStrategies;
    private RestrictedWordsStrategy restrictedWordsStrategy;
    private SuggestedWordsStrategy suggestedWordsStrategy;


    /**
     * Suggests a list of words
     * 
     * @param input
     *            a base word to give suggestions
     * @return a list of suggested words
     */
    @Override
    public List<String> suggestWords(final String input) {
        return findSuggestions(input);
    }


    /**
     * Verifies if {@code input} is a restricted word
     * 
     * @param input
     *            the word to be verified
     * @return {@link Boolean#TRUE} if the given {@code input} is not allowed,
     *         {@link Boolean#FALSE} otherwise
     */
    public boolean isRestricted(final String input) {
        final Collection<String> restrictedWords = getRestrictedWordsStrategy().getWords();
        return restrictedWords.contains(StringUtils.lowerCase(input));
    }


    /**
     * Finds a suggestion based on {@code input}
     * 
     * @param input
     *            the input to find a suggested word
     * @return a sorted list of suggestions based on {@code input}
     */
    protected List<String> findSuggestions(final String input) {
        final List<String> suggestions = new ArrayList<>();
        final int maxSuggestions = Integer.valueOf(
                getEnvironment().getProperty(ProjectConstants.App.USERNAME_MAX_SUGGESTIONS));
        while (suggestions.size() < maxSuggestions) {
            for (GenerateUsernameStrategy usernameStrategy : getGenerateUsernameStrategies()) {
                String word = input;
                if (isRestricted(input)) {
                    word = getSuggestedWordsStrategy().getWord(null);
                }
                String username = usernameStrategy.generate(word);
                if (!suggestions.contains(username)) {
                    suggestions.add(username);
                }
            }
        }
        suggestions.sort(Comparator.comparing(String::toString));
        return suggestions;
    }


    protected Environment getEnvironment() {
        return environment;
    }


    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    protected List<GenerateUsernameStrategy> getGenerateUsernameStrategies() {
        return generateUsernameStrategies;
    }


    public void setGenerateUsernameStrategies(List<GenerateUsernameStrategy> strategies) {
        this.generateUsernameStrategies = strategies;
    }


    protected RestrictedWordsStrategy getRestrictedWordsStrategy() {
        return restrictedWordsStrategy;
    }


    public void setRestrictedWordsStrategy(RestrictedWordsStrategy restrictedWordsStrategy) {
        this.restrictedWordsStrategy = restrictedWordsStrategy;
    }


    protected SuggestedWordsStrategy getSuggestedWordsStrategy() {
        return suggestedWordsStrategy;
    }


    public void setSuggestedWordsStrategy(SuggestedWordsStrategy suggestedWordsStrategy) {
        this.suggestedWordsStrategy = suggestedWordsStrategy;
    }
}

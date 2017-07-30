package com.learn.springboot.validusername.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import com.learn.springboot.validusername.ProjectConstants;
import com.learn.springboot.validusername.services.WordSuggestionService;
import com.learn.springboot.validusername.strategies.GenerateUsernameStrategy;

/**
 * Default implementation of {@link WordSuggestionService}
 * 
 * @author felipe
 *
 */
public class DefaultWordSuggestionService implements WordSuggestionService {

    private Environment environment;
    private List<GenerateUsernameStrategy> strategies;


    /**
     * Suggests a list of words
     * 
     * @param input
     *            a base word to give suggestions
     * @return a list of suggested words
     */
    @Override
    public List<String> suggestWords(final String input) {
        String word = input;
        if (isRestricted(input)) {
            word = StringUtils.truncate(input, (input.length() /3));
        }
        return findSuggestions(word);
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
        final String restrictedWordsFilePath = getEnvironment()
                .getProperty(ProjectConstants.Files.RESTRICTED_WORDS);
        try (BufferedReader reader = getFileReader(restrictedWordsFilePath)) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.indexOf(input) != -1) {
                    return Boolean.TRUE;
                }
            }
        } catch (IOException e) {
            // Nothing to do in this case. Or do have, maybe...
        }
        return Boolean.FALSE;
    }


    /**
     * Gets the File based on {@code filePath}
     * 
     * @param filePath
     *            the path of the file to be retrieved
     * @return ClassPathResource the File
     * @throws IOException
     */
    protected BufferedReader getFileReader(final String filePath) throws IOException {
        // Spring Boot has its caveats, unfortunately...
        return new BufferedReader(
                new InputStreamReader(new ClassPathResource(filePath).getInputStream()));
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
            for (GenerateUsernameStrategy strategy : getGenerateUsernameStrategies()) {
                String username = strategy.generate(input);
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
        return strategies;
    }


    public void setGenerateUsernameStrategies(List<GenerateUsernameStrategy> strategies) {
        this.strategies = strategies;
    }
}

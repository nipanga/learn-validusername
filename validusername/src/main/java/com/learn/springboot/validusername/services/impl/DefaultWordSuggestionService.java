package com.learn.springboot.validusername.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections4.CollectionUtils;
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
	    word = getRandomWordFromDictionary();
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
	try (BufferedReader reader = new BufferedReader(
	        new InputStreamReader(getFileReader(restrictedWordsFilePath).getInputStream()))) {
	    String line = "";
	    while ((line = reader.readLine()) != null) {
		if (line.indexOf(input) != -1) {
		    return Boolean.TRUE;
		}
	    }
	} catch (IOException e) {
	    // Nothing to do in this case
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
    protected ClassPathResource getFileReader(final String filePath) throws IOException {
	return new ClassPathResource(filePath);
    }


    /**
     * Gets a random word from a txt dictionary
     * 
     * @return a random word from a txt dictionary
     */
    protected String getRandomWordFromDictionary() {
	final String suggestedDictionaryFilePath = getEnvironment()
	        .getProperty(ProjectConstants.Files.SUGGESTED_WORDS);
	String line = "";
	try (RandomAccessFile file = new RandomAccessFile(
	        getFileReader(suggestedDictionaryFilePath).getFile(), "r")) {
	    while (StringUtils.isEmpty(line)) {
		int randomPos = new Random().nextInt((int) file.length());
		file.seek(randomPos);
		line = file.readLine();
	    }
	} catch (IOException e) {
	    // Nothing to do in this case
	}
	return line;
    }


    /**
     * Finds a suggestion based on {@code input}
     * 
     * @param input
     *            the input to find a suggested word
     * @return a sorted list of suggestions based on {@code input}
     */
    protected List<String> findSuggestions(final String input) {
	final int maxSuggestions = Integer.valueOf(
	        getEnvironment().getProperty(ProjectConstants.App.USERNAME_MAX_SUGGESTIONS));
	final List<String> suggestions = new ArrayList<>();
	for (int i = 0; i < maxSuggestions; i++) {
	    String generatedUsername = generateUsername(input, true);
	    while (suggestions.contains(generatedUsername)) {
		generatedUsername = generateUsername(input, true);
	    }
	    suggestions.add(generatedUsername);
	}
	suggestions.sort(Comparator.comparing(String::toString));
	return suggestions;
    }


    /**
     * Generates a single username, based on {@code input}
     * 
     * @param input
     *            the base word to generate a new username
     * @param useStrategy
     *            defines if this method should use the injected strategies to
     *            generate a new username or not [for code brevity]
     * @return a new word based on {@code input}
     */
    private String generateUsername(final String input, final boolean useStrategy) {
	if (useStrategy) {
	    if (CollectionUtils.isNotEmpty(getStrategies())) {
		int randomNumber = new Random().nextInt(getStrategies().size());
		GenerateUsernameStrategy strategy = getStrategies().get(randomNumber);
		return strategy.generate(input);
	    }
	}
	// This is here just for code brevity...
	int randomNumber = new Random().nextInt(Integer.MAX_VALUE);
	return input + StringUtils.abbreviate(String.valueOf(randomNumber), 6);
    }


    protected Environment getEnvironment() {
	return environment;
    }


    public void setEnvironment(Environment environment) {
	this.environment = environment;
    }


    protected List<GenerateUsernameStrategy> getStrategies() {
	return strategies;
    }


    public void setStrategies(List<GenerateUsernameStrategy> strategies) {
	this.strategies = strategies;
    }
}

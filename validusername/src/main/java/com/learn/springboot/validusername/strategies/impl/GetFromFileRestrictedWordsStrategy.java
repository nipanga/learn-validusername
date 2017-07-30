package com.learn.springboot.validusername.strategies.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import com.learn.springboot.validusername.ProjectConstants;
import com.learn.springboot.validusername.strategies.RestrictedWordsStrategy;

/**
 * Retrieves the restricted words from a file.
 * 
 * @author felipe
 *
 */
public class GetFromFileRestrictedWordsStrategy implements RestrictedWordsStrategy {

    private Environment environment;


    /**
     * Gets the restricted words from a file in the resource path
     * 
     * @return restricted words from a file in the resource path
     */
    @Override
    public Collection<String> getWords() {
        List<String> restrictedWords = new ArrayList<>();
        final String restrictedWordsFilePath = getEnvironment()
                .getProperty(ProjectConstants.Files.RESTRICTED_WORDS);
        try (BufferedReader reader = getFileReader(restrictedWordsFilePath)) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                restrictedWords.add(StringUtils.lowerCase(line));
            }
        } catch (IOException e) {
            // Nothing to do in this case.
        }
        return restrictedWords;
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


    protected Environment getEnvironment() {
        return environment;
    }


    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

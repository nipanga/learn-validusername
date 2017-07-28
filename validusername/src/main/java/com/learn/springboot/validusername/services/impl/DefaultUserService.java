package com.learn.springboot.validusername.services.impl;

import java.util.Collections;
import java.util.List;

import com.learn.springboot.validusername.dto.UserResponseDTO;
import com.learn.springboot.validusername.exceptions.UserValidationException;
import com.learn.springboot.validusername.exceptions.UsernameExistsException;
import com.learn.springboot.validusername.exceptions.UsernameInvalidWordException;
import com.learn.springboot.validusername.models.UserModel;
import com.learn.springboot.validusername.repositories.UserRepository;
import com.learn.springboot.validusername.services.UserService;
import com.learn.springboot.validusername.services.WordSuggestionService;
import com.learn.springboot.validusername.validators.Validator;

/**
 * The default implementation of {@link UserService}
 * 
 * @author felipe
 *
 */
public class DefaultUserService implements UserService {

    private UserRepository userRepository;
    private Validator<String> userValidator;
    private WordSuggestionService wordSuggestionService;


    /**
     * Saves the given {@code user}
     */
    public UserModel save(UserModel user) {
	return getUserRepository().save(user);
    }


    /**
     * Checks if {@code username} is valid.
     * 
     * 
     * @param username
     *            The username to be validated
     * @return a {@link UserResponseDTO} containing information about the
     *         validation. {@link UserResponseDTO#isSuccessful()} equals
     *         {@link Boolean#TRUE} is case the validation is OK.
     *         {@link UserResponseDTO#isSuccessful()} equals false and up to 14
     *         suggested usernames inside
     *         {@link UserResponseDTO#getSuggestedUsernames()} in case the username
     *         validation is not OK.
     * 
     */
    public UserResponseDTO isValidUsername(final String username) {
	try {
	    getUserValidator().validate(username);
	    isValidUsernameInternal(username);
	    final UserResponseDTO response = new UserResponseDTO();
	    response.setSuccessful(Boolean.TRUE);
	    response.setSuggestedUsernames(Collections.<String>emptyList());
	    return response;
	} catch (UserValidationException e) {
	    final UserResponseDTO response = new UserResponseDTO();
	    response.setSuccessful(Boolean.FALSE);
	    response.setSuggestedUsernames(getSuggestedUsernames(username));
	    return response;
	}
    }


    /**
     * Verifies if the given {@code username} already exists, and checks if the
     * {@code username} is a invalid word
     * 
     * @param username
     *            The username to be validated
     * 
     * @throws UserValidationException
     *             if {@code username} is not valid
     */
    protected void isValidUsernameInternal(final String username) throws UserValidationException {
	if (Boolean.TRUE.equals(usernameExists(username))) {
	    throw new UsernameExistsException(
	            String.format("username %s already exists.", username));
	}
	checkInvalidWords(username);
    }


    /**
     * Verifies if {@code username} is already taken
     * 
     * @param username
     *            The username to be validated
     * @return {@link Boolean#TRUE} if {@code username] already exists, {@link
     *         Boolean#FALSE} otherwise
     */
    protected boolean usernameExists(final String username) {
	return getUserRepository().findOneByUsername(username) != null;
    }


    /**
     * Checks if {@code username} has invalid words
     * 
     * @param username
     *            The username to be validated
     * 
     * @throws UserValidationException
     *             if {@code username} is not valid
     */
    protected void checkInvalidWords(final String username) throws UserValidationException {
	if (getWordSuggestionService().isRestricted(username)) {
	    throw new UsernameInvalidWordException("username has a invalid word.");
	}
    }


    /**
     * Retuns a list of suggested usernames
     * 
     * @param username
     *            the base username to suggest another usernames
     * @return a list of suggestes usernames
     */
    private List<String> getSuggestedUsernames(String username) {
	return getWordSuggestionService().suggestWords(username);
    }


    protected UserRepository getUserRepository() {
	return userRepository;
    }


    public void setUserRepository(UserRepository userRepository) {
	this.userRepository = userRepository;
    }


    protected Validator<String> getUserValidator() {
	return userValidator;
    }


    public void setUserValidator(Validator<String> userValidator) {
	this.userValidator = userValidator;
    }


    protected WordSuggestionService getWordSuggestionService() {
	return wordSuggestionService;
    }


    public void setWordSuggestionService(WordSuggestionService wordSuggestionService) {
	this.wordSuggestionService = wordSuggestionService;
    }
}

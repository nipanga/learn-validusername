package com.learn.springboot.validusername.services;

import com.learn.springboot.validusername.dto.UserDTO;
import com.learn.springboot.validusername.dto.UserResponseDTO;
import com.learn.springboot.validusername.models.UserModel;

/**
 * Provides informations regarding {@link UserModel}
 * 
 * @author felipe
 *
 */
public interface UserService extends BusinessService<UserModel, UserDTO> {

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
    public UserResponseDTO isValidUsername(final String username);
}

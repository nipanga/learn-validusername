package com.learn.springboot.validusername.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author felipe
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDTO {

    private boolean successful;
    private List<String> suggestedUsernames;


    public boolean isSuccessful() {
	return successful;
    }


    public void setSuccessful(boolean success) {
	this.successful = success;
    }


    public List<String> getSuggestedUsernames() {
	return suggestedUsernames;
    }


    public void setSuggestedUsernames(List<String> suggestedUsernames) {
	this.suggestedUsernames = suggestedUsernames;
    }
}

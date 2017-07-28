package com.learn.springboot.validusername.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author felipe
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private String username;


    public String getUsername() {
	return username;
    }


    public void setUsername(String username) {
	this.username = username;
    }
}

package com.learn.springboot.validusername.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author felipe
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDTOList {

    private List<UserResponseDTO> userResponseDTOs;


    public List<UserResponseDTO> getUserResponseDTOs() {
        return userResponseDTOs;
    }

    
    public void setUserResponseDTOs(List<UserResponseDTO> userResponseDTOs) {
        this.userResponseDTOs = userResponseDTOs;
    }


}

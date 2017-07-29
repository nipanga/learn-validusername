package com.learn.springboot.validusername.endpoints;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.springboot.validusername.dto.UserDTO;
import com.learn.springboot.validusername.dto.UserResponseDTO;
import com.learn.springboot.validusername.dto.UserResponseDTOList;
import com.learn.springboot.validusername.services.UserService;

/**
 * Provides information about Users
 * 
 * [ This endpoint is here so we can test with a app like Postman or Restman
 * (chrome and opera, respectively), and see the list of suggested usernames
 * easily ]
 * 
 * @author felipe
 *
 */
@RestController
@RequestMapping(value = "/users")
public class UserEndpoint {

    @Resource(name = "userService")
    private UserService userService;


    /**
     * Checks if the given Username(s) are valid.
     * 
     * @param username
     *            the usernames to be checked.
     * @return a {@link UserResponseDTOList} containg information about each given
     *         {@code username}
     */
    @RequestMapping(value = "/check-username", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    @ResponseBody
    public UserResponseDTOList checkUserName(@RequestBody final UserDTO username) {
	final UserResponseDTOList response = new UserResponseDTOList();
	final List<UserResponseDTO> userResponseDTOs = new ArrayList<>();
	UserResponseDTO singleResponse = getUserService().isValidUsername(username.getUsername());
	userResponseDTOs.add(singleResponse);
	response.setUserResponseDTOs(userResponseDTOs);
	return response;
    }


    protected UserService getUserService() {
	return userService;
    }


    public void setUserService(UserService userService) {
	this.userService = userService;
    }
}

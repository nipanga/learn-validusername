package com.learn.springboot.validusername.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author felipe
 *
 */
@Entity(name = "users")
public class UserModel extends BaseModel {

    @NotNull
    @Column(name = "username", unique = true, nullable = false)
    private String username;


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }
}

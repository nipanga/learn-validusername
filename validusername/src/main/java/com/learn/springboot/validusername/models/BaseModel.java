package com.learn.springboot.validusername.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author felipe
 *
 */
@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk", unique = true, nullable = false)
    private Long pk;


    public Long getPk() {
        return pk;
    }


    public void setPk(Long pk) {
        this.pk = pk;
    }
}

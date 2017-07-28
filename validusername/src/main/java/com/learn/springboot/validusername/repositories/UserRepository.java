package com.learn.springboot.validusername.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.learn.springboot.validusername.models.UserModel;

/**
 * 
 * @author felipe
 *
 */
@Repository
public interface UserRepository
        extends JpaRepository<UserModel, Long>, QueryByExampleExecutor<UserModel> {

    /**
     * 
     * @param username
     * @return
     */
    UserModel findOneByUsername(final String username);
}

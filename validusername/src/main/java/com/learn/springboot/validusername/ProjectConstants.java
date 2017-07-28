package com.learn.springboot.validusername;

/**
 * Constants shared by the whole project
 * 
 * @author felipe
 *
 */
public interface ProjectConstants {

    interface App {

	String USERNAME_MAX_SUGGESTIONS = "app.username.suggest.max";
    }

    interface Files {

	String CLASSPATH = "classpath:";
	String RESTRICTED_WORDS = "app.file.restrictedwords";
	String SUGGESTED_WORDS = "app.file.suggestedwords";
    }
}

package com.learn.springboot.validusername.services.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learn.springboot.validusername.TestContextConfiguration;
import com.learn.springboot.validusername.dto.UserResponseDTO;
import com.learn.springboot.validusername.models.UserModel;
import com.learn.springboot.validusername.repositories.UserRepository;
import com.learn.springboot.validusername.validators.impl.UsernameValidator;

/**
 * 
 * @author felipe
 *
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContextConfiguration.class)
@SpringBootTest
public class DefaultUserServiceTest {

    private DefaultUserService fixture;
    //
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DefaultWordSuggestionService wordSuggestionService;
    @Autowired
    private Environment environment;
    //
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    /**
     * 
     */
    @Before
    public void setUp() {
        UserModel mockUser = new UserModel();
        mockUser.setPk(1L);
        mockUser.setUsername("username");
        this.wordSuggestionService.setEnvironment(this.environment);
        this.fixture = new DefaultUserService();
        this.fixture.setWordSuggestionService(this.wordSuggestionService);
        this.fixture.setUserRepository(this.userRepository);
        this.fixture.setUserValidator(new UsernameValidator());
    }


    /**
     * 
     */
    @Test
    public void test_invalidUsername_charLength() {
        final String username = "12345";
        UserResponseDTO response = fixture.isValidUsername(username);
        assertFalse(response.isSuccessful());
        assertFalse(CollectionUtils.isEmpty(response.getSuggestedUsernames()));
        checkSuggestions(response.getSuggestedUsernames());
    }


    /**
     * 
     */
    @Test
    public void test_invalidUsername_alreadyExists() {
        UserModel testUser = new UserModel();
        testUser.setUsername("username");
        fixture.save(testUser);
        final String username = "username";
        UserResponseDTO response = fixture.isValidUsername(username);
        assertFalse(response.isSuccessful());
        assertFalse(CollectionUtils.isEmpty(response.getSuggestedUsernames()));
        checkSuggestions(response.getSuggestedUsernames());
    }


    /**
     * 
     */
    @Test
    public void test_username_invalid_restrictedWords() {
        final String username = "invalidWord";
        final UserResponseDTO response = fixture.isValidUsername(username);
        assertFalse(response.isSuccessful());
        assertFalse(CollectionUtils.isEmpty(response.getSuggestedUsernames()));
        checkSuggestions(response.getSuggestedUsernames());
    }


    @Test
    public void test_username_valid() {
        final String username = "avalidusername";
        final UserResponseDTO response = fixture.isValidUsername(username);
        assertTrue(response.isSuccessful());
        assertTrue(CollectionUtils.isEmpty(response.getSuggestedUsernames()));
    }


    /**
     * 
     */
    void checkSuggestions(final List<String> suggestions) {
        final int suggestionsSize = suggestions.size();
        assertTrue(String.format("Should have from 5 up to 14 suggestions, but has %d",
                suggestionsSize), ((14 <= suggestionsSize) && (suggestionsSize <= 14)));
        for (String s : suggestions) {
            assertTrue(String.format("Suggestion %s should have more than 6 chars, but has %d", s,
                    s.length()), (6 <= s.length()));
        }
    }
}
package com.learn.springboot.validusername.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

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
import com.learn.springboot.validusername.exceptions.UserValidationException;
import com.learn.springboot.validusername.models.UserModel;
import com.learn.springboot.validusername.repositories.UserRepository;
import com.learn.springboot.validusername.strategies.RestrictedWordsStrategy;
import com.learn.springboot.validusername.strategies.SuggestedWordsStrategy;
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
    @Autowired
    private RestrictedWordsStrategy restrictedWordsStrategy;
    @Autowired
    private SuggestedWordsStrategy suggestedWordsStrategy;
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
        this.wordSuggestionService.setRestrictedWordsStrategy(restrictedWordsStrategy);
        this.wordSuggestionService.setSuggestedWordsStrategy(suggestedWordsStrategy);
        this.fixture = new DefaultUserService();
        this.fixture.setWordSuggestionService(this.wordSuggestionService);
        this.fixture.setUserRepository(this.userRepository);
        this.fixture.setUserValidator(new UsernameValidator());
    }


    /**
     * 
     */
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
    @Test
    public void test_invalidUsername_charLength_1() {
        final String username = "1";
        UserResponseDTO response = fixture.isValidUsername(username);
        assertFalse(response.isSuccessful());
        test_suggestions(response.getSuggestedUsernames());
    }


    /**
     * 
     */
    @Test
    public void test_invalidUsername_charLength_2() {
        final String username = "12";
        UserResponseDTO response = fixture.isValidUsername(username);
        assertFalse(response.isSuccessful());
        test_suggestions(response.getSuggestedUsernames());
    }


    /**
     * 
     */
    @Test
    public void test_invalidUsername_charLength_3() {
        final String username = "123";
        UserResponseDTO response = fixture.isValidUsername(username);
        assertFalse(response.isSuccessful());
        test_suggestions(response.getSuggestedUsernames());
    }


    /**
     * 
     */
    @Test
    public void test_invalidUsername_charLength_4() {
        final String username = "1234";
        UserResponseDTO response = fixture.isValidUsername(username);
        assertFalse(response.isSuccessful());
        test_suggestions(response.getSuggestedUsernames());
    }


    /**
     * 
     */
    @Test
    public void test_invalidUsername_charLength_5() {
        final String username = "12345";
        UserResponseDTO response = fixture.isValidUsername(username);
        assertFalse(response.isSuccessful());
        test_suggestions(response.getSuggestedUsernames());
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
        test_suggestions(response.getSuggestedUsernames());
    }


    /**
     * 
     */
    @Test
    public void test_username_invalid_restrictedWords() {
        final String username = "invalidWord";
        final UserResponseDTO response = fixture.isValidUsername(username);
        assertFalse(response.isSuccessful());
        test_suggestions(response.getSuggestedUsernames());
    }


    /**
     * 
     */
    void test_suggestions(final List<String> suggestions) {
        assertFalse(CollectionUtils.isEmpty(suggestions));
        final int suggestionsSize = suggestions.size();
        assertTrue(String.format("Should have from 5 up to 14 suggestions, but has %d",
                suggestionsSize), ((5 <= suggestionsSize) && (suggestionsSize <= 14)));
        List<String> distinctSuggestions = suggestions.stream().distinct()
                .collect(Collectors.toList());
        assertEquals("Suggestion should not have duplicates", suggestions.size(),
                distinctSuggestions.size());
        for (String suggestion : suggestions) {
            assertTrue(String.format("Suggestion %s should have more than 6 chars, but has %d",
                    suggestion, suggestion.length()), (6 <= suggestion.length()));
            try {
                this.fixture.checkInvalidWords(suggestion);
            } catch (UserValidationException e) {
                assertTrue("Suggested list should not have invalid words", Boolean.FALSE);
            }
        }
    }
}

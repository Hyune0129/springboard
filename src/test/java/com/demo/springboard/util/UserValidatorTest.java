package com.demo.springboard.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

public class UserValidatorTest {

    @Test
    @DisplayName("testIsValidId")
    public void testIsValidId() {
        assertTrue(UserValidator.isValidId("test1234"));
        assertFalse(UserValidator.isValidId("123test1"));
        assertFalse(UserValidator.isValidId("test123456789012345678901"));
    }

    @Test
    @DisplayName("testIsValidPassword")
    public void testIsValidPassword() {
        assertTrue(UserValidator.isValidPassword("test1234"));
        assertTrue(UserValidator.isValidPassword("123test34"));
        assertFalse(UserValidator.isValidPassword("test123456789012345678901"));
        assertFalse(UserValidator.isValidPassword("12www3456789012345678901"));
    }

    @Test
    @DisplayName("testIsValidName")
    public void testIsValidName() {
        assertTrue(UserValidator.isValidName("test1234"));
        assertFalse(UserValidator.isValidName("test123456789012345678901"));
        assertFalse(UserValidator.isValidName("11test1"));
    }
}

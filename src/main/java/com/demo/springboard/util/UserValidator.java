package com.demo.springboard.util;

public class UserValidator {

    static final String ID_REGEX = "^[a-zA-Z](?=.*\\d)[a-zA-Z\\d]{5,19}$";
    static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$";
    static final String NAME_REGEX = "^[a-zA-Z][a-zA-Z0-9]{5,19}$";

    public static boolean isValidId(String id) {
        return id.matches(ID_REGEX);
    }

    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean isValidName(String name) {
        return name.matches(NAME_REGEX);
    }

    private UserValidator() { // hide
    }
}

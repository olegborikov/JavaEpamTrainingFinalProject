package com.borikov.bullfinch.validator;

public abstract class AbstractValidator {
    public boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}

package com.system.Learning_system_springboot.service;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
public class PasswordGenerator {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";
    private static final String ALL_CHARS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARS;
    private static final SecureRandom RANDOM = new SecureRandom();
    public static String generatePassword() {
        int minLength = 12;
        int minLowercase = 1;
        int minUppercase = 1;
        int minDigits = 1;
        int minSpecialChars = 1;
        StringBuilder password = new StringBuilder();
        password.append(getRandomChar(LOWERCASE));
        password.append(getRandomChar(UPPERCASE));
        password.append(getRandomChar(DIGITS));
        password.append(getRandomChar(SPECIAL_CHARS));
        for (int i = password.length(); i < minLength; i++) {
            password.append(getRandomChar(ALL_CHARS));
        }
        List<Character> passwordChars = password.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(passwordChars, RANDOM);
        return passwordChars.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private static char getRandomChar(String charSet) {
        int randomIndex = RANDOM.nextInt(charSet.length());
        return charSet.charAt(randomIndex);
    }

}
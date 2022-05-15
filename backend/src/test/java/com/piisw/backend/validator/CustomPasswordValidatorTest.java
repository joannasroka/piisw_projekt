package com.piisw.backend.validator;


import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomPasswordValidatorTest {

    private final CustomPasswordValidator passwordValidator = new CustomPasswordValidator();

    @ParameterizedTest
    @MethodSource("provideCorrectPassword")
    void shouldReturnTrueIfPasswordValid(String input) {
        // when
        boolean isValid = passwordValidator.validate(input);

        // then
        assertTrue(isValid);
    }

    private static Stream<Arguments> provideCorrectPassword() {
        return Stream.of(
                Arguments.of("Ka1@" + RandomString.make(56)),
                Arguments.of("Ka1@" + RandomString.make(4)));
    }

    @ParameterizedTest
    @MethodSource("provideIncorrectPassword")
    void shouldReturnFalseIfPasswordInvalid(String input) {
        // when
        boolean isValid = passwordValidator.validate(input);

        // then
        assertFalse(isValid);
    }

    private static Stream<Arguments> provideIncorrectPassword() {
        return Stream.of(
                Arguments.of("KKKK123@"),
                Arguments.of("kkkk123@"),
                Arguments.of("KKkk1234"),
                Arguments.of("KKkkss#@"),
                Arguments.of(""),
                Arguments.of("Kk1#"),
                Arguments.of("KKkk1#@"),
                Arguments.of("Ka1@ThereIsNextFiftySixCharactersAndOneMoreToExceedLimitxxxxX")
        );
    }
}
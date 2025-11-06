package se.yrgo.libraryapp.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    // tests for onlyLetterAndWhiteSpace()

    @ParameterizedTest
    @ValueSource(strings = {"123 abc @£", "1234567890+´/*-@£$€{[]}\\~^¨'_.:,;<>|§½ abc "})
    void onlyLettersAndWhitespace(String input) {
        var result = Utils.onlyLettersAndWhitespace(input);
        assertEquals(" abc ", result);
    }

    @Test
    void whiteSpace() {
        var result = Utils.onlyLettersAndWhitespace("   ");
        assertEquals("   ", result);
    }

    @Test
    void emptyString() {
        var result = Utils.onlyLettersAndWhitespace("");
        assertEquals("", result);
    }

    @Test
    void nullString() {
        assertThrows(NullPointerException.class, () -> Utils.onlyLettersAndWhitespace(null));
    }

    // tests for cleanAndUnLeet()

    @Test
    void cleanAndUnLeet() {
        var result = Utils.cleanAndUnLeet("1337 45 680");
        assertEquals("leet as bbo", result);
    }

}

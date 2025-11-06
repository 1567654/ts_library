package se.yrgo.libraryapp.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsernameTest {
    @Test
    void correctUsername() {
        assertTrue(Username.validate("bosse"));
    }

    @Test
    void incorrectUsername() {
        assertFalse(Username.validate("name with space"));
    }

    @Test
    void nullUsername() {
        assertThrows(NullPointerException.class, () -> Username.validate(null));
    }

    @Test
    void emptyUsername() {
        assertFalse(Username.validate(""));
    }

    @Test
    void specialUsername() {
        assertTrue(Username.validate("@._-"));
    }

    @Test
    void longUsername() {
        assertTrue(Username.validate("123456789012345678901234567890"));
    }
}

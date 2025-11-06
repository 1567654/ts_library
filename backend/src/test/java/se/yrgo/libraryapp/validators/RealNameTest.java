package se.yrgo.libraryapp.validators;

import io.jooby.JoobyTest;
import org.junit.jupiter.api.Test;
import se.yrgo.libraryapp.App;

import static org.junit.jupiter.api.Assertions.*;

@JoobyTest(App.class)
public class RealNameTest {
    @Test
    void testValidate() {
        assertTrue(RealName.validate("William"));
    }

    @Test
    void testValidateNull() {
        assertThrows(NullPointerException.class, () -> RealName.validate(null));
    }

    @Test
    void testValidateInvalidName() {
        assertFalse(RealName.validate("frack"));
    }

    @Test
    void testValidateInvalidNames() {
        assertFalse(RealName.validate("frack Blimey "));
    }
}

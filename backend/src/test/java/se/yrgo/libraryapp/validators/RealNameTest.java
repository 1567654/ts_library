package se.yrgo.libraryapp.validators;

import io.jooby.JoobyTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

    @ParameterizedTest
    @ValueSource(strings = {"frack", "Blimey shite", "xx DeMoN xx", "@_.demon@£€"})
    void testValidateInvalidName(String name) {
        assertFalse(RealName.validate(name));
    }
}

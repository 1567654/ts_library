package se.yrgo.libraryapp.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.yrgo.libraryapp.dao.UserDao;
import se.yrgo.libraryapp.entities.LoginInfo;
import se.yrgo.libraryapp.entities.UserId;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @Test
    void correctLogin() {
        final String userId = "1";
        final UserId id = UserId.of(userId);
        final String username = "testuser";
        final String password = "password";
        final String passwordHash =
                "$argon2i$v=19$m=16,t=2,p=1$QldXU09Sc2dzOWdUalBKQw$LgKb6x4usOpDLTlXCBVhxA";
        final LoginInfo info = new LoginInfo(id, passwordHash);
        @SuppressWarnings("deprecation")
        final PasswordEncoder passwordEncoder =
                org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();

        when(userDao.getLoginInfo(username)).thenReturn(Optional.of(info));
        UserService userService = new UserService(userDao, passwordEncoder);
        assertThat(userService.validate(username,
                password)).isEqualTo(Optional.of(id));
    }

    @Test
    void registerTest() {
        final String username = "testing";
        final String password = "password";
        @SuppressWarnings("deprecation")
        final PasswordEncoder passwordEncoder =
                org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        when(userDao.register(username, username, password)).thenReturn(true);
        UserService userService = new UserService(userDao, passwordEncoder);
        userService.register(username, username, password);
        assertThat(userDao.getLoginInfo(username)).isNotNull();
    }

    @Test
    void isNameAvailable() {
        final String username = "testing";

        when(userDao.isNameAvailable(username)).thenReturn(true);
        @SuppressWarnings("deprecation")
        final PasswordEncoder passwordEncoder =
                org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        UserService userService = new UserService(userDao, passwordEncoder);
        userService.isNameAvailable(username);
        assertThat(userDao.isNameAvailable(username)).isTrue();
    }
}
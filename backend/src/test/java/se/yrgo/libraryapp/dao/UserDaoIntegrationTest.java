package se.yrgo.libraryapp.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.radcortez.flyway.test.annotation.H2;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import se.yrgo.libraryapp.entities.LoginInfo;
import se.yrgo.libraryapp.entities.User;
import se.yrgo.libraryapp.entities.UserId;

@Tag("integration")
@Testcontainers
public class UserDaoIntegrationTest {
//    private static DataSource ds;
//    @BeforeAll
//    static void initDataSource() {
//// this way we do not need to create a new datasource every time
//        final JdbcDataSource ds = new JdbcDataSource();
//        ds.setURL("jdbc:h2:mem:test");
//        UserDaoIntegrationTest.ds = ds;
//    }

@Container
private static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.29");
    private static DataSource ds;
    @BeforeAll
    static void setup() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(mysql.getJdbcUrl());
        hikariConfig.setUsername(mysql.getUsername());
        hikariConfig.setPassword(mysql.getPassword());
        hikariConfig.setDriverClassName(mysql.getDriverClassName());
        UserDaoIntegrationTest.ds = new HikariDataSource(hikariConfig);
        Flyway flyway = Flyway.configure().dataSource(ds).load();
        flyway.migrate();
    }

    @Test
    void getUserByName() {
// this data comes from the test migration files
        final String username = "test";
        final UserId userId = UserId.of(1);
        UserDao userDao = new UserDao(ds);
        Optional<User> maybeUser = userDao.get(Integer.toString(userId.getId()));
        assertThat(maybeUser).isPresent();
        assertThat(maybeUser.get().getName()).isEqualTo(username);
        assertThat(maybeUser.get().getId()).isEqualTo(userId);
    }

    @Test
    void getLoginInfo() {
        final String username = "test";
        final UserId userId = UserId.of(1);
        UserDao userDao = new UserDao(ds);
        Optional<LoginInfo> maybeLoginInfo = userDao.getLoginInfo(username);
        assertThat(maybeLoginInfo).isPresent();
        assertThat(maybeLoginInfo.get().getUserId()).isEqualTo(userId);
    }

//    @Test
//    void register() {
//        final String username = "test";
//        final UserId userId = UserId.of(1);
//        final String passwordHash =
//        UserDao userDao = new UserDao(ds);
//    }
}
package coffee.backend;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DatabaseConnectionTest {

    @Test
    void testDatabaseDriverClass() {

        assertThrows(ClassNotFoundException.class, () -> {

            Class.forName("com.invalid.jdbc.Driver");
        }, "Expected ClassNotFoundException for an invalid driver class");
    }

    @Test
    void testDatabaseConnectionFailure() {

        assertThrows(SQLException.class, () -> {

            DriverManager.getConnection(
                    "jdbc:mysql://invalid-host:3306/invaliddb",
                    "invaliduser",
                    "invalidpassword"
            );
        }, "Expected SQLException for invalid connection parameters");
    }
}

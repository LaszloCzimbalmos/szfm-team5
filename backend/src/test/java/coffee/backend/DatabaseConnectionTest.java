package coffee.backend;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

public class DatabaseConnectionTest {

    @Test
    public void testGetConnection() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            assertNotNull(connection, "Database connection should not be null.");
            assertFalse(connection.isClosed(), "Database connection should be open.");
        } catch (Exception e) {
            fail("Exception occurred while establishing database connection: " + e.getMessage());
        }
    }
}

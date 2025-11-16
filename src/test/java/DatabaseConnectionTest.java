import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;

public class DatabaseConnectionTest {
    
    @Test
    public void testGetConnectionReturnsNullWhenDatabaseNotAvailable() {
        // Since we're skipping database connections, we expect null
        // This test verifies the method handles missing database gracefully
        Connection conn = DatabaseConnection.getConnection();
        
        // When database is not available, connection should be null
        // This is the expected behavior - method should not throw exception
        assertNull(conn, "Connection should be null when database is not available");
    }
    
    @Test
    public void testGetConnectionCanBeCalledMultipleTimes() {
        // Test that method can be called multiple times without throwing exceptions
        // Even when database is not available
        Connection conn1 = DatabaseConnection.getConnection();
        Connection conn2 = DatabaseConnection.getConnection();
        
        // Both calls should complete without throwing exceptions
        // Both should return null when database is not available
        assertNull(conn1, "First call should return null when database not available");
        assertNull(conn2, "Second call should return null when database not available");
    }
    
    @Test
    public void testGetConnectionDoesNotThrowException() {
        // Verify that getConnection doesn't throw exceptions even without database
        assertDoesNotThrow(() -> {
            DatabaseConnection.getConnection();
        }, "getConnection should not throw exception when database is unavailable");
    }
}


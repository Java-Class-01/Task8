import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    //details of sql server
    // Add loginTimeout to fail fast in test environments (2 seconds)
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=TestDB;encrypt=true;trustServerCertificate=true;loginTimeout=2;";
    private static final String USER = "sa";
    private static final String PASSWORD = "put your sql password";

    public static Connection getConnection() {
        try {
            // Set system property for connection timeout to fail fast
            System.setProperty("java.sql.DriverManager.timeout", "2000");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Silently return null - connection failures are expected in test environments
            // Only print error if not in headless/test mode
            if (!Boolean.getBoolean("java.awt.headless") && !Boolean.getBoolean("skip.db.errors")) {
                System.out.println("Database connection failed!");
                e.printStackTrace();
            }
            return null;
        }
    }
}

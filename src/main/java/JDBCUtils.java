import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private static String dbURL = "jdbc:sqlserver://localhost\\MSSQLSERVER:1433;user=sa;password=MSahaidac0508;database=BikeStores";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}



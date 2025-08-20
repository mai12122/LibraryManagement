package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {

    private static Connection connection = null;
    private static final String URL = "jdbc:mysql://localhost:3306/LMSs6";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "$Nuth170124$";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connected to MySQL successfully!");
            } catch (Exception e) {
                System.out.println("Connection failed!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static ResultSet executeQuery(String query) {
        try {
            Statement statement = getConnection().createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Query execution failed!");
            e.printStackTrace();
        }
        return null;
    }

    public static int executeUpdate(String query) {
        try {
            Statement statement = getConnection().createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Update execution failed!");
            e.printStackTrace();
        }
        return 0;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close the connection!");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            getConnection();

            String query = "SELECT id, username, email FROM Accounts";
            ResultSet rs = executeQuery(query);

            System.out.println("=== Accounts Table ===");
            while (rs != null && rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Username: " + rs.getString("username") +
                                   ", Email: " + rs.getString("email"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}

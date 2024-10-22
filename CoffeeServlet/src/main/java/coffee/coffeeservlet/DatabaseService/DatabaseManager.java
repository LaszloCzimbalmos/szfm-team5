package coffee.coffeeservlet.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection connection;
    public DatabaseManager() throws SQLException, ClassNotFoundException {
        connection = DatabaseConnection.initializeDatabase();
    }

    public static void UpdateTable(Integer tableNumber, String Name, String Email, String Password, Boolean isFree, String Date) throws SQLException {
        String updateSql = "UPDATE booking SET Name = ?, Email = ?, Password = ?, IsFree = ?, Date = ? WHERE TableNumber = ?";
        PreparedStatement updateStmt = connection.prepareStatement(updateSql);

        updateStmt.setString(1, Name);
        updateStmt.setString(2, Email);
        updateStmt.setString(3, Password);
        updateStmt.setBoolean(4, isFree);
        updateStmt.setString(5, Date);

        updateStmt.setInt(6, tableNumber);

        updateStmt.executeUpdate();
    }

    public static Integer GetFreeTableNumber() throws SQLException {
        // Gets only one free table as it's number, returns -1 if there is no free one
        String sql = "SELECT TableNumber FROM booking WHERE IsFree = true LIMIT 1";
        PreparedStatement getFreeTableStatement = connection.prepareStatement(sql);

        ResultSet resultSet = getFreeTableStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("TableNumber");
        }
        else{
            return -1;
        }
    }
}

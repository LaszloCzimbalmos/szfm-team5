package coffee.backend;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "loginServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cofedb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String callerCode = request.getParameter("userid");
        String pawPrint = request.getParameter("pswrd");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connect to the database
            conn = DatabaseConnection.initializeDatabase();

            // Prepare the SQL statement to insert the user data
            String sql = "INSERT INTO registered (email, caller_code, paw_print) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, callerCode);
            stmt.setString(3, pawPrint);

            // Execute the statement
            int rowsInserted = stmt.executeUpdate();

            // Check if the user was successfully inserted
            if (rowsInserted > 0) {
                response.sendRedirect("https://laszloczimbalmos.github.io/");
            } else {
                response.getWriter().write("Error adding user.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Database error.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

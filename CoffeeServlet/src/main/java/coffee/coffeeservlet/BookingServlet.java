package coffee.coffeeservlet;

import coffee.coffeeservlet.Models.TableRequest;
import coffee.coffeeservlet.DatabaseService.DatabaseManager;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bookingServlet", value = "/bookTable")
public class BookingServlet extends HttpServlet {

    public void init() {
        System.out.println("Booking the table...");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter responseSender = response.getWriter();
        TableRequest tableRequest = new TableRequest(request);

        Integer freeTable = bookTable(tableRequest);

        responseSender.println("<html><body>");
        responseSender.println("<h1>Table Booking Confirmation</h1>");

        responseSender.println("<p>Dear " + tableRequest.getName() + ",</p>");
        responseSender.println("<p>Your booking was successful! You have been assigned Table Number: <strong>" + freeTable + "</strong>.</p>");
        responseSender.println("<p>We look forward to seeing you at the café.</p>");

        responseSender.println("</body></html>");
        responseSender.close();

        // Generate Password for Table
        // Send email with the credentials

    }

    private static Integer bookTable(TableRequest tableRequest) {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            String tablePassword = "testPW"; // TODO: Create method for pw generation

            Integer freeTableNumber = dbManager.GetFreeTableNumber();
            System.out.println(freeTableNumber);

            dbManager.UpdateTable(freeTableNumber,tableRequest.getName(), tableRequest.getEmail(), tablePassword, false, tableRequest.getDate());

            return freeTableNumber;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}
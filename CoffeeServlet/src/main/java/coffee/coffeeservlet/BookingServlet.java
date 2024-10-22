package coffee.coffeeservlet;

import coffee.coffeeservlet.Models.TableRequest;
import coffee.coffeeservlet.DatabaseService.DatabaseManager;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bookingServlet", value = "/bookTable")
public class BookingServlet extends HttpServlet {

    public void init() {
        System.out.println("Booking the table...");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        TableRequest tableRequest = new TableRequest(request);

        Integer freeTable = bookTable(tableRequest);

        if (freeTable != -1) {
            request.setAttribute("name", tableRequest.getName());
            request.setAttribute("tableNumber", freeTable);
            RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("noTablesAvailable.jsp");
            dispatcher.forward(request, response);
        }

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
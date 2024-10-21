package coffee.coffeeservlet;

import coffee.coffeeservlet.Models.TableRequest;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bookingServlet", value = "/bookTable")
public class BookingServlet extends HttpServlet {
    private String message;

    public void init() {
        System.out.println("Booking the table...");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        TableRequest tableRequest = new TableRequest(request);

        System.out.println(tableRequest.getName());
        System.out.println(tableRequest.getEmail());
        System.out.println(tableRequest.getDate());

        // Gets free table number
        // Update DB record according to tableNumber
        // Generate Password for Table
        // Send email with the credentials

    }

    public void destroy() {
    }
}
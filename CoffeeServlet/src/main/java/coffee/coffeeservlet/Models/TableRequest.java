package coffee.coffeeservlet.Models;

import javax.servlet.http.HttpServletRequest;

public class TableRequest {
    private final String Name;
    private final String Email;
    private final String Date;

    public TableRequest(HttpServletRequest request) {
        Name = request.getParameter("name");
        Email = request.getParameter("email");
        Date = request.getParameter("bookingDate");
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getDate() {
        return Date;
    }
}

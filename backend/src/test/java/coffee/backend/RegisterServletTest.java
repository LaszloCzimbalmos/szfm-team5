package coffee.backend;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import javax.servlet.http.*;
import java.io.PrintWriter;

public class RegisterServletTest {

    @Test
    public void testDoPostSuccess() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);

        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("password")).thenReturn("testPassword");
        when(response.getWriter()).thenReturn(writer);

        RegisterServlet servlet = new RegisterServlet();
        servlet.doPost(request, response);

        verify(writer).println("Registration Successful!");
    }

    @Test
    public void testDoPostFailure() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);

        when(request.getParameter("username")).thenReturn(null);
        when(response.getWriter()).thenReturn(writer);

        RegisterServlet servlet = new RegisterServlet();
        servlet.doPost(request, response);

        verify(writer).println("Registration Failed: Missing username or password.");
    }
}


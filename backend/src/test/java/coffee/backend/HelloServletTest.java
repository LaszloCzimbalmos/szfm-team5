package coffee.backend;


import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import javax.servlet.http.*;
import java.io.PrintWriter;

public class HelloServletTest {

    @Test
    public void testDoGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);

        when(response.getWriter()).thenReturn(writer);

        HelloServlet servlet = new HelloServlet();
        servlet.doGet(request, response);

        verify(writer).println("Hello, World!");
    }
}

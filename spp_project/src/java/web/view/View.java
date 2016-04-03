package java.web.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Олег on 01.04.2016.
 */
public class View {

    /**
     * Registers the client
     * @param request
     * @param response
     * @throws IOException
     */
    public static void registerClientView(HttpServletRequest request,
                                          HttpServletResponse response) throws IOException {
        if (request.getParameter("registration") != null) {
            PrintWriter writer = response.getWriter();
            String login = request.getParameter("login");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String password = request.getParameter("password");

            if (login != null && name != null && surname != null && password != null) {
                writer.println(login);
                writer.println(name);
                writer.println(surname);
                writer.println(password);
            } else
                writer.println("Incorrect data!");
        }
    }
}

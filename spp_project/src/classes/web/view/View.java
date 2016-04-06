package classes.web.view;

import classes.web.controller.ModificationController;
import classes.web.entity.user.User;

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
     * @param request - http request of client
     * @param response - response for user
     * @throws IOException
     */
    public static void registerUserView(HttpServletRequest request,
                                        HttpServletResponse response,
                                        User.UserType userType) {
        try {
            if (request.getParameter("registration") != null) {
                PrintWriter writer = response.getWriter();
                String result = "";
                result = ModificationController.AddNewUserController(request, userType);
                writer.println(result);
            }
        } catch (IOException ex) {}
    }

    /**
     *
     * @param request
     * @param response
     */
    public static void loginUserView(HttpServletRequest request,
                                     HttpServletResponse response) {
    }
}

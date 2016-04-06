package classes.web.controller;

import classes.web.entity.user.Client;
import classes.web.model.service.ModificationService;

import javax.servlet.http.HttpServletRequest;

import static classes.web.entity.user.User.*;

/**
 * Created by Олег on 31.03.2016.
 */
public class ModificationController {

    public static String AddNewUserController(HttpServletRequest request, UserType userType) {
        String result = "";
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");

        try {
            if (login == null || name == null || surname == null || password == null ||
                login.equals("") || name.equals("") || surname.equals("") || password.equals(""))
                return ("Not all fields are field!");

            switch (userType) {
                case CLIENT: {
                    Client client = new Client();
                    client.setLogin(login);
                    client.setName(name);
                    client.setSurname(surname);
                    client.setPassword(password);

                    if(ModificationService.addNewUserService(client))
                        result = "The user have been created!";
                } break;
                case DISPATCHER: {

                } break;
                case ADMIN: {

                }
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }

        return result;
    }

}

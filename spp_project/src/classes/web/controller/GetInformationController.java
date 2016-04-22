package classes.web.controller;

import classes.web.controller.exception.ControllerException;
import classes.web.entity.Load;
import classes.web.entity.Transport;
import classes.web.entity.user.User;
import classes.web.model.service.exception.ServiceException;
import classes.web.model.service.GetInformationService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static classes.web.entity.user.User.*;

public class GetInformationController {
    public static List<Load> viewLoadListController(UserType userType, String... args) throws ControllerException {
        List<Load> loadList = new ArrayList<>();
        try {
            switch (userType) {
                case ADMIN:
                case VISITOR: {
                    loadList = GetInformationService.viewFreeLoadListService();
                } break;
                case CLIENT: {
                    String login = args[0];
                    loadList = GetInformationService.viewClientLoadListService(login);
                } break;
                case DISPATCHER: {

                }
            }
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }
        return loadList;
    }

    public static List<Transport> viewTransportListController(UserType userType, String... args) throws ControllerException {
        List<Transport> transportList = new ArrayList<>();
        try {
            switch (userType) {
                case ADMIN:
                case VISITOR: {
                    transportList = GetInformationService.viewFreeTransportListService();
                } break;
                case CLIENT: {
                    String login = args[0];
                    transportList = GetInformationService.viewClientTransportListService(login);
                } break;
                case DISPATCHER: {

                }
            }
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }
        return transportList;
    }

    public static String loginUserController(HttpServletRequest request) throws ControllerException {
        String result = "";
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (login == null || password == null ||
                login.equals("") || password.equals(""))
                    throw new ControllerException("Not all fields are field!");
            UserType userType = GetInformationService.loginUserService(login, password);
            switch (userType) {
                case ADMIN: {
                    request.getSession().setAttribute("login", login);
                    request.getSession().setAttribute("user_type", userType);
                    result = "Success admin!";
                } break;
                case DISPATCHER: {
                    request.getSession().setAttribute("login", login);
                    request.getSession().setAttribute("user_type", userType);
                    result = "Success dispatcher!";
                } break;
                case CLIENT: {
                    request.getSession().setAttribute("login", login);
                    request.getSession().setAttribute("user_type", userType);
                    result = "Success client!";
                }
            }
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }
        return result;
    }

    public static List<User> viewAllUsersController(HttpServletRequest request) throws ControllerException {
        List<User> userList;
        try {
            String login = (String) request.getSession().getAttribute("login");
            userList = GetInformationService.viewAllUsersService(login);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }
        return userList;
    }
}

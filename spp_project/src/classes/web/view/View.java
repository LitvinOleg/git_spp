package classes.web.view;

import classes.web.controller.ModificationController;
import classes.web.controller.GetInformationController;
import classes.web.controller.exception.ControllerException;
import classes.web.entity.Load;
import classes.web.entity.Transport;
import classes.web.entity.user.User;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static classes.web.entity.user.User.*;

public class View {
    public static String loginUserView(HttpServletRequest request) {
        String result = "";
        if (request.getParameter("enter") != null) {
            try {
                result =  GetInformationController.loginUserController(request);
            } catch (ControllerException ex) {
                result =  ex.getMessage();
            }
        }
        return result;
    }
    public static String registerUserView(HttpServletRequest request, UserType userType) {
        String result = "";
        try {
            if (request.getParameter("registration") != null && userType != null)
                result = ModificationController.AddNewUserController(request, userType);
        } catch (ControllerException ex) {
            result = ex.getMessage();
        }
        return result;
    }




    public static String removeUserView(HttpServletRequest request) {
        String result = "";
        try {
            if (request.getParameter("delete") != null)
                result = ModificationController.removeUserController(request);
        } catch (ControllerException ex) {
            result = ex.getMessage();
        }
        return result;
    }
    public static String removeLoadView(HttpServletRequest request) {
        try {
            if (request.getParameter("load_id") == null ||
                request.getParameter("delete_load") == null ||
                request.getParameter("load_id").equals(""))
                    return "";
            else
                return ModificationController.removeLoadController(request);
        } catch (ControllerException ex) {
            return ex.getMessage();
        }
    }
    public static String removeTransportView(HttpServletRequest request) {
        try {
            if (request.getParameter("state_number") == null ||
                    request.getParameter("delete_transport") == null ||
                    request.getParameter("state_number").equals(""))
                return "";
            else
                return ModificationController.removeTransportController(request);
        } catch (ControllerException ex) {
            return ex.getMessage();
        }
    }



    public static String addFreeLoadView(HttpServletRequest request) {
        try {
            if (request.getParameter("add_free_load") == null)
                return "";
            else
                return ModificationController.addFreeLoadController(request);
        } catch (ControllerException ex) {
            return ex.getMessage();
        }
    }
    public static String addClientLoadView(HttpServletRequest request) {
        try {
            if (request.getParameter("add_load") == null ||
                request.getParameter("load_id") == null ||
                request.getParameter("load_id").equals(""))
                    return "";
            else
                return ModificationController.addClientLoadController(request);
        } catch (ControllerException ex) {
            return ex.getMessage();
        }
    }
    public static String addFreeTransportView(HttpServletRequest request) {
        try {
            if (request.getParameter("add_free_transport") == null)
                return "";
            else
                return ModificationController.addFreeTransportController(request);
        } catch (ControllerException ex) {
            return ex.getMessage();
        }
    }
    public static String addClientTransportView(HttpServletRequest request) {
        try {
            if (request.getParameter("add_transport") == null ||
                    request.getParameter("state_number") == null ||
                    request.getParameter("state_number").equals(""))
                return "";
            else
                return ModificationController.addClientTransportController(request);
        } catch (ControllerException ex) {
            return ex.getMessage();
        }
    }



    public static List<Transport> viewFreeTransportList() {
        try {
            return GetInformationController.viewTransportListController(UserType.VISITOR);
        } catch (ControllerException ex) {
            return new ArrayList<>();
        }
    }
    public static List<Transport> viewClientTransportList(String login) {
        try {
            return GetInformationController.viewTransportListController(UserType.CLIENT, login);
        } catch (ControllerException ex) {
            return new ArrayList<>();
        }
    }
    public static List<User> viewAllUsersView(HttpServletRequest request) {
        try {
            return GetInformationController.viewAllUsersController(request);
        } catch (ControllerException ex) {
            return new ArrayList<>();
        }
    }
    public static List<Load> viewFreeLoadList() {
        try {
            return GetInformationController.viewLoadListController(UserType.VISITOR);
        } catch (ControllerException ex) {
            return new ArrayList<>();
        }
    }
    public static List<Load> viewClientLoadList(String login) {
        try {
            return GetInformationController.viewLoadListController(UserType.CLIENT, login);
        } catch (ControllerException ex) {
            return new ArrayList<>();
        }
    }
}


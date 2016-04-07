package classes.web.view;

import classes.web.controller.ModificationController;
import classes.web.controller.ShowInformationController;
import classes.web.controller.exception.ControllerException;
import classes.web.entity.Load;
import classes.web.entity.user.User;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олег on 01.04.2016.
 */
public class View {

    /**
     * Registers the client
     * @param request - http request of client
     * @throws IOException
     */
    public static String registerUserView(HttpServletRequest request,
                                        User.UserType userType) {
        String result = "";
        if (request.getParameter("registration") != null)
            result = ModificationController.AddNewUserController(request, userType);
        return result;
    }

    /**
     * Views free load
     * @return list of free loads
     */
    public static List<Load> viewFreeLoadList() {

        try {
            return ShowInformationController.viewLoadListController(User.UserType.VISITOR);
        } catch (ControllerException ex) {
            return new ArrayList<>();
        }
    }

    /**
     *
     * @return
     */
    public static List<String> viewFreeOrderList() {
        ArrayList<String> freeOrderList = new ArrayList<>();

        return freeOrderList;
    }
}

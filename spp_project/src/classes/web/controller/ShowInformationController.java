package classes.web.controller;

import classes.web.controller.exception.ControllerException;
import classes.web.entity.Load;
import classes.web.model.service.exception.ServiceException;
import classes.web.model.service.ShowInformationService;

import java.util.ArrayList;
import java.util.List;

import static classes.web.entity.user.User.*;

/**
 * Created by Олег on 07.04.2016.
 */
public class ShowInformationController {

    /**
     * Creates loads list depends on user type
     * @param userType
     * @return loads list
     * @throws ControllerException
     */
    public static List<Load> viewLoadListController(UserType userType) throws ControllerException {
        List<Load> loadList = new ArrayList<>();
        try {
            switch (userType) {
                case ADMIN:
                case VISITOR: {
                    loadList = ShowInformationService.viewFreeLoadListService();
                } break;
                case CLIENT: {

                } break;
                case DISPATCHER: {

                }
            }
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

        return loadList;
    }
}

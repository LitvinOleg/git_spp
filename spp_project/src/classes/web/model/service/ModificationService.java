package classes.web.model.service;

import classes.web.entity.user.User;
import classes.web.model.dao.ModificationDao;

import java.io.IOException;

/**
 * Created by Олег on 06.04.2016.
 */
public class ModificationService {

    public static boolean addNewUserService(User user) throws Exception {
        boolean result = false;

        try {
            result = ModificationDao.addNewUser(user);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

        return result;
    }
}

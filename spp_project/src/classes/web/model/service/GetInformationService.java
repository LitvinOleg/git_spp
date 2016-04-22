package classes.web.model.service;

import classes.web.entity.Load;
import classes.web.entity.Transport;
import classes.web.entity.user.User;
import classes.web.entity.user.User.UserType;
import classes.web.model.dao.exception.DaoException;
import classes.web.model.dao.GetInformationDao;
import classes.web.model.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Shows some information
 */
public class GetInformationService {

    public static List<Load> viewFreeLoadListService() throws ServiceException {
        List<Load> loadList;
        try {
            loadList = GetInformationDao.selectAllFreeLoads();
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return loadList;
    }


    public static List<Load> viewClientLoadListService(String login) throws ServiceException {
        List<Load> loadList;
        try {
            loadList = GetInformationDao.selectAllClientLoads(login);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return loadList;
    }


    public static List<Transport> viewFreeTransportListService() throws ServiceException {
        List<Transport> transportList;
        try {
            transportList = GetInformationDao.selectAllFreeTransports();
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return transportList;
    }


    public static List<Transport> viewClientTransportListService(String login) throws ServiceException {
        List<Transport> transportList;
        try {
            transportList = GetInformationDao.selectAllClientTransports(login);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return transportList;
    }

    public static List<User> viewAllUsersService(String login) throws ServiceException {
        List<User> userList;
        try {
            userList = GetInformationDao.selectAllUsers(login);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return userList;
    }


    public static UserType loginUserService(String login, String password) throws ServiceException {
        try {
            int userType = GetInformationDao.loginUserDao(login, password);
            switch (userType) {
                case 1: return UserType.ADMIN;
                case 2: return UserType.DISPATCHER;
                case 3: return UserType.CLIENT;
                default: return UserType.VISITOR;
            }
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}

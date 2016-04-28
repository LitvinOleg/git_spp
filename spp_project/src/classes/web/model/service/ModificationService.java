package classes.web.model.service;

import classes.web.entity.Load;
import classes.web.entity.Transport;
import classes.web.entity.user.Admin;
import classes.web.entity.user.Client;
import classes.web.entity.user.Dispatcher;
import classes.web.model.dao.ModificationDao;
import classes.web.model.dao.exception.DaoException;
import classes.web.model.service.exception.ServiceException;

/**
 * Modifies information
 */
public class ModificationService {
    public static boolean addNewClientService(Client client) throws ServiceException {
        boolean result;
        try {
            result = ModificationDao.insertNewClient(client);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return result;
    }
    public static boolean addNewDispatcherService(Dispatcher dispatcher) throws ServiceException {
        boolean result;
        try {
            result = ModificationDao.insertNewDispatcher(dispatcher);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return result;
    }
    public static boolean addNewAdminService(Admin admin) throws ServiceException {
        boolean result;
        try {
            result = ModificationDao.insertNewAdmin(admin);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return result;
    }
    public static String addClientLoadService(int loadID, String login) throws ServiceException {
        try {
            if (ModificationDao.insertClientLoad(loadID, login))
                return String.format("The load - %s added!", loadID);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return "";
    }
    public static boolean addFreeLoadService(Load load) throws ServiceException {
        try {
            return ModificationDao.insertFreeLoad(load);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    public static String addClientTransportService(int state_number, String login) throws ServiceException {
        try {
            if (ModificationDao.insertClientTransport(state_number, login))
                return String.format("The transport - %s added!", state_number);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return "";
    }
    public static boolean addFreeTransportService(Transport transport) throws  ServiceException {
        try {
            return ModificationDao.insertFreeTransport(transport);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public static boolean updateLoadService(Load load) throws ServiceException {
        try {
            return ModificationDao.updateLoad(load);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    public static boolean updateTransportService(Transport transport) throws ServiceException {
        try {
            return ModificationDao.updateTransport(transport);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public static boolean removeUserService(String login) throws ServiceException {
        boolean result;
        try {
            result = ModificationDao.deleteUser(login);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return result;
    }
    public static String removeClientLoadService(int loadID, String login) throws ServiceException {
        try {
            if (ModificationDao.deleteClientLoad(loadID, login))
                return String.format("The load - %s removed!", loadID);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return "";
    }
    public static String removeFreeLoadService(int loadID) throws ServiceException {
        try {
            if (ModificationDao.deleteFreeLoad(loadID))
                return String.format("The load - %s removed!", loadID);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return "";
    }
    public static String removeClientTransportService(int state_number, String login) throws ServiceException {
        try {
            if (ModificationDao.deleteClientTransport(state_number, login))
                return String.format("The transport - %s removed!", state_number);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return "";
    }
    public static String removeFreeTransportService(int state_number) throws ServiceException {
        try {
            if (ModificationDao.deleteFreeTransport(state_number))
                return String.format("The transport - %s removed!", state_number);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return "";
    }
}

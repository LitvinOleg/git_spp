package classes.web.model.service;

import classes.web.entity.Load;
import classes.web.model.dao.exception.DaoException;
import classes.web.model.dao.ShowInformationDao;
import classes.web.model.service.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олег on 07.04.2016.
 */
public class ShowInformationService {

    /**
     * Creates list of free loads
     * @return free loads list
     * @throws ServiceException
     */
    public static List<Load> viewFreeLoadListService() throws ServiceException {
        List<Load> loadList = new ArrayList<>();
        try
        {
            loadList = ShowInformationDao.selectAllFreeLoads();
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return loadList;
    }
}

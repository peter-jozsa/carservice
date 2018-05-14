package hu.unideb.inf.lev.carservice.dao;

import hu.unideb.inf.lev.carservice.dao.impl.CarDAOImpl;
import hu.unideb.inf.lev.carservice.dao.impl.JobTypeDAOImpl;
import hu.unideb.inf.lev.carservice.dao.impl.PersonDAOImpl;
import hu.unideb.inf.lev.carservice.dao.impl.WorksheetDAOImpl;

/**
 * Factory class which provides static methods to instantiate new DAOs.
 * It also hides the implementation of the interface.
 */
public final class DAOFactory {
    /**
     * Creates a new {@link PersonDAO}.
     * @return An object which implements {@link PersonDAO}.
     */
    public static PersonDAO createPersonDAO() {
        return new PersonDAOImpl();
    }

    /**
     * Creates a new {@link CarDAO}.
     * @return An object which implements {@link CarDAO}.
     */
    public static CarDAO createCarDAO() {
        return new CarDAOImpl();
    }

    /**
     * Creates a new {@link JobTypeDAO}.
     * @return An object which implements {@link JobTypeDAO}.
     */
    public static JobTypeDAO createJobTypeDAO() {
        return new JobTypeDAOImpl();
    }

    /**
     * Creates a new {@link WorksheetDAO}.
     * @return An object which implements {@link WorksheetDAO}.
     */
    public static WorksheetDAO createWorksheetDAO() {
        return new WorksheetDAOImpl();
    }
}

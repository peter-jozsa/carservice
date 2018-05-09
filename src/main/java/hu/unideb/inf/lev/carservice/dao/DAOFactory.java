package hu.unideb.inf.lev.carservice.dao;

import hu.unideb.inf.lev.carservice.dao.impl.CarDAOImpl;
import hu.unideb.inf.lev.carservice.dao.impl.JobTypeDAOImpl;
import hu.unideb.inf.lev.carservice.dao.impl.PersonDAOImpl;

public final class DAOFactory {
    public static PersonDAO createPersonDAO() {
        return new PersonDAOImpl();
    }

    public static CarDAO createCarDAO() {
        return new CarDAOImpl();
    }

    public static JobTypeDAO createJobTypeDAO() {
        return new JobTypeDAOImpl();
    }
}

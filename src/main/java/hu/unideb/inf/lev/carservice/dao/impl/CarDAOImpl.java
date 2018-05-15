package hu.unideb.inf.lev.carservice.dao.impl;

import hu.unideb.inf.lev.carservice.dao.CarDAO;
import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.utility.EntityManagerFactoryHelper;

import java.util.List;

/**
 * This class implements the {@link CarDAO} interface.
 */
public class CarDAOImpl extends GenericDAOImpl<Car, Long> implements CarDAO {
    @Override
    public List<Car> findByString(String str) {
        return EntityManagerFactoryHelper.getEntityManager()
                .createQuery("SELECT c FROM Car c WHERE lower(c.registrationNumber) LIKE lower('%" + str + "%') OR lower(c.VIN) LIKE lower('%" + str + "%')", Car.class)
                .getResultList();
    }
}

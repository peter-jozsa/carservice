package hu.unideb.inf.lev.carservice.dao.impl;

import hu.unideb.inf.lev.carservice.dao.JobTypeDAO;
import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.utility.EntityManagerFactoryHelper;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * This class implements the {@link JobTypeDAO} interface.
 */
public class JobTypeDAOImpl extends GenericDAOImpl<JobType, Long> implements JobTypeDAO {
    @Override
    public List<JobType> findByName(String name) {
        EntityManager entityManager = EntityManagerFactoryHelper.getEntityManager();

        return entityManager
                .createQuery("SELECT j FROM JobType j WHERE lower(j.name) LIKE lower('%" + name + "%')", JobType.class)
                .getResultList();
    }
}

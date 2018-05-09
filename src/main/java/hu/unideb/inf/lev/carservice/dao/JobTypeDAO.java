package hu.unideb.inf.lev.carservice.dao;

import hu.unideb.inf.lev.carservice.dao.GenericDAO;
import hu.unideb.inf.lev.carservice.model.JobType;

import java.util.List;

public interface JobTypeDAO extends GenericDAO<JobType, Long> {
    List<JobType> findByName(String name);
}

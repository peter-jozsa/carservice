package hu.unideb.inf.lev.carservice.service.impl;

import hu.unideb.inf.lev.carservice.dao.DAOFactory;
import hu.unideb.inf.lev.carservice.dao.JobTypeDAO;
import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.service.JobTypeService;
import hu.unideb.inf.lev.carservice.service.exception.EmptyFieldValueException;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;

import java.util.List;

/**
 * A service class implementation which manages {@link JobType} entities.
 */
public class JobTypeServiceImpl implements JobTypeService {
    private JobTypeDAO jobTypeDAO = DAOFactory.createJobTypeDAO();

    @Override
    public JobType getJobTypeById(Long id) {
        return jobTypeDAO.findById(id);
    }

    @Override
    public void createJobType(JobType jobType) throws ValidationException {
        if (validateJobType(jobType)) {
            jobTypeDAO.create(jobType);
        }
    }

    @Override
    public void updateJobType(JobType jobType) throws ValidationException, EntityNotFoundException {
        if (validateJobType(jobType)) {
            JobType entity = getJobTypeById(jobType.getId());
            if (entity == null) {
                throw new EntityNotFoundException("JobType entity (id=" + jobType.getId() + ") does not exist!");
            }

            entity.setName(jobType.getName());

            jobTypeDAO.update(entity);
        }
    }

    @Override
    public boolean validateJobType(JobType jobType) throws ValidationException {
        if (jobType.getName() == null || jobType.getName().trim().isEmpty()) {
            throw new EmptyFieldValueException(JobType.class, "name");
        }
        return true;
    }

    @Override
    public void deleteJobTypeById(Long id) throws EntityNotFoundException {
        JobType entity = getJobTypeById(id);
        if (entity == null) {
            throw new EntityNotFoundException("JobType entity (id=" + id + ") does not exist!");
        }

        jobTypeDAO.delete(entity);
    }

    @Override
    public List<JobType> getAllJobType() {
        return jobTypeDAO.getAll();
    }

    @Override
    public List<JobType> textSearchJobType(String str) {
        return jobTypeDAO.findByName(str);
    }
}

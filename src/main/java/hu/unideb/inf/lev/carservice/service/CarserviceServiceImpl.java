package hu.unideb.inf.lev.carservice.service;

import hu.unideb.inf.lev.carservice.dao.CarDAO;
import hu.unideb.inf.lev.carservice.dao.DAOFactory;
import hu.unideb.inf.lev.carservice.dao.JobTypeDAO;
import hu.unideb.inf.lev.carservice.dao.PersonDAO;
import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.service.exception.EmptyFieldValueException;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;

import java.util.List;

public class CarserviceServiceImpl implements CarserviceService {
    private CarDAO carDAO = DAOFactory.createCarDAO();
    private PersonDAO personDAO = DAOFactory.createPersonDAO();
    private JobTypeDAO jobTypeDAO = DAOFactory.createJobTypeDAO();

    @Override
    public Person getPersonsById(Long id) {
        return personDAO.findById(id);
    }

    @Override
    public void createPerson(Person person) throws ValidationException {
        if (validatePerson(person)) {
            personDAO.create(person);
        }
    }

    @Override
    public void updatePerson(Person person) throws ValidationException, EntityNotFoundException {
        if (validatePerson(person)) {
            Person personEntity = getPersonsById(person.getId());
            if (personEntity == null) {
                throw new EntityNotFoundException("Person entity (id=" + person.getId() + ") does not exist!");
            }

            personEntity.setFirstName(person.getFirstName());
            personEntity.setLastName(person.getLastName());
            personEntity.setPhone(person.getPhone());
            personEntity.setAddress(person.getAddress());

            personDAO.update(personEntity);
        }
    }



    @Override
    public List<Person> getAllPerson() {
        return personDAO.getAll();
    }

    @Override
    public boolean validatePerson(Person person) throws ValidationException {
        if (person.getFirstName() == null || person.getFirstName().trim().isEmpty()) {
            throw new EmptyFieldValueException(Person.class, "firstName");
        }
        if (person.getLastName() == null || person.getLastName().trim().isEmpty()) {
            throw new EmptyFieldValueException(Person.class, "lastName");
        }
        if (person.getPhone() == null || person.getPhone().trim().isEmpty()) {
            throw new EmptyFieldValueException(Person.class, "phone");
        }
        if (person.getAddress() == null) {
            throw new EmptyFieldValueException(Person.class, "address");
        }
        return true;
    }

    @Override
    public List<Person> textSearchPerson(String str) {
        return personDAO.findAllByName(str);
    }

    @Override
    public void deletePersonById(Long id) throws EntityNotFoundException {
        Person entity = getPersonsById(id);
        if (entity == null) {
            throw new EntityNotFoundException("Person entity (id=" + id + ") does not exist!");
        }

        personDAO.delete(entity);
    }

    @Override
    public JobType getJobTypeById(Long id) {
        return jobTypeDAO.findById(id);
    }

    @Override
    public void createJobType(JobType jobType) throws ValidationException {
        jobTypeDAO.create(jobType);
    }

    @Override
    public void updateJobType(JobType jobType) throws ValidationException, EntityNotFoundException {
        JobType entity = getJobTypeById(jobType.getId());
        if (entity == null) {
            throw new EntityNotFoundException("JobType entity (id=" + jobType.getId() + ") does not exist!");
        }

        entity.setName(jobType.getName());

        jobTypeDAO.update(entity);
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

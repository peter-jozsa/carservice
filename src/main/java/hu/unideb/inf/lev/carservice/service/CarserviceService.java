package hu.unideb.inf.lev.carservice.service;

import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.service.exception.EmptyFieldValueException;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;

import java.util.List;

public interface CarserviceService {
    Person getPersonsById(Long id);
    void createPerson(Person person) throws ValidationException;
    void updatePerson(Person person) throws ValidationException, EntityNotFoundException;
    void deletePersonById(Long id) throws EntityNotFoundException;
    boolean validatePerson(Person person) throws ValidationException;
    List<Person> getAllPerson();
    List<Person> textSearchPerson(String str);

    JobType getJobTypeById(Long id);
    void createJobType(JobType jobType) throws ValidationException;
    void updateJobType(JobType jobType) throws ValidationException, EntityNotFoundException;
    void deleteJobTypeById(Long id) throws EntityNotFoundException;
    List<JobType> getAllJobType();
    List<JobType> textSearchJobType(String str);
}

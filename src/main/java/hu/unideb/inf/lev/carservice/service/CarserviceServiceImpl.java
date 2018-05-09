package hu.unideb.inf.lev.carservice.service;

import hu.unideb.inf.lev.carservice.dao.CarDAO;
import hu.unideb.inf.lev.carservice.dao.DAOFactory;
import hu.unideb.inf.lev.carservice.dao.PersonDAO;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;

import java.util.List;

public class CarserviceServiceImpl implements CarserviceService {
    private CarDAO carDAO = DAOFactory.createCarDAO();
    private PersonDAO personDAO = DAOFactory.createPersonDAO();

    @Override
    public Person getPersonsById(Long id) {
        return personDAO.findById(id);
    }

    @Override
    public void createPerson(Person person) {
        personDAO.persist(person);
    }

    @Override
    public void updatePerson(Person person) {
        Person personEntity = getPersonsById(person.getId());
        if (personEntity == null) {
            throw new EntityNotFoundException("Person entity (id=" + person.getId() + ") does not exist!");
        }

        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity.setPhone(person.getPhone());
        personEntity.setAddress(person.getAddress());

        personDAO.persist(personEntity);
    }

    @Override
    public List<Person> getAllPerson() {
        return personDAO.getAll();
    }

    @Override
    public List<Person> findPersonsByName() {
        return null;
    }
}

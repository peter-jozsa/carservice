package hu.unideb.inf.lev.carservice.service.impl;

import hu.unideb.inf.lev.carservice.dao.DAOFactory;
import hu.unideb.inf.lev.carservice.dao.PersonDAO;
import hu.unideb.inf.lev.carservice.model.Address;
import hu.unideb.inf.lev.carservice.model.Discount;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.service.PersonService;
import hu.unideb.inf.lev.carservice.service.WorksheetService;
import hu.unideb.inf.lev.carservice.service.exception.EmptyFieldValueException;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.InvalidFieldValueException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A service class implementation which manages {@link Person} entities.
 */
public class PersonServiceImpl implements PersonService {
    private PersonDAO personDAO = DAOFactory.createPersonDAO();

    @Override
    public Person getPersonsById(Long id) {
        Person p = personDAO.findById(id);

        if (p != null) {
            removeExpiredDiscount(p);
            return p;
        }
        return null;
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
            personEntity.setDiscount(person.getDiscount());

            personDAO.update(personEntity);
        }
    }



    @Override
    public List<Person> getAllPerson() {
        List<Person> list = personDAO.getAll();

        list.stream()
                .forEach(person -> removeExpiredDiscount(person));

        return list;
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

        Address addr = person.getAddress();
        if (addr == null) {
            throw new EmptyFieldValueException(Person.class, "address");
        }

        if (addr.getCountry() == null || addr.getCountry().trim().isEmpty()) {
            throw new EmptyFieldValueException(Address.class, "country");
        }

        if (addr.getZip() == 0) {
            throw new EmptyFieldValueException(Address.class, "zip");
        }

        if (addr.getCity() == null || addr.getCity().trim().isEmpty()) {
            throw new EmptyFieldValueException(Address.class, "city");
        }

        if (addr.getAddressLine() == null || addr.getAddressLine().trim().isEmpty()) {
            throw new EmptyFieldValueException(Address.class, "addressLine");
        }

        Discount discount = person.getDiscount();
        if(discount != null) {
            if (discount.getValue() < 0 || discount.getValue() > WorksheetService.DISCOUNT_MAX_VALUE) {
                throw new InvalidFieldValueException(Discount.class, "value");
            }
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
    public void increaseDiscountOfPerson(Person person) {
        Discount discount = person.getDiscount();

        if (discount != null) {
            discount.setValue(discount.getValue() + WorksheetService.DISCOUNT_STEP_VALUE);
            discount.setValidUntil(LocalDateTime.now().plusSeconds(WorksheetService.DISCOUNT_VALIDITY_TIME));

            if (discount.getValue() >= WorksheetService.DISCOUNT_MAX_VALUE) {
                discount.setValue(WorksheetService.DISCOUNT_MAX_VALUE);
            }
        } else {
            discount = new Discount(WorksheetService.DISCOUNT_STEP_VALUE);
        }

        discount.setValidUntil(LocalDateTime.now().plusSeconds(WorksheetService.DISCOUNT_VALIDITY_TIME));
        person.setDiscount(discount);
    }

    @Override
    public void removeExpiredDiscount(Person person) {
        if (person != null &&
                person.getDiscount() != null &&
                person.getDiscount().getValidUntil() != null &&
                LocalDateTime.now().isAfter(person.getDiscount().getValidUntil())
                ) {
            person.setDiscount(null);
        }
    }
}

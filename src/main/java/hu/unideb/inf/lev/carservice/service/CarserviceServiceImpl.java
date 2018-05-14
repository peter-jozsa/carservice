package hu.unideb.inf.lev.carservice.service;

import hu.unideb.inf.lev.carservice.dao.*;
import hu.unideb.inf.lev.carservice.model.*;
import hu.unideb.inf.lev.carservice.service.exception.EmptyFieldValueException;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.InvalidFieldValueException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CarserviceServiceImpl implements CarserviceService {
    private static final int DISCOUNT_VALIDITY_TIME = 30*24*60*60;
    private static final int DISCOUNT_MAX_VALUE = 40;
    private static final int DISCOUNT_STEP_VALUE = 5;

    private CarDAO carDAO = DAOFactory.createCarDAO();
    private PersonDAO personDAO = DAOFactory.createPersonDAO();
    private JobTypeDAO jobTypeDAO = DAOFactory.createJobTypeDAO();
    private WorksheetDAO worksheetDAO = DAOFactory.createWorksheetDAO();

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
            personEntity.setDiscount(person.getDiscount());

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
            if (discount.getValue() < 0 || discount.getValue() > DISCOUNT_MAX_VALUE) {
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

    @Override
    public Car getCarById(Long id) {
        return carDAO.findById(id);
    }

    @Override
    public void createCar(Car car) throws ValidationException {
            if (validateCar(car)) {
                carDAO.create(car);
            }
    }

    @Override
    public void updateCar(Car car) throws ValidationException, EntityNotFoundException {
        if (validateCar(car)) {
            Car entity = getCarById(car.getId());
            if (entity == null) {
                throw new EntityNotFoundException("Car entity (id=" + car.getId() + ") does not exist!");
            }

            entity.setRegistrationNumber(car.getRegistrationNumber());
            entity.setBrand(car.getBrand());
            entity.setType(car.getType());
            entity.setVIN(car.getVIN());
            entity.setOwner(car.getOwner());

            carDAO.update(entity);
        }
    }

    @Override
    public void deleteCarById(Long id) throws EntityNotFoundException {
        Car entity = getCarById(id);
        if (entity == null) {
            throw new EntityNotFoundException("Car entity (id=" + id + ") does not exist!");
        }

        carDAO.delete(entity);
    }

    @Override
    public boolean validateCar(Car car) throws ValidationException {
        if (car.getRegistrationNumber() == null || car.getRegistrationNumber().trim().isEmpty()) {
            throw new EmptyFieldValueException(Address.class, "registrationNumber");
        }
        if (car.getBrand() == null || car.getBrand().trim().isEmpty()) {
            throw new EmptyFieldValueException(Address.class, "brand");
        }
        if (car.getType() == null || car.getType().trim().isEmpty()) {
            throw new EmptyFieldValueException(Address.class, "type");
        }
        if (car.getVIN() == null || car.getVIN().trim().isEmpty()) {
            throw new EmptyFieldValueException(Address.class, "VIN");
        }
        if (car.getOwner() == null) {
            throw new EmptyFieldValueException(Address.class, "owner");
        }
        return true;
    }

    @Override
    public List<Car> getAllCar() {
        return carDAO.getAll();
    }

    @Override
    public List<Car> textSearchCar(String str) {
        return null;
    }

    @Override
    public Worksheet getWorksheetById(Long id) {
        return worksheetDAO.findById(id);
    }

    @Override
    public void createWorksheet(Worksheet worksheet) throws ValidationException {
        if (validateWorksheet(worksheet)) {
            worksheet.setCreationDate(LocalDateTime.now());
            if (worksheet.getCar().getOwner().getDiscount() != null) {
                worksheet.setDiscount(worksheet.getCar().getOwner().getDiscount().getValue());
            }

            worksheetDAO.create(worksheet);
            increaseDiscountOfPerson(worksheet.getCar().getOwner());
        }
    }

    @Override
    public void updateWorksheet(Worksheet worksheet) throws ValidationException, EntityNotFoundException {
        if (validateWorksheet(worksheet)) {
            Worksheet entity = worksheetDAO.findById(worksheet.getId());
            if (entity == null) {
                throw new EntityNotFoundException("Worksheet entity (id=" + worksheet.getId() + ") does not exist!");
            }

            entity.setCar(worksheet.getCar());
            entity.setJobs(worksheet.getJobs());
            entity.setTotal(worksheet.getTotal());
            entity.setDiscount(worksheet.getDiscount());

            worksheetDAO.update(entity);
        }
    }

    @Override
    public void deleteWorksheetById(Long id) throws EntityNotFoundException {
        Worksheet entity = worksheetDAO.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException("Worksheet entity (id=" + id + ") does not exist!");
        }

        worksheetDAO.delete(entity);
    }

    @Override
    public boolean validateWorksheet(Worksheet worksheet) throws ValidationException {
        if (worksheet == null) {
            return false;
        }

        if (worksheet.getCar() == null) {
            throw new EmptyFieldValueException(Worksheet.class, "car");
        }

        if (worksheet.getDiscount() < 0 || worksheet.getDiscount() > 40) {
            throw new InvalidFieldValueException(Worksheet.class, "discount");
        }

        if (worksheet.getTotal() < 0) {
            throw new InvalidFieldValueException(Worksheet.class, "total");
        }

        return true;
    }

    @Override
    public List<Worksheet> getAllWorksheet() {
        return worksheetDAO.getAll();
    }

    @Override
    public List<Worksheet> textSearchWorksheet(String str) {
        return null;
    }

    private void increaseDiscountOfPerson(Person person) throws ValidationException {
        Discount discount = person.getDiscount();

        if (discount != null) {
            discount.setValue(discount.getValue() + DISCOUNT_STEP_VALUE);
            discount.setValidUntil(LocalDateTime.now().plusSeconds(DISCOUNT_VALIDITY_TIME));

            if (discount.getValue() >= DISCOUNT_MAX_VALUE) {
                discount.setValue(DISCOUNT_MAX_VALUE);
            }
        } else {
            discount = new Discount(DISCOUNT_STEP_VALUE);
        }

        discount.setValidUntil(LocalDateTime.now().plusSeconds(DISCOUNT_VALIDITY_TIME));
        person.setDiscount(discount);

        try {
            updatePerson(person);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
    }
}

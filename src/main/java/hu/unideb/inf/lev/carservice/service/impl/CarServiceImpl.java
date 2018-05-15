package hu.unideb.inf.lev.carservice.service.impl;

import hu.unideb.inf.lev.carservice.dao.CarDAO;
import hu.unideb.inf.lev.carservice.dao.DAOFactory;
import hu.unideb.inf.lev.carservice.model.Address;
import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.service.CarService;
import hu.unideb.inf.lev.carservice.service.exception.EmptyFieldValueException;
import hu.unideb.inf.lev.carservice.service.exception.EntityNotFoundException;
import hu.unideb.inf.lev.carservice.service.exception.ValidationException;

import java.util.List;

/**
 * A service class implementation which manages {@link Car} entities.
 */
public class CarServiceImpl implements CarService {
    private CarDAO carDAO = DAOFactory.createCarDAO();

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
            throw new EmptyFieldValueException(Car.class, "registrationNumber");
        }
        if (car.getBrand() == null || car.getBrand().trim().isEmpty()) {
            throw new EmptyFieldValueException(Car.class, "brand");
        }
        if (car.getType() == null || car.getType().trim().isEmpty()) {
            throw new EmptyFieldValueException(Car.class, "type");
        }
        if (car.getVIN() == null || car.getVIN().trim().isEmpty()) {
            throw new EmptyFieldValueException(Car.class, "VIN");
        }
        if (car.getOwner() == null) {
            throw new EmptyFieldValueException(Car.class, "owner");
        }
        return true;
    }

    @Override
    public List<Car> getAllCar() {
        return carDAO.getAll();
    }

    @Override
    public List<Car> textSearchCar(String str) {
        return carDAO.findByString(str);
    }
}

package hu.unideb.inf.lev.carservice.utility.converter;

import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.viewmodel.CarViewModel;

/**
 * A converter which manages the conversions between {@link Car} entities
 * and {@link CarViewModel} view models.
 */
public interface CarConverter extends GenericConverter<Car, CarViewModel> {
}

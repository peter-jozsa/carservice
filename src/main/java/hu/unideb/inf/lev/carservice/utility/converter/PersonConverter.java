package hu.unideb.inf.lev.carservice.utility.converter;

import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.viewmodel.PersonViewModel;

/**
 * A converter class which manages the conversions between {@link Person} entities
 * and {@link PersonViewModel} view models.
 */
public interface PersonConverter extends GenericConverter<Person, PersonViewModel> {
}

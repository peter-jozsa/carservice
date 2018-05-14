package hu.unideb.inf.lev.carservice.utility.converter;

import hu.unideb.inf.lev.carservice.model.Address;
import hu.unideb.inf.lev.carservice.viewmodel.AddressViewModel;

/**
 * A converter which manages the conversions between {@link Address} entities
 * and {@link AddressViewModel} view models.
 */
public interface AddressConverter extends GenericConverter<Address, AddressViewModel> {
}

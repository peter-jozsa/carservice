package hu.unideb.inf.lev.carservice.utility.converter;

import hu.unideb.inf.lev.carservice.model.Address;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.utility.converter.impl.AddressConverterImpl;
import hu.unideb.inf.lev.carservice.utility.converter.impl.PersonConverterImpl;
import hu.unideb.inf.lev.carservice.viewmodel.AddressViewModel;
import hu.unideb.inf.lev.carservice.viewmodel.PersonViewModel;

public class ConverterHelper {
    private static PersonConverter personConverter = new PersonConverterImpl();
    private static AddressConverter addressConverter = new AddressConverterImpl();

    public static PersonViewModel fromModel(Person model) {
        return personConverter.fromModel(model);
    }

    public static AddressViewModel fromModel(Address model) {
        return addressConverter.fromModel(model);
    }
}

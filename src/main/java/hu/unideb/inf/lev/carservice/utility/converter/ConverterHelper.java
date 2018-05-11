package hu.unideb.inf.lev.carservice.utility.converter;

import hu.unideb.inf.lev.carservice.model.Address;
import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.model.JobType;
import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.utility.converter.impl.AddressConverterImpl;
import hu.unideb.inf.lev.carservice.utility.converter.impl.CarConverterImpl;
import hu.unideb.inf.lev.carservice.utility.converter.impl.JobTypeConverterImpl;
import hu.unideb.inf.lev.carservice.utility.converter.impl.PersonConverterImpl;
import hu.unideb.inf.lev.carservice.viewmodel.AddressViewModel;
import hu.unideb.inf.lev.carservice.viewmodel.CarViewModel;
import hu.unideb.inf.lev.carservice.viewmodel.JobTypeViewModel;
import hu.unideb.inf.lev.carservice.viewmodel.PersonViewModel;

import java.util.List;

public class ConverterHelper {
    private static PersonConverter personConverter = new PersonConverterImpl();
    private static AddressConverter addressConverter = new AddressConverterImpl();
    private static JobTypeConverter jobTypeConverter = new JobTypeConverterImpl();
    private static CarConverter carConverter = new CarConverterImpl();

    public static PersonViewModel fromModel(Person model) {
        return personConverter.fromModel(model);
    }

    public static AddressViewModel fromModel(Address model) {
        return addressConverter.fromModel(model);
    }

    public static JobTypeViewModel fromModel(JobType model) {
        return jobTypeConverter.fromModel(model);
    }

    public static CarViewModel fromModel(Car car) {
        return carConverter.fromModel(car);
    }

    public static Person toModel(PersonViewModel viewModel) {
        return personConverter.toModel(viewModel);
    }

    public static Address toModel(AddressViewModel viewModel) {
        return addressConverter.toModel(viewModel);
    }

    public static JobType toModel(JobTypeViewModel viewModel) {
        return jobTypeConverter.toModel(viewModel);
    }

    public static Car toModel(CarViewModel viewModel) {
        return carConverter.toModel(viewModel);
    }
}

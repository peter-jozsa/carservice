package hu.unideb.inf.lev.carservice.utility.converter;

import hu.unideb.inf.lev.carservice.model.*;
import hu.unideb.inf.lev.carservice.utility.converter.impl.*;
import hu.unideb.inf.lev.carservice.viewmodel.*;
import org.hibernate.sql.Select;

import java.util.List;

public class ConverterHelper {
    private static PersonConverter personConverter = new PersonConverterImpl();
    private static DiscountConverter discountConverter = new DiscountConverterImpl();
    private static AddressConverter addressConverter = new AddressConverterImpl();
    private static JobTypeConverter jobTypeConverter = new JobTypeConverterImpl();
    private static CarConverter carConverter = new CarConverterImpl();
    private static WorksheetConverter worksheetConverter = new WorksheetConverterImpl();

    private ConverterHelper() {}

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

    public static WorksheetViewModel fromModel(Worksheet model) {
        return worksheetConverter.fromModel(model);
    }

    public static DiscountViewModel fromModel(Discount model) {
        return discountConverter.fromModel(model);
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

    public static Worksheet toModel(WorksheetViewModel viewModel) {
        return worksheetConverter.toModel(viewModel);
    }

    public static Discount toModel(DiscountViewModel viewModel) {
        return discountConverter.toModel(viewModel);
    }
}

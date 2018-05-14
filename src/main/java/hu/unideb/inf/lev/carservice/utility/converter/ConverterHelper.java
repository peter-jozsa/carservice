package hu.unideb.inf.lev.carservice.utility.converter;

import hu.unideb.inf.lev.carservice.model.*;
import hu.unideb.inf.lev.carservice.utility.converter.impl.*;
import hu.unideb.inf.lev.carservice.viewmodel.*;

/**
 * A helper class which can handle conversion request to/from every model/view model.
 */
public class ConverterHelper {
    private static PersonConverter personConverter = new PersonConverterImpl();
    private static DiscountConverter discountConverter = new DiscountConverterImpl();
    private static AddressConverter addressConverter = new AddressConverterImpl();
    private static JobTypeConverter jobTypeConverter = new JobTypeConverterImpl();
    private static CarConverter carConverter = new CarConverterImpl();
    private static WorksheetConverter worksheetConverter = new WorksheetConverterImpl();

    /**
     * Private constructor in order to disable instantiation.
     */
    private ConverterHelper() {}

    /**
     * Convert a {@link Person} entity model to a view model.
     * @param model The entity model to be converted.
     * @return A view model which represents the given entity model.
     */
    public static PersonViewModel fromModel(Person model) {
        return personConverter.fromModel(model);
    }

    /**
     * Convert a {@link Address} entity model to a view model.
     * @param model The entity model to be converted.
     * @return A view model which represents the given entity model.
     */
    public static AddressViewModel fromModel(Address model) {
        return addressConverter.fromModel(model);
    }

    /**
     * Convert a {@link JobType} entity model to a view model.
     * @param model The entity model to be converted.
     * @return A view model which represents the given entity model.
     */
    public static JobTypeViewModel fromModel(JobType model) {
        return jobTypeConverter.fromModel(model);
    }

    /**
     * Convert a {@link Car} entity model to a view model.
     * @param model The entity model to be converted.
     * @return A view model which represents the given entity model.
     */
    public static CarViewModel fromModel(Car model) {
        return carConverter.fromModel(model);
    }

    /**
     * Convert a {@link Worksheet} entity model to a view model.
     * @param model The entity model to be converted.
     * @return A view model which represents the given entity model.
     */
    public static WorksheetViewModel fromModel(Worksheet model) {
        return worksheetConverter.fromModel(model);
    }

    /**
     * Convert a {@link Discount} entity model to a view model.
     * @param model The entity model to be converted.
     * @return A view model which represents the given entity model.
     */
    public static DiscountViewModel fromModel(Discount model) {
        return discountConverter.fromModel(model);
    }

    /**
     * Convert a {@link PersonViewModel} view model to an entity model.
     * @param viewModel The view model to be converted.
     * @return An entity model which represents the given view model.
     */
    public static Person toModel(PersonViewModel viewModel) {
        return personConverter.toModel(viewModel);
    }

    /**
     * Convert a {@link AddressViewModel} view model to an entity model.
     * @param viewModel The view model to be converted.
     * @return An entity model which represents the given view model.
     */
    public static Address toModel(AddressViewModel viewModel) {
        return addressConverter.toModel(viewModel);
    }

    /**
     * Convert a {@link JobTypeViewModel} view model to an entity model.
     * @param viewModel The view model to be converted.
     * @return An entity model which represents the given view model.
     */
    public static JobType toModel(JobTypeViewModel viewModel) {
        return jobTypeConverter.toModel(viewModel);
    }

    /**
     * Convert a {@link CarViewModel} view model to an entity model.
     * @param viewModel The view model to be converted.
     * @return An entity model which represents the given view model.
     */
    public static Car toModel(CarViewModel viewModel) {
        return carConverter.toModel(viewModel);
    }

    /**
     * Convert a {@link WorksheetViewModel} view model to an entity model.
     * @param viewModel The view model to be converted.
     * @return An entity model which represents the given view model.
     */
    public static Worksheet toModel(WorksheetViewModel viewModel) {
        return worksheetConverter.toModel(viewModel);
    }

    /**
     * Convert a {@link DiscountViewModel} view model to an entity model.
     * @param viewModel The view model to be converted.
     * @return An entity model which represents the given view model.
     */
    public static Discount toModel(DiscountViewModel viewModel) {
        return discountConverter.toModel(viewModel);
    }
}

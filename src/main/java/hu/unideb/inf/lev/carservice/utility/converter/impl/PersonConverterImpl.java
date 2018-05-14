package hu.unideb.inf.lev.carservice.utility.converter.impl;

import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.utility.converter.PersonConverter;
import hu.unideb.inf.lev.carservice.viewmodel.PersonViewModel;
import javafx.beans.property.SimpleStringProperty;

public class PersonConverterImpl implements PersonConverter {
    @Override
    public PersonViewModel fromModel(Person model) {
        if (model == null) {
            return null;
        }

        return new PersonViewModel(
                model.getId(),
                new SimpleStringProperty(model.getFirstName()),
                new SimpleStringProperty(model.getLastName()),
                new SimpleStringProperty(model.getPhone()),
                ConverterHelper.fromModel(model.getAddress()),
                ConverterHelper.fromModel(model.getDiscount())
        );
    }

    @Override
    public Person toModel(PersonViewModel viewModel) {
        if (viewModel == null) {
            return null;
        }

        return new Person(
                viewModel.getId(),
                viewModel.getFirstName(),
                viewModel.getLastName(),
                viewModel.getPhone(),
                ConverterHelper.toModel(viewModel.getAddress()),
                ConverterHelper.toModel(viewModel.getDiscount())
        );
    }
}

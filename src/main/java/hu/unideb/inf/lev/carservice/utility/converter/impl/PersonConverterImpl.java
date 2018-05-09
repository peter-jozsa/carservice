package hu.unideb.inf.lev.carservice.utility.converter.impl;

import hu.unideb.inf.lev.carservice.model.Person;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.utility.converter.PersonConverter;
import hu.unideb.inf.lev.carservice.viewmodel.PersonViewModel;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class PersonConverterImpl implements PersonConverter {
    @Override
    public PersonViewModel fromModel(Person model) {
        return new PersonViewModel(
                new SimpleLongProperty(model.getId()),
                new SimpleStringProperty(model.getFirstName()),
                new SimpleStringProperty(model.getLastName()),
                new SimpleStringProperty(model.getPhone()),
                ConverterHelper.fromModel(model.getAddress())
        );
    }

    @Override
    public Person toModel(PersonViewModel viewModel) {
        return null;
    }
}

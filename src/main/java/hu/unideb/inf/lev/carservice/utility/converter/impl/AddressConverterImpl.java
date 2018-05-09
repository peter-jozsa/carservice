package hu.unideb.inf.lev.carservice.utility.converter.impl;

import hu.unideb.inf.lev.carservice.model.Address;
import hu.unideb.inf.lev.carservice.utility.converter.AddressConverter;
import hu.unideb.inf.lev.carservice.viewmodel.AddressViewModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AddressConverterImpl implements AddressConverter {
    @Override
    public AddressViewModel fromModel(Address model) {
        return new AddressViewModel(
                new SimpleStringProperty(model.getCountry()),
                new SimpleIntegerProperty(model.getZip()),
                new SimpleStringProperty(model.getCity()),
                new SimpleStringProperty(model.getAddressLine())
        );
    }

    @Override
    public Address toModel(AddressViewModel viewModel) {
        if (viewModel == null) {
            return null;
        }

        return new Address(
                viewModel.getCountry(),
                viewModel.getZip(),
                viewModel.getCity(),
                viewModel.getAddressLine()
        );
    }
}

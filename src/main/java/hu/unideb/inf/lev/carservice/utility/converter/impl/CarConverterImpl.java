package hu.unideb.inf.lev.carservice.utility.converter.impl;

import hu.unideb.inf.lev.carservice.model.Car;
import hu.unideb.inf.lev.carservice.utility.converter.CarConverter;
import hu.unideb.inf.lev.carservice.utility.converter.ConverterHelper;
import hu.unideb.inf.lev.carservice.viewmodel.CarViewModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class CarConverterImpl implements CarConverter {
    @Override
    public CarViewModel fromModel(Car model) {
        return new CarViewModel(
                model.getId(),
                new SimpleStringProperty(model.getRegistrationNumber()),
                new SimpleStringProperty(model.getBrand()),
                new SimpleStringProperty(model.getType()),
                new SimpleStringProperty(model.getVIN()),
                new SimpleObjectProperty<>(ConverterHelper.fromModel(model.getOwner()))
        );
    }

    @Override
    public Car toModel(CarViewModel viewModel) {
        return new Car(
                viewModel.getId(),
                viewModel.getRegistrationNumber(),
                viewModel.getBrand(),
                viewModel.getType(),
                viewModel.getVIN(),
                ConverterHelper.toModel(viewModel.getOwner())
        );
    }
}

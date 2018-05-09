package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;

public class AddressViewModel {
    private StringProperty country = new SimpleStringProperty();
    private IntegerProperty zip = new SimpleIntegerProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty addressLine = new SimpleStringProperty();

    public AddressViewModel() {
    }

    public AddressViewModel(StringProperty country, IntegerProperty zip, StringProperty city, StringProperty addressLine) {
        this.country = country;
        this.zip = zip;
        this.city = city;
        this.addressLine = addressLine;
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public int getZip() {
        return zip.get();
    }

    public IntegerProperty zipProperty() {
        return zip;
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public String getAddressLine() {
        return addressLine.get();
    }

    public StringProperty addressLineProperty() {
        return addressLine;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public void setZip(int zip) {
        this.zip.set(zip);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setAddressLine(String addressLine) {
        this.addressLine.set(addressLine);
    }

    public ObservableStringValue fullAddress() {
        return Bindings.concat(zipProperty(), " ", countryProperty(), ", ", cityProperty(), ", ", addressLineProperty());
    }
}

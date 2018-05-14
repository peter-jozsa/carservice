package hu.unideb.inf.lev.carservice.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;

/**
 * View model which represents a {@link hu.unideb.inf.lev.carservice.model.Address} entity.
 */
public class AddressViewModel {
    private StringProperty country = new SimpleStringProperty();
    private IntegerProperty zip = new SimpleIntegerProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty addressLine = new SimpleStringProperty();

    /**
     * Parameter-less constructor.
     */
    public AddressViewModel() {
    }

    /**
     * Initializes a new instance with the provided values.
     * @param country Name of the country
     * @param zip ZIP code of the address
     * @param city City of the address
     * @param addressLine Address line
     */
    public AddressViewModel(StringProperty country, IntegerProperty zip, StringProperty city, StringProperty addressLine) {
        this.country = country;
        this.zip = zip;
        this.city = city;
        this.addressLine = addressLine;
    }

    /**
     * Gets the country of the address.
     * @return A country name
     */
    public String getCountry() {
        return country.get();
    }

    /**
     * Gets the observable country property.
     * @return An observable property.
     */
    public StringProperty countryProperty() {
        return country;
    }

    /**
     * Gets the zip code of the address.
     * @return The zip code of the address.
     */
    public int getZip() {
        return zip.get();
    }

    /**
     * Gets the observable zip code property.
     * @return An observable property.
     */
    public IntegerProperty zipProperty() {
        return zip;
    }

    /**
     * Gets city name of the address.
     * @return A city name
     */
    public String getCity() {
        return city.get();
    }

    /**
     * Gets the observable city name property.
     * @return An observable property.
     */
    public StringProperty cityProperty() {
        return city;
    }

    /**
     * Gets the address line of the address.
     * @return The address line
     */
    public String getAddressLine() {
        return addressLine.get();
    }

    /**
     * Gets the observable address line property.
     * @return An observable property.
     */
    public StringProperty addressLineProperty() {
        return addressLine;
    }

    /**
     * Sets the country name of the address.
     * @param country A country name
     */
    public void setCountry(String country) {
        this.country.set(country);
    }

    /**
     * Sets the ZIP code of the address.
     * @param zip A ZIP code
     */
    public void setZip(int zip) {
        this.zip.set(zip);
    }

    /**
     * Sets the city of the address.
     * @param city A city name
     */
    public void setCity(String city) {
        this.city.set(city);
    }

    /**
     * Sets the address line of the address.
     * @param addressLine The address line
     */
    public void setAddressLine(String addressLine) {
        this.addressLine.set(addressLine);
    }

    /**
     * Gets the concatenated full address as an observable.
     * @return An observable string value.
     */
    public ObservableStringValue fullAddress() {
        return Bindings.concat(zipProperty(), " ", countryProperty(), ", ", cityProperty(), ", ", addressLineProperty());
    }
}

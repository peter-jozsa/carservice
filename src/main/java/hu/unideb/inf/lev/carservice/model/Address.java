package hu.unideb.inf.lev.carservice.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

/**
 * An entity model which describes an address.
 */
@Embeddable
@Access(AccessType.FIELD)
public class Address {
    private String country;
    private int zip;
    private String city;
    private String addressLine;

    /**
     * Parameter-less constructor.
     */
    public Address() {
    }

    /**
     * Initializes a new instance.
     * @param country The name of the country.
     * @param zip The ZIP code of the address.
     * @param city The city of the address.
     * @param addressLine The address line of the address.
     */
    public Address(String country, int zip, String city, String addressLine) {
        this.country = country;
        this.zip = zip;
        this.city = city;
        this.addressLine = addressLine;
    }

    /**
     * Gets the name of the country of the address.
     * @return The country where this address is located.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the ZIP code of the address.
     * @return The ZIP code of the address.
     */
    public int getZip() {
        return zip;
    }

    /**
     * Gets the city of the address.
     * @return The city where this address is located.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the address line of the address.
     * @return The addres line of the address.
     */
    public String getAddressLine() {
        return addressLine;
    }

    /**
     * Sets the country of the address.
     * @param country The name of the country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Sets the ZIP code of the address.
     * @param zip The ZIP code.
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * Sets the city of the address.
     * @param city The name of the city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the address line of the address.
     * @param addressLine The address line
     */
    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    /**
     * Converts the instance to a string representation.
     * @return A string representation of an <code>Address</code> instance.
     */
    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", zip=" + zip +
                ", city='" + city + '\'' +
                ", addressLine='" + addressLine + '\'' +
                '}';
    }
}

package hu.unideb.inf.lev.carservice.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Address {
    private String country;
    private int zip;
    private String city;
    private String addressLine;

    public Address() {
    }

    public Address(String country, int zip, String city, String addressLine) {
        this.country = country;
        this.zip = zip;
        this.city = city;
        this.addressLine = addressLine;
    }

    public String getCountry() {
        return country;
    }

    public int getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

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

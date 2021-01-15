package io.namoosori.travelclub.step1.entity.club;

public class Address {

    private String zipCode;
    private String zipAddress;
    private String streetAddress;
    private String country;
    private AddressType addressType;

    /*create table Address(
        zipCode varchar(?),
        zipAddress varchar(?),
        streetAddrerss varchar(),
        country varchar(?),
        addressType varchar()
    );*/

    public Address(){
        //
    }

    public Address(String zipCode, String zipAddress, String streetAddress){
        this.zipCode = zipCode;
        this.zipAddress = zipAddress;
        this.streetAddress = streetAddress;
        this.country = "South Korea";
        this.addressType = AddressType.Office;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Address{");
        builder.append("zipCode='").append(zipCode).append('\'');
        builder.append(", zipAddress='").append(zipAddress).append('\'');
        builder.append(", streetAddress='").append(streetAddress).append('\'');
        builder.append(", country='").append(country).append('\'');
        builder.append(", addressType=").append(addressType);
        builder.append('}');
        return builder.toString();
    }

    public static Address getHomeAddressSample(){

        Address address = new Address("134-321", "Seoul, Geumcheon-gu, Gasan-dong", "231");
        address.setAddressType(AddressType.Home);

        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipAddress() {
        return zipAddress;
    }

    public void setZipAddress(String zipAddress) {
        this.zipAddress = zipAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }
}

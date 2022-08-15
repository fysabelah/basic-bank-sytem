package model.clients;

public class Address {

    private String district;
    private String complement;
    private String number;
    private String city;
    private String state;

    public Address(String district, String complement, String number, String city, String state) {
        this.district = district;
        this.complement = complement;
        this.number = number;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return "District: " + district + "\n" +
                "Complement: " + complement + "\n" +
                "Number: " + number + "\n" +
                "City: " + city + "\n" +
                "State: " + state + "\n";
    }
}

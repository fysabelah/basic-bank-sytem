package model.clients;

import model.account.Account;

public abstract class Client {

    private String name;
    private String cellphone;
    private Address address;

    public Client(String name, String cellphone, Address address) {
        this.name = name;
        this.cellphone = cellphone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getCellphone() {
        return cellphone;
    }

    public Address getAddress() {
        return address;
    }

    public abstract String getIdentification();

    public abstract Integer getNextNumberOfAccount();

    public abstract Account getPrimaryAccount();
}

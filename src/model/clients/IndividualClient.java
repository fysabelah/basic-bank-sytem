package model.clients;

import model.account.Account;
import model.account.PersonalAccount;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IndividualClient extends Client {

    private String cpf;
    private List<PersonalAccount> accounts;

    public IndividualClient(String name, String cellphone, Address address, String cpf) {
        super(name, cellphone, address);
        this.cpf = cpf;
    }

    public void addAccounts(List<PersonalAccount> accounts) {
        if (this.accounts == null || this.accounts.isEmpty()) {
            setAccounts(accounts);
        } else {
            this.accounts.addAll(accounts);
        }
    }

    public void addAccounts(PersonalAccount account) {
        if (this.accounts == null) {
            setAccounts(new ArrayList<>());
        }

        this.accounts.add(account);
    }

    public void setAccounts(List<PersonalAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String getIdentification() {
        return cpf;
    }

    @Override
    public Integer getNextNumberOfAccount() {
        if (this.accounts == null || this.accounts.isEmpty()) {
            return 1;
        }

        return this.accounts.stream().max(Comparator.comparingInt(Account::getNumber)).get().getNumber() + 1;
    }

    public List<PersonalAccount> getAccounts() {
        return accounts;
    }

    @Override
    public Account getPrimaryAccount() {
        if (this.accounts == null || this.accounts.isEmpty()) {
            return null;
        }

        return this.accounts.get(0);
    }

    @Override
    public String toString() {
        return "------------------- Client ------------------------:\n" +
                "Name: " + getName() + "\n" +
                "CPF: " + getIdentification() + "\n" +
                "Cellphone: " + getCellphone() + "\n" +
                getAddress().toString();
    }
}

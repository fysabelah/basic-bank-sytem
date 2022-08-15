package model.clients;

import model.account.Account;
import model.account.BusinessAccount;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LegalClient extends Client {

    private String cnpj;
    private String commercialName;
    private List<BusinessAccount> accounts;

    public LegalClient(String name, String cellphone, Address address, String cnpj, String commercialName) {
        super(name, cellphone, address);
        this.cnpj = cnpj;
        this.commercialName = commercialName;
    }

    public void addAccounts(List<BusinessAccount> accounts) {
        this.accounts = accounts;
    }

    public void addAccounts(BusinessAccount account) {
        if (this.accounts == null) {
            this.accounts = new ArrayList<>();
        }

        this.accounts.add(account);
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public List<BusinessAccount> getAccounts() {
        return accounts;
    }

    @Override
    public String getIdentification() {
        return cnpj;
    }

    @Override
    public Integer getNextNumberOfAccount() {
        if (this.accounts == null || this.accounts.isEmpty()) {
            return 1;
        }

        return this.accounts.stream().max(Comparator.comparingInt(Account::getNumber)).get().getNumber() + 1;
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
                "CNPJ: " + cnpj + "\n" +
                "Commercial Name: " + commercialName + "\n" +
                getAddress().toString();
    }
}

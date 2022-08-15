package model.account;

import utils.AccountsType;

import java.math.BigDecimal;

public class PersonalAccount extends Account {

    private AccountsType type;

    public PersonalAccount(Integer number, BigDecimal balance, AccountsType type) {
        super(number, balance);
        this.type = type;
    }

    public AccountsType getType() {
        return type;
    }

    public void setType(AccountsType type) {
        this.type = type;
    }
}

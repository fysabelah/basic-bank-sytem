package model.account;

import utils.AccountsType;

import java.math.BigDecimal;

public class BusinessAccount extends Account {

    private BigDecimal overdraft;
    private AccountsType type;

    public BusinessAccount(Integer number, BigDecimal balance) {
        super(number, balance);
        this.overdraft = BigDecimal.ZERO;
        this.type = AccountsType.CHECKING;
    }

    public BigDecimal getOverdraft() {
        return overdraft;
    }

    public void updateOverdraft(BigDecimal overdraft) {
        if (this.overdraft.compareTo(BigDecimal.ZERO) == 0) {
            this.overdraft = overdraft;
        } else {
            this.overdraft = this.overdraft.add(overdraft);
        }
    }
}

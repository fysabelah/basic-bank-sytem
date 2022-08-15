package controller.client;

import model.account.Account;
import utils.AccountsType;

import java.math.BigDecimal;
import java.util.List;

public interface ClientTransactionsInterface {

    String makeLoan(Account account, BigDecimal value);

    void showAccount(List<? extends Account> account);

    BigDecimal getBalance(Account account);

    void listStatement(Account account);

    void changeAccountType(Account account, AccountsType type);

    void makeDeposit(Account account, BigDecimal value);

    void makeWithdraw(Account account, BigDecimal value);

    void showOverdraft(Account account);
}

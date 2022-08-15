package controller.client;

import model.account.Account;
import model.account.BusinessAccount;
import model.account.PersonalAccount;
import utils.AccountsType;
import utils.OperationsTypes;

import java.math.BigDecimal;
import java.util.List;

public class ClientTransactions implements ClientTransactionsInterface {

    @Override
    public String makeLoan(Account account, BigDecimal value) {
        if (account.isLoanAvailable()) {
            account.setLoan(value);
            account.addStatementToAccount(OperationsTypes.LOAN, value);
            return "Loan contract with success";
        }

        return "Loan is not available";
    }

    @Override
    public void showAccount(List<? extends Account> accounts) {
        accounts.forEach(account -> System.out.println(account.toString()));
    }

    @Override
    public BigDecimal getBalance(Account account) {
        return account.getBalance();
    }

    @Override
    public void listStatement(Account account) {
        System.out.println(account.toString());
        account.getStatements().forEach(System.out::println);
    }

    @Override
    public void changeAccountType(Account account, AccountsType type) {
        if (account instanceof BusinessAccount) {
            System.out.println("Type cannot be changed");
        } else {
            ((PersonalAccount) account).setType(type);
            account.addStatementToAccount(OperationsTypes.TYPE_CHANGE);

            System.out.println("Change made successfully");
        }
    }

    @Override
    public void makeDeposit(Account account, BigDecimal value) {
        account.setBalance(account.getBalance().add(value));
        account.addStatementToAccount(OperationsTypes.CREDIT, value);

        operationMakeWithSucess("Deposit");
    }

    private void operationMakeWithSucess(String operation) {
        System.out.println(operation + " make with sucess");
    }

    private void withdrawPersonalAccount(Account account, BigDecimal value) {
        if (account.getBalance().compareTo(value) < 0) {
            System.out.println("Withdraw is not available");
        } else {
            account.setBalance(account.getBalance().subtract(value));
            account.addStatementToAccount(OperationsTypes.DEBIT, value);

            operationMakeWithSucess("Withdraw");
        }
    }

    private void withdrawBusinessAccount(Account account, BigDecimal value) {
        if (account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            account.setBalance(
                    account.getBalance().subtract(value)
            );
        } else if (account.getBalance().compareTo(BigDecimal.ZERO) == 0) {
            ((BusinessAccount) account).updateOverdraft(value);
        } else {
            BigDecimal difference = account.getBalance().subtract(value).abs();
            ((BusinessAccount) account).updateOverdraft(difference);
            account.setBalance(BigDecimal.ZERO);
        }

        account.addStatementToAccount(OperationsTypes.DEBIT, value);
        operationMakeWithSucess("Withdraw");
    }


    @Override
    public void makeWithdraw(Account account, BigDecimal value) {
        if (account instanceof PersonalAccount) {
            withdrawPersonalAccount(account, value);
        } else {
            withdrawBusinessAccount(account, value);
        }
    }

    @Override
    public void showOverdraft(Account account) {
        if (account instanceof PersonalAccount) {
            System.out.println("Operation not available");
        } else {
            System.out.println("Overdraft = " + ((BusinessAccount) account).getOverdraft());
        }
    }
}

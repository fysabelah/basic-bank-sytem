package model.account;

import utils.Dates;
import utils.OperationsTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private static final String branch = "1212";
    private Integer number;
    private BigDecimal loan;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private List<Statement> statements;

    public Account(Integer number, BigDecimal balance) {
        this.number = number;
        this.loan = BigDecimal.ZERO;
        this.balance = balance;
        this.createdAt = Dates.getDateNow();
        this.statements = new ArrayList<>();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Integer getNumber() {
        return number;
    }

    public String getBranch() {
        return branch;
    }

    public BigDecimal getLoan() {
        return loan;
    }

    public void setLoan(BigDecimal loan) {
        this.loan = loan;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Statement> getStatements() {
        if (statements == null) {
            statements = new ArrayList<>();
        }

        return statements;
    }

    public void addStatementToAccount(OperationsTypes operationTypes, BigDecimal value) {
        this.getStatements().add(new Statement(value, operationTypes));
    }

    public void addStatementToAccount(OperationsTypes operationTypes) {
        this.getStatements().add(new Statement(operationTypes));
    }

    public boolean isLoanAvailable() {
        return this.getLoan().compareTo(new BigDecimal(5000)) >= 0;
    }

    @Override
    public String toString() {
        StringBuilder account = new StringBuilder("Account\n")
                .append("Number: ")
                .append(getNumber())
                .append("\nLoan: ")
                .append(getLoan())
                .append("\nBalance: ")
                .append(getBalance())
                .append("\nCreate at: ")
                .append(getCreatedAt());

        return account.toString();
    }
}

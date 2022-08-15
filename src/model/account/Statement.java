package model.account;

import utils.Dates;
import utils.OperationsTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Statement {

    private BigDecimal value;
    private OperationsTypes operationType;
    private LocalDateTime operationDate;

    public Statement(BigDecimal value, OperationsTypes operationType) {
        this.value = value;
        this.operationType = operationType;
        this.operationDate = Dates.getDateNow();
    }

    public Statement(OperationsTypes operationType) {
        this.operationType = operationType;
        this.operationDate = Dates.getDateNow();
    }

    @Override
    public String toString() {
        return "Statement: " +
                "value = " + value +
                "| operationType=" + operationType +
                "| operationDate=" + operationDate +
                '\n';
    }
}

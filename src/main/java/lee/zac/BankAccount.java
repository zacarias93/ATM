package lee.zac;

import java.util.ArrayList;

/**
 * Created by zaclee on 9/16/16.
 */
public class BankAccount {

    private double accountBalance;
    private ArrayList<String> accountHistory;
    private Type accountType;
    private Status accountStatus;
    private int bankAccountNumber;
    private String message;

    enum Type { CHECKING, SAVINGS, INVESTMENT }

    enum Status { OPEN, CLOSED; }

    BankAccount(int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
        accountHistory = new ArrayList<String>();
        accountBalance=0;
        accountStatus = Status.OPEN;
    }

    ArrayList<String> getAccountHistory() {
        return accountHistory;
    }

    void addToAccountHistory(String str) {
        accountHistory.add(str);
    }

    void setAccountStatus(Status accountStatus) {
        this.accountStatus = accountStatus;
    }

    Status getAccountStatus() {
        return accountStatus;
    }

    void setAccountType(Type type) {
         accountType = type;
    }

    Type getAccountType() {
        return accountType;
    }

    void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    double getAccountBalance() {
        return accountBalance;
    }

    int getBankAccountNumber() {
        return bankAccountNumber;
    }
}

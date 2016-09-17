package lee.zac;

import java.util.ArrayList;

/**
 * Created by zaclee on 9/16/16.
 */
public class ATM {

    private static ArrayList<User> arrayListOfUsers;
    private String userName;
    private String userPassword;
    private double newAccountBalance;
    private BankAccount transferFrom;
    private BankAccount transferTo;
    private BankAccount userSelectedBankAccount;
    private BankAccount userSelectedAccountToTransferFrom;
    private BankAccount userSelectedAccountToTransferTo;
    private double newTransFromBalance;
    private double newTransTobalance;
    private int bankAccountNumber=0;
    private String message;


    public ATM() {
        arrayListOfUsers = new ArrayList<User>();
    }


    protected ArrayList<User> getArrayListOfUsers() {
        return arrayListOfUsers;
    }

    protected User getUserFromArrayListByIndex (int i) {
        return arrayListOfUsers.get(i);
    }

    //for me to test - delete when turning in - NO ONE should be able to print out list of users + pass
    public void showUsers() {
        System.out.println(arrayListOfUsers.toString());
    }

    protected String createUser(String name, String pass) {
        User temp = new User();
        temp.setUserName(name);
        temp.setUserPassword(pass);
        arrayListOfUsers.add(temp);
        return message = "You just created a User! Your userID is: " + name;
    }

    protected User getUserFromArrayByNameAndPass(String userName, String userPassword) {
        for(int i =0; i <arrayListOfUsers.size(); i++) {
            if((userName + userPassword).equals(arrayListOfUsers.get(i).toString())) {
                return arrayListOfUsers.get(i);
            }
        }
        return null;
    }

    protected String createBankAccountAndAddToUserProfile(String userName, String userPassword, BankAccount.Type type) { //Do you know what im doing tho?? :)
        BankAccount temp = new BankAccount(++this.bankAccountNumber);
        temp.setAccountType(type);
        getUserFromArrayByNameAndPass(userName,userPassword).addBankAccountToUser(temp);
        message = "Your " + type + " account has been created and added to your user profile.";
        temp.addToAccountHistory(message);
        return message;
    }

    protected String closeBankAccount(String userName, String userPassword, BankAccount.Type type) {

        if(getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(type).getAccountBalance() == 0) {
            userSelectedBankAccount = getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(type);
            userSelectedBankAccount.setAccountStatus(BankAccount.Status.CLOSED);
            message = "Your account is closed.";
            userSelectedBankAccount.addToAccountHistory(message);
            return message;
        }
        else {
            return message = "Your account balance must be 0 before the account can be closed.";
        }
    }

    public boolean authenticate(String user, String pass) {
        for(int i = 0; i < arrayListOfUsers.size(); i++) {
            if((user + pass).equals(arrayListOfUsers.get(i).toString())) {
                return true;
            }
        }
        return false;
    }

    protected String showAccountBalance(String userName, String userPassword, BankAccount.Type type) {
        userSelectedBankAccount = getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(type);
        return message = "Your account balance is: " + userSelectedBankAccount.getAccountBalance();

    }

    protected String deposit(String userName, String userPassword, BankAccount.Type type, double amount) {
        userSelectedBankAccount = getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(type);
        if(userSelectedBankAccount.getAccountStatus() == BankAccount.Status.OPEN) {
            newAccountBalance = userSelectedBankAccount.getAccountBalance() + amount;
            userSelectedBankAccount.setAccountBalance(newAccountBalance);
            message = "You deposited " + amount + " to your " + type + " account.";
            userSelectedBankAccount.addToAccountHistory(message);
            return message;
        }
        return message = "Transaction denied. Account is not open.";
    }

    protected String withdraw(String userName, String userPassword, BankAccount.Type type, double amount) {
        userSelectedBankAccount = getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(type);
        if(userSelectedBankAccount.getAccountStatus() == BankAccount.Status.OPEN && userSelectedBankAccount.getAccountBalance() - amount < 0) {
            return message = "You do not have sufficient funds for this transaction.";
        }
        else if(userSelectedBankAccount.getAccountStatus() == BankAccount.Status.OPEN && userSelectedBankAccount.getAccountBalance() - amount >= 0) {
            newAccountBalance = userSelectedBankAccount.getAccountBalance() - amount;
            userSelectedBankAccount.setAccountBalance(newAccountBalance);
            message = "You withdrew " + amount + " from your " + type + " account.";
            userSelectedBankAccount.addToAccountHistory(message);
            return message;
        }
        return message = "Transaction denied. Account is not open.";
    }

    protected String transfer(String userName, String userPassword, BankAccount.Type transferFrom, BankAccount.Type transferTo, double amount) { // add same acc check
        userSelectedAccountToTransferFrom = getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(transferFrom);
        userSelectedAccountToTransferTo = getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(transferTo);
        if(userSelectedAccountToTransferFrom.getAccountStatus() == BankAccount.Status.OPEN && userSelectedAccountToTransferTo.getAccountStatus() == BankAccount.Status.OPEN) { //Both ACC Open
            if(userSelectedAccountToTransferFrom.getAccountBalance() - amount >= 0) { // have enough to transfer
                newTransFromBalance = userSelectedAccountToTransferFrom.getAccountBalance() - amount;
                userSelectedAccountToTransferFrom.setAccountBalance(newTransFromBalance);
                newTransTobalance = userSelectedAccountToTransferTo.getAccountBalance() + amount;
                userSelectedAccountToTransferTo.setAccountBalance(newTransTobalance);
                message = "Transfer was successful. Your new account balance is: \n" + userSelectedAccountToTransferFrom.getAccountType() + " : " +
                        userSelectedAccountToTransferFrom.getAccountBalance() + "\n" + userSelectedAccountToTransferTo.getAccountType() + " : " + userSelectedAccountToTransferTo.getAccountBalance();
                userSelectedAccountToTransferTo.addToAccountHistory(message);
                userSelectedAccountToTransferFrom.addToAccountHistory(message);
                return message;
            }
            else {
                return message = "You did not have sufficient funds to transfer.";
            }
        }
        return message = "Both accounts must be your accounts and open to make a transfer.";
    }

    protected String showTransactionHistory(String userName, String userPassword, BankAccount.Type type) {
        userSelectedBankAccount = getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(type);
        return message = userSelectedBankAccount.getAccountHistory().toString();
    }






}

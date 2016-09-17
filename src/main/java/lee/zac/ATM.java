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
    private double newTransFromBalance;
    private double newTransTobalance;
    private int bankAccountNumber=0;


    public ATM() {
        arrayListOfUsers = new ArrayList<User>();
    }


    public ArrayList<User> getArrayListOfUsers() {
        return arrayListOfUsers;
    }

    public User getUserFromArrayListByIndex (int i) {
        return arrayListOfUsers.get(i);
    }

    //for me to test - delete when turning in - NO ONE should be able to print out list of users + pass
    public void showUsers() {
        System.out.println(arrayListOfUsers.toString());
    }

    public String createUser(String name, String pass) {
        User temp = new User();
        temp.setUserName(name);
        temp.setUserPassword(pass);
        arrayListOfUsers.add(temp);
        return "Your userID is: " + name;
    }

    public User getUserFromArrayByNameAndPass(String userName, String userPassword) {
        for(int i =0; i <arrayListOfUsers.size(); i++) {
            if((userName + userPassword).equals(arrayListOfUsers.get(i).toString())) {
                return arrayListOfUsers.get(i);
            }
        }
        return null;
    }

    public String createBankAccountAndAddToUserProfile(String userName, String userPassword, BankAccount.Type type) { //Do you know what im doing tho?? :)
        BankAccount temp = new BankAccount(++this.bankAccountNumber);
        temp.setAccountType(type);
        getUserFromArrayByNameAndPass(userName,userPassword).addBankAccountToUser(temp);
        return "Your account has been created and added to your profile.";
    }

    public String closeBankAccount(String userName, String userPassword, BankAccount.Type type) {

        if(getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(type).getAccountBalance() == 0) {
            getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(type).setAccountStatus(BankAccount.Status.CLOSED);
            return "Your account is closed.";
        }
        else {
            return "Your account balance must be 0 before the account can be closed.";
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

    public String deposit(String userName, String userPassword, BankAccount.Type type, double amount) {
        userSelectedBankAccount = getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(type);
        if(userSelectedBankAccount.getAccountStatus() == BankAccount.Status.OPEN) {
            newAccountBalance = userSelectedBankAccount.getAccountBalance() + amount;
            userSelectedBankAccount.setAccountBalance(newAccountBalance);
            String message = "You deposited " + amount + " to your " + type + " account.";
            userSelectedBankAccount.addToAccountHistory(message);
            return message;
        }
        return "Transaction denied. Account is not open.";
    }






}

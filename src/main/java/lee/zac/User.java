package lee.zac;

import java.util.ArrayList;

/**
 * Created by zaclee on 9/16/16.
 */
public class User {

    private String userName;
    private String userPassword;
    public ArrayList<BankAccount> arrayListOfBankAccountsByUser;

    public User () {
        arrayListOfBankAccountsByUser = new ArrayList<BankAccount>();
    }

    @Override
    public String toString() {
        return userName + userPassword;
    }

    public BankAccount getBankAccountByIndex(int index) {
        return arrayListOfBankAccountsByUser.get(index);
    }

    public BankAccount getBankAccountByType(BankAccount.Type type) {
        for(int i=0; i<arrayListOfBankAccountsByUser.size();i++) {
            if(type == arrayListOfBankAccountsByUser.get(i).getAccountType()) {
                return arrayListOfBankAccountsByUser.get(i);
            }
        }
        return null;
    }

    public ArrayList<BankAccount> getArrayListOfBankAccountsByUser() {
        return this.arrayListOfBankAccountsByUser;
    }

    public void addBankAccountToUser(BankAccount acc) {
        arrayListOfBankAccountsByUser.add(acc);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void showUserBankAccounts() {
        System.out.println(arrayListOfBankAccountsByUser);
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    // keep these to change name / pass??

}

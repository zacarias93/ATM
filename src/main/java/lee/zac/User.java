package lee.zac;

import java.util.ArrayList;

public class User {

    private String userName;
    private String userPassword;
    ArrayList<BankAccount> arrayListOfBankAccountsByUser;

    User() {
        arrayListOfBankAccountsByUser = new ArrayList<BankAccount>();
    }

    @Override
    public String toString() {
        return userName + userPassword;
    }

    BankAccount getBankAccountByIndex(int index) {
        return arrayListOfBankAccountsByUser.get(index);
    }

    BankAccount getBankAccountByType(BankAccount.Type type) {
        for(int i=0; i<arrayListOfBankAccountsByUser.size();i++) {
            if(type == arrayListOfBankAccountsByUser.get(i).getAccountType()) {
                return arrayListOfBankAccountsByUser.get(i);
            }
        }
        return null;
    }

    ArrayList<BankAccount> getArrayListOfBankAccountsByUser() {
        return this.arrayListOfBankAccountsByUser;
    }

    void addBankAccountToUser(BankAccount acc) {
        arrayListOfBankAccountsByUser.add(acc);
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

//    public void showUserBankAccounts() {
//        System.out.println(arrayListOfBankAccountsByUser);
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public String getUserPassword() {
//        return userPassword;
//    }

}

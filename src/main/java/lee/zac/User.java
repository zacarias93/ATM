package lee.zac;

import java.util.ArrayList;

/**
 * Created by zaclee on 9/16/16.
 */
public class User {

    private String userName;
    private String userPassword;
    private ArrayList<BankAccount> listOfAccountsByUser;

    @Override
    public String toString() {
        return userName + userPassword;
    }

    public void addToArrayOfUsers() {

    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    // keep these to change name / pass??

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}

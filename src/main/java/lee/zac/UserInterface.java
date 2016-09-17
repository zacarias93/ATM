package lee.zac;

import java.util.Scanner;

public class UserInterface {

    private String userName;
    private String userPassword;
    public type accountType;
    public int action;
    public double amount;
    public Scanner scan = new Scanner(System.in);


    public enum type { CHECKING, SAVINGS, INVESTMENT }

    public void getLoginInfo() {
        System.out.println("Please enter your: ");
        System.out.print("Username: ");
        userName = scan.next();
        System.out.print("Password: ");
        userPassword = scan.next();

    }









    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.userPassword = password;
    }



}

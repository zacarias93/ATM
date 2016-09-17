package lee.zac;

import lee.zac.BankAccount.Type;

import java.util.Scanner;

public class UserInterface {

    public String userName;
    public String userPassword;
    public Type userAccountType;
    public Type userTransferToAccountType;
    public double userAmount;
    public String message;
    public Scanner scan = new Scanner(System.in);
    public int userFirstChoice;
    public int userAccountChoice;
    public int userTransferToAccountChoice;
    public int userTransactionChoice;
    private String newUserName;
    private String newUserPassword;
    private boolean userSession = false;

    ATM atm = new ATM();

    public void useATM() {
        atm.createUserAndAddToUserArray("1","1");
        atm.createBankAccountAndAddToUserProfile("1","1", Type.CHECKING);
        atm.createBankAccountAndAddToUserProfile("1","1", Type.SAVINGS);
        atm.createBankAccountAndAddToUserProfile("1","1", Type.INVESTMENT);
        atm.deposit("1","1", Type.CHECKING,100);
        atm.deposit("1","1", Type.SAVINGS,500);
        atm.deposit("1","1", Type.INVESTMENT,1000);


        atm.powerOn = true;

        while(atm.powerOn) {

            homeScreen();

            if (userFirstChoice == 1) {

                getLoginInfo();

                if (authenticateUser(userName, userPassword) == true) {
                    userSession=true;
                    while(userSession) {
                        menuOptionsScreen();
                        switch (userTransactionChoice) {
                            case 1:
                                selectAccount();
                                selectAmount();
                                System.out.println(atm.withdraw(userName, userPassword, userAccountType, userAmount));
                                break;

                            case 2:
                                selectAccount();
                                selectAmount();
                                System.out.println(atm.deposit(userName, userPassword, userAccountType, userAmount));
                                break;

                            case 3:
                                selectAccount();
                                selectAccountTransferTo();
                                selectAmount();
                                System.out.println(atm.transfer(userName, userPassword, userAccountType, userTransferToAccountType, userAmount));
                                break;

                            case 4:
                                selectAccount();
                                System.out.println(atm.showAccountBalance(userName, userPassword, userAccountType));
                                break;

                            case 5:
                                selectAccount();
                                System.out.println(atm.showTransactionHistory(userName, userPassword, userAccountType));
                                break;

                            case 6:
                                selectAccount();
                                System.out.println(atm.closeBankAccount(userName, userPassword, userAccountType));
                                break;

                            case 7:
                                userSession = false;
                                break;

                        }
                    }
                }
                else {
                    // login was wrong
                    System.out.println("Your account name and password did not match our records. Please try again!");
                }
            }

            else if(userFirstChoice == 2) {
                System.out.println(createNewUser());
                createBankAccount();
            }
            else if(userFirstChoice == 3) {
                createBankAccount();

            }
            else { // Anything but 1,2,3 - turns OFF
                System.out.println("Goodbye.");
                atm.powerOn=false;
            }
        }
        System.out.println("This is the end of ZE WORLD!!");
    }

    public int homeScreen() {
        message = "\nHello! \nWhat would you like to do? \nPlease select a number: \n  1: Login   \n  2: Create User  \n  3: Create Account";
        System.out.println(message);
        userFirstChoice = scan.nextInt();
        return userFirstChoice;
    }

    public int menuOptionsScreen() {
        System.out.println("\nWhat would you like to do? \n 1: Withdraw \n 2: Deposit \n 3: Transfer \n 4: Check Balance \n 5: Print Transaction History \n 6: Close Account \n 7: Log out");
        return userTransactionChoice = scan.nextInt();
    }

    public String createNewUser() {
        System.out.println("Please enter a username.");
        newUserName = scan.next();
        System.out.println("Please enter a password.");
        newUserPassword = scan.next();
        return atm.createUserAndAddToUserArray(newUserName, newUserPassword);
    }

    public void createBankAccount() {
        System.out.println("What kind of account would you like to create? \n 1: Checking \n 2: Savings \n 3: Investment");
        userAccountChoice = scan.nextInt();

        switch (userAccountChoice) {
            case 1:
                atm.createBankAccountAndAddToUserProfile(newUserName, newUserPassword, Type.CHECKING);
                break;
            case 2:
                atm.createBankAccountAndAddToUserProfile(newUserName, newUserPassword, Type.SAVINGS);
                break;
            case 3:
                atm.createBankAccountAndAddToUserProfile(newUserName, newUserPassword, Type.INVESTMENT);
                break;

            default: System.out.println("Please try again.");
        }
    }

    public Type selectAccount() {
        System.out.println("Which account would to use? \n 1: Checking \n 2: Savings \n 3: Investment");
        userAccountChoice = scan.nextInt();

        switch (userAccountChoice) {
            case 1:
                userAccountType = atm.getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(Type.CHECKING).getAccountType();
                break;
            case 2:
                userAccountType = atm.getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(Type.SAVINGS).getAccountType();
                break;
            case 3:
                userAccountType = atm.getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(Type.INVESTMENT).getAccountType();
                break;

                default: selectAccount();
        }
        return userAccountType;
    }

    public Type selectAccountTransferTo() {
        System.out.println("Which account would to transfer to? \n 1: Checking \n 2: Savings \n 3: Investment");
        userTransferToAccountChoice = scan.nextInt();

        switch (userTransferToAccountChoice) {
            case 1:
                userTransferToAccountType = atm.getUserFromArrayByNameAndPass(userName,userPassword).getBankAccountByType(Type.CHECKING).getAccountType();
                break;
            case 2:
                userTransferToAccountType = atm.getUserFromArrayByNameAndPass(userName, userPassword).getBankAccountByType(Type.SAVINGS).getAccountType();
                break;
            case 3:
                userTransferToAccountType = atm.getUserFromArrayByNameAndPass(userName, userPassword).getBankAccountByType(Type.INVESTMENT).getAccountType();

            default: selectAccount();
        }
        return userTransferToAccountType;
    }

    public double selectAmount() {
        System.out.println("Enter an amount: ");
        return userAmount = scan.nextDouble();
    }

    public void getLoginInfo() {
        System.out.println("Please enter your: ");
        System.out.print("Username: ");
        userName = scan.next();
        System.out.print("Password: ");
        userPassword = scan.next();
    }

    public boolean authenticateUser(String user, String pass) {
        for(int i = 0; i < atm.arrayListOfUsers.size(); i++) {
            if((user + pass).equals(atm.arrayListOfUsers.get(i).toString())) {
                return true;
            }
        }
        return false;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.userPassword = password;
    }
    }

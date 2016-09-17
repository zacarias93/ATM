package lee.zac;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ATMTest {

    ATM atm;
    public double delta = 10^-15;

    @Before
    public void setUpTest() {
        atm = new ATM();
    }

    @Test
    public void createUserTest() {
        atm.createUser("zac","soccer");
        int expected = 1;
        int actual = atm.getArrayListOfUsers().size();
        assertEquals("Should be 1",expected,actual);
    }

    @Test
    public void authenticateTest() {
        atm.createUser("zac","soccer");
        boolean expected = true;
        boolean actual = atm.authenticate("zac","soccer");
        assertEquals(expected,actual);
    }

    @Test
    public void getUserFromArrayByNameAndPassTest() {
        atm.createUser("zac","soccer");
        atm.createUser("wes","fighting");
        User expected = atm.getUserFromArrayListByIndex(1);
        User actual = atm.getUserFromArrayByNameAndPass("wes","fighting");
        assertEquals("Check second element w/ searching by name and pass",expected,actual);
    }

    @Test
    public void createBankAccountAndAddToUserProfileTest() {// I know it's a long name get over it!
        atm.createUser("zac","soccer");
        atm.createUser("wes","fighting");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.SAVINGS);
        int expected = 2;
        int actual = atm.getUserFromArrayByNameAndPass("zac","soccer").getArrayListOfBankAccountsByUser().size();
        assertEquals("check size of list of accounts by user to see if added - should be 1",expected,actual);
    }

    @Test
    public void closeBankAccountTest() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.SAVINGS);
        atm.closeBankAccount("zac","soccer", BankAccount.Type.CHECKING);
        BankAccount.Status expected = BankAccount.Status.CLOSED;
        BankAccount.Status actual = atm.getUserFromArrayByNameAndPass("zac","soccer").getBankAccountByType(BankAccount.Type.CHECKING).getAccountStatus();
        assertEquals("Close the checking account..",expected,actual);
    }

    @Test
    public void getBankAccountNumberTest() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.SAVINGS);
        int expected = 1;
        int actual = atm.getUserFromArrayByNameAndPass("zac","soccer").getBankAccountByType(BankAccount.Type.CHECKING).getBankAccountNumber();
        assertEquals("Test account number of first created.",expected,actual);
    }

    @Test
    public void getBankAccountNumberTest2() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.SAVINGS);
        int expected = 2;
        int actual = atm.getUserFromArrayByNameAndPass("zac","soccer").getBankAccountByType(BankAccount.Type.SAVINGS).getBankAccountNumber();
        assertEquals("Test account number of second created.",expected,actual);
    }

    @Test
    public void showAccountBalanceTest() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);
        String expected = "Your account balance is: 50.0";
        String actual = atm.showAccountBalance("zac","soccer", BankAccount.Type.CHECKING);
        assertEquals("checking acc bal - should be 50",expected,actual);
    }

    @Test
    public void depositTest() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);
        double expected = 50;
        double actual = atm.getUserFromArrayByNameAndPass("zac","soccer").getBankAccountByType(BankAccount.Type.CHECKING).getAccountBalance();
        assertEquals(expected,actual,delta);
    }

    @Test
    public void depositTest2() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);
        String expected = "You deposited 50.0 to your CHECKING account.";
        String actual = atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);
        assertEquals("Test to deposit 50",expected,actual);
    }

    @Test
    public void depositTest3() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);
        atm.getUserFromArrayByNameAndPass("zac","soccer").getBankAccountByType(BankAccount.Type.CHECKING).setAccountStatus(BankAccount.Status.CLOSED);
        String expected = "Transaction denied. Account is not open.";
        String actual = atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);
        assertEquals("Testing when account is closed.",expected,actual);
    }

    @Test
    public void withdrawTest1() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);
        String expected = "You withdrew 50.0 from your CHECKING account.";
        String actual = atm.withdraw("zac","soccer", BankAccount.Type.CHECKING,50);
        assertEquals("Withdraw 50 from account with 50 - should approve.",expected,actual);
    }

    @Test
    public void withdrawTest2() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);
        String expected = "You do not have sufficient funds for this transaction.";
        String actual = atm.withdraw("zac","soccer", BankAccount.Type.CHECKING,60);
        assertEquals("Withdraw 60 from account with 50 - should deny.",expected,actual);
    }

    @Test
    public void withdrawTest3() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.getUserFromArrayByNameAndPass("zac","soccer").getBankAccountByType(BankAccount.Type.CHECKING).setAccountStatus(BankAccount.Status.CLOSED);
        String expected = "Transaction denied. Account is not open.";
        String actual = atm.withdraw("zac","soccer", BankAccount.Type.CHECKING,50);
        assertEquals("Withdraw 60 from account with 50 - should deny.",expected,actual);
    }

    @Test
    public void transferTest1() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.SAVINGS);
        atm.deposit("zac","soccer", BankAccount.Type.SAVINGS,50);
        String expected = "Transfer was successful. Your new account balance is: \n" + "CHECKING : 0.0\n" + "SAVINGS : 100.0";
        String actual = atm.transfer("zac","soccer", BankAccount.Type.CHECKING, BankAccount.Type.SAVINGS,50);
        assertEquals("transfer 50 from CH TO SAV",expected,actual);
    }

    @Test
    public void transferTest2() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.SAVINGS);
        atm.deposit("zac","soccer", BankAccount.Type.SAVINGS,50);
        String expected = "You did not have sufficient funds to transfer.";
        String actual = atm.transfer("zac","soccer", BankAccount.Type.CHECKING, BankAccount.Type.SAVINGS,100);
        assertEquals("transfer 100 from CH TO SAV - over draw",expected,actual);
    }

    @Test
    public void transferTest3() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.SAVINGS);
        atm.deposit("zac","soccer", BankAccount.Type.SAVINGS,50);
        atm.getUserFromArrayByNameAndPass("zac","soccer").getBankAccountByType(BankAccount.Type.CHECKING).setAccountStatus(BankAccount.Status.CLOSED);
        String expected = "Both accounts must be your accounts and open to make a transfer.";
        String actual = atm.transfer("zac","soccer", BankAccount.Type.CHECKING, BankAccount.Type.SAVINGS,100);
        assertEquals("acc 1 closed",expected,actual);
    }

    @Test
    public void transferTest4() {
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.SAVINGS);
        atm.deposit("zac","soccer", BankAccount.Type.SAVINGS,50);
        atm.getUserFromArrayByNameAndPass("zac","soccer").getBankAccountByType(BankAccount.Type.SAVINGS).setAccountStatus(BankAccount.Status.CLOSED);
        String expected = "Both accounts must be your accounts and open to make a transfer.";
        String actual = atm.transfer("zac","soccer", BankAccount.Type.CHECKING, BankAccount.Type.SAVINGS,100);
        assertEquals("acc 2 closed",expected,actual);
    }

    @Test
    public void showTransactionHistory() { // I have tested transfer but with line breaks didn't include in here.
        atm.createUser("zac","soccer");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);
        atm.withdraw("zac","soccer", BankAccount.Type.CHECKING,50);
        atm.closeBankAccount("zac","soccer", BankAccount.Type.CHECKING);
        String expected = "[Your CHECKING account has been created and added to your user profile., You deposited 50.0 to your CHECKING account., You withdrew 50.0 from your CHECKING account., Your account is closed.]";
        String actual = atm.showTransactionHistory("zac","soccer", BankAccount.Type.CHECKING);
        assertEquals("create / deposit / withdraw / close message",expected,actual);



    }







}

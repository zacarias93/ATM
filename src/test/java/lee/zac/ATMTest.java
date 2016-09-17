package lee.zac;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ATMTest {

    ATM atm;
    public double delta = 10^-15;
    public String message;


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
    public void addBankAccountToUserTest() {
        atm.createUser("zac","soccer");
        atm.createUser("wes","fighting");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.SAVINGS);
        int expected = 2;
        int actual = atm.getUserFromArrayByNameAndPass("zac","soccer").arrayListOfBankAccountsByUser.size();
        assertEquals("checking + savings = 2",expected,actual);
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
    public void getBankAccountByTypeTest() {
        atm.createUser("zac","soccer");
        atm.createUser("wes","fighting");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.SAVINGS);
        BankAccount expected = atm.getUserFromArrayListByIndex(0).getBankAccountByIndex(0);
        BankAccount actual = atm.getUserFromArrayByNameAndPass("zac","soccer").getBankAccountByType(BankAccount.Type.CHECKING);
        assertEquals("User index 0 = zac / Bank acc index 0= checking",expected,actual);
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

}

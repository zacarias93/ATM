package lee.zac;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest {

        ATM atm;
        public double delta = 10^-15;

        @Before
        public void setUpTest() {
            atm = new ATM();
        }

    @Test
    public void getBankAccountByTypeTest() {
        atm.createUserAndAddToUserArray("zac","soccer");
        atm.createUserAndAddToUserArray("wes","fighting");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.SAVINGS);
        BankAccount expected = atm.getUserFromArrayListByIndex(0).getBankAccountByIndex(0);
        BankAccount actual = atm.getUserFromArrayByNameAndPass("zac","soccer").getBankAccountByType(BankAccount.Type.CHECKING);
        assertEquals("User index 0 = zac / Bank acc index 0= checking",expected,actual);
    }

    @Test
    public void addBankAccountToUserTest() {
        atm.createUserAndAddToUserArray("zac","soccer");
        atm.createUserAndAddToUserArray("wes","fighting");
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.SAVINGS);
        int expected = 2;
        int actual = atm.getUserFromArrayByNameAndPass("zac","soccer").arrayListOfBankAccountsByUser.size();
        assertEquals("checking + savings = 2",expected,actual);
    }






}

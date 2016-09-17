package lee.zac;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ATMTest {

    ATM atm;


    @Before
    public void setUpTest() {
        atm = new ATM();
        atm.createUser("zac","soccer");
    }

    @Test
    public void createUserTest() {
        int expected = 1;
        int actual = atm.getArrayListOfUsers().size();
        assertEquals("Should be 1",expected,actual);
    }
}

package lee.zac;

/**
 * Created by zaclee on 9/16/16.
 */
public class App {

    public static void main(String[] args) {


        UserInterface operate = new UserInterface();
        operate.atm.createUser("zac","soccer");
        operate.atm.createBankAccountAndAddToUserProfile("zac","soccer", BankAccount.Type.CHECKING);
        operate.atm.deposit("zac","soccer", BankAccount.Type.CHECKING,50);





    }
}

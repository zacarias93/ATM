package lee.zac;

/**
 * Created by zaclee on 9/16/16.
 */
public class App {

    public static void main(String[] args) {


        ATM atm = new ATM();
        atm.createUser("zach", "soccer");
        atm.createUser("wes","fighting");
        atm.showUsers();


        System.out.println(atm.authenticate("zach","soccer"));
        System.out.println(atm.authenticate("wes","fighting"));





    }
}

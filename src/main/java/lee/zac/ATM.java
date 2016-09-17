package lee.zac;

import java.util.ArrayList;

/**
 * Created by zaclee on 9/16/16.
 */
public class ATM {

    private static ArrayList<User> arrayListOfUsers;
    private String userName;
    private String userPassword;


    public ATM() {
        arrayListOfUsers = new ArrayList<User>();
    }

    public boolean authenticate(String user, String pass) {
        for(int i = 0; i < arrayListOfUsers.size(); i++) {
            if((user + pass).equals(arrayListOfUsers.get(i).toString())) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<User> getArrayListOfUsers() {
        return arrayListOfUsers;
    }

    public void showUsers() {
        System.out.println(arrayListOfUsers.toString());
    }

    public void createUser(String name, String pass) {
        User temp = new User();
        temp.setUserName(name);
        temp.setUserPassword(pass);
        arrayListOfUsers.add(temp);
    }






}

package Model.Request;

import Application.DataReader;
import Application.DataWriter;
import Model.Collection;
import Model.CustomException.UnapprovedUsernameException;
import Model.User;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.HashMap;

public class SignUpReq implements Request{
    private final String username;
    private final String password;
    private HashMap<String, User> systemUsers;
    private int code = 0;
    private User currentUser;

    public SignUpReq(String username, String password, HashMap<String, User> systemUsers){
        this.username = username;
        this.password = password;
        this.systemUsers = systemUsers;
    }

    @Override
    public void execute() {
        String lowerUsername = username.toLowerCase();
        for(int i=0; i<lowerUsername.length(); i++){
            char character = lowerUsername.charAt(i);
            if(!(character == 95 || (character >= 97 && character <= 122) || (character >= 48 && character <= 57))){
                code = 1;
                break;
            }
        }
        for(User user: systemUsers.values()){
            if (user.getUsername().toLowerCase().equals(lowerUsername)) {
                code = 2;
                break;
            }
        }

        if(code == 0) {
            User user = new User(username, password);
            systemUsers.put(username, user);
            makeNewCollectionList();
            currentUser = systemUsers.get(username);
            DataWriter dataWriter = new DataWriter();
            dataWriter.addAccount(user);
            DataReader reader = new DataReader();
            reader.readUsers();
        }
    }

    @Override
    public String getResponse() throws UnapprovedUsernameException {
        if(code == 0){
            return "\nYour new account was created successfully!!\n";
        }else if(code == 1){
            String UNAPPROVED_CHARACTERS = "\nYour username should contain only letters, numbers, or the underscore character!";
            throw new UnapprovedUsernameException(UNAPPROVED_CHARACTERS);
        }else{
            String EXISTED_USERNAME = "\nThe username is already existed!";
            throw new UnapprovedUsernameException(EXISTED_USERNAME);
        }
    }

    public void makeNewCollectionList(){
        DataWriter dataWriter = new DataWriter();
        dataWriter.addCollectionList(username);
    }

    public User getCurrentUser() {
        return currentUser;
    }
}

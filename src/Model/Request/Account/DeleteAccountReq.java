package Model.Request.Account;

import Application.DataWriter;
import Model.CustomException.UnAuthenticatedException;
import Model.Request.Request;
import Model.User;

import java.util.HashMap;

public class DeleteAccountReq implements Request {
    private final String username;
    private final String password;
    private HashMap<String, User> systemUsers;
    private boolean success = false;

    public DeleteAccountReq(String username, String password, HashMap<String, User> systemUsers){
        this.username = username.toLowerCase();
        this.password = password;
        this.systemUsers = systemUsers;
    }

    @Override
    public void execute() {
        for(User user: systemUsers.values()){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                systemUsers.remove(username);
                DataWriter dataWriter = new DataWriter();
                dataWriter.deleteAccount(systemUsers, username);
                success = true;
                break;
            }
        }
    }

    @Override
    public String getResponse() throws Exception {
        if(success){
            return "\nDeleted your account successfully!\n";
        }else{
            String ERROR_MESSAGE = "\nYour username or password is incorrect";
            throw new UnAuthenticatedException(ERROR_MESSAGE);
        }
    }
}

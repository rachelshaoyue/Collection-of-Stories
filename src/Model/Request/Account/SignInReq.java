package Model.Request.Account;

import Model.CustomException.UnAuthenticatedException;
import Model.Request.Request;
import Model.User;

import java.util.HashMap;

public class SignInReq implements Request {
    private final String username;
    private final String password;
    private HashMap<String, User> systemUsers;
    private boolean authenticated = false;
    private User currentUser;

    public SignInReq(String username, String password, HashMap<String, User> systemUsers){
        this.username = username.toLowerCase();
        this.password = password;
        this.systemUsers = systemUsers;
    }

    @Override
    public void execute(){
        for(User user: systemUsers.values()){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = systemUsers.get(user.getUsername());
                authenticated = true;
                break;
            }
        }
    }

    @Override
    public String getResponse() throws Exception{
        if(authenticated){
            return "\nAuthenticated!\n";
        }else{
            String ERROR_MESSAGE = "\nYour username or password is incorrect";
            throw new UnAuthenticatedException(ERROR_MESSAGE);
        }
    }

    public User getCurrentUser(){
        return currentUser;
    }
}

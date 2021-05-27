package Model.Request.Account;

import Model.Request.Request;

public class SignOffReq implements Request {
    public SignOffReq(){
    }

    @Override
    public void execute() {
        //do nothing
    }

    @Override
    public String getResponse() {
        return "\nSigned off successfully!\nThank you for using the Collection System! Enjoy your day!";
    }
}

package Model.Request.Collection;

import Application.DataWriter;
import Model.Collection;
import Model.CustomException.NotFoundException;
import Model.Request.Request;
import Model.User;

import java.util.HashMap;

public class RemoveCollectionReq implements Request {
    private User user;
    private final int ID;
    private boolean flag = false;

    public RemoveCollectionReq(User user, int ID){
        this.user = user;
        this.ID = ID;
    }
    @Override
    public void execute() {
        HashMap<Integer, Collection> collections = user.getCollectionList();
        if(collections.containsKey(ID)){
            DataWriter dataWriter = new DataWriter();
            dataWriter.removeCollection(user.getUsername(), collections.get(ID));
            collections.remove(ID);
            flag = true;
        }
    }

    @Override
    public String getResponse() throws Exception {
        if(flag){
            return "\nRemoved Collection ID " + ID + " successfully!\n";
        }else{
            String ERROR_MESSAGE = "\nThe collection isn't existed to be removed!\n";
            throw new NotFoundException(ERROR_MESSAGE);
        }
    }
}

package Model.Request.Collection;

import Application.DataWriter;
import Model.Collection;
import Model.Request.Request;
import Model.User;

import java.util.HashMap;

public class RenameCollectionReq implements Request {
    private User user;
    private final int ID;
    private final String newName;

    public RenameCollectionReq(User user, int ID, String newName){
        this.user = user;
        this.ID = ID;
        this.newName = newName;
    }

    @Override
    public void execute() {
        HashMap<Integer, Collection> collections = user.getCollectionList();
        Collection collection = collections.get(ID);
        collection.rename(newName);
        DataWriter dataWriter = new DataWriter();
        dataWriter.updateCollection(user.getUsername(), collection);
    }

    @Override
    public String getResponse() {
        return "\nRenamed Collection ID " + ID + " successfully!\n";
    }
}

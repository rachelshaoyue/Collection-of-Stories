package Model.Request.Collection;

import Application.DataWriter;
import Model.Collection;
import Model.Request.Request;
import Model.User;

public class AddCollectionReq implements Request {
    private final User user;

    public AddCollectionReq(User user){
        this.user = user;
    }

    @Override
    public void execute() {
        user.addCollection();
        DataWriter dataWriter = new DataWriter();
        dataWriter.addCollection(user.getUsername(), new Collection());
    }

    @Override
    public String getResponse() {
        return "\nAdded a new collection successfully\n";
    }
}

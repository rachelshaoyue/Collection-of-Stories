package Model.Request;

import Model.Collection;
import Model.CustomException.NotFoundException;
import Model.User;

import java.util.HashMap;

public class ViewCollectionsReq implements Request{
    private User user;
    private String message = "\nCollections:\n";

    public ViewCollectionsReq(User user){
        this.user = user;
    }

    @Override
    public void execute() {
        System.out.println(user.getCollectionList());
        for (Collection collection : user.getCollectionList().values()) {
            message += "\tCollection Name: " + collection.getName() + ", ID: " + collection.getID() + "\n";
        }
    }

    @Override
    public String getResponse() throws NotFoundException {
        if(user.getCollectionList().size() != 0){
            return message;
        }else{
            throw new NotFoundException("\nNo collections found!\n");
        }
    }
}

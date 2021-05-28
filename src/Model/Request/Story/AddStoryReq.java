package Model.Request.Story;

import Model.Collection;
import Model.Request.Request;
import Model.Story;
import Model.User;

import java.util.HashMap;

public class AddStoryReq implements Request {
    private User user;
    private Story story;
    private int collectionID;

    public AddStoryReq(User user, Story story, int collectionID){
        this.user = user;
        this.story = story;
        this.collectionID = collectionID;
    }

    @Override
    public void execute() {
        HashMap<Integer, Collection> collectionHashMap = user.getCollectionList();
        Collection collection = collectionHashMap.get(collectionID);
        collection.add(story);
    }

    @Override
    public String getResponse() throws Exception {
        return story.getInfo();
    }
}

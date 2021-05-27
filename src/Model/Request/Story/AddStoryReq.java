package Model.Request.Story;

import Model.Request.Request;
import Model.User;

public class AddStoryReq implements Request {
    private User user;

    public AddStoryReq(User user){
        this.user = user;
    }

    @Override
    public void execute() {

    }

    @Override
    public String getResponse() throws Exception {
        return null;
    }
}

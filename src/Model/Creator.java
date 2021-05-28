package Model;

import java.util.ArrayList;

public class Creator {
    private String name;
    private ArrayList<Story> storyList;

    public Creator(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addWork(Story story){
        storyList.add(story);
    }

    public void removeWork(Story story){
        storyList.remove(story);
    }

    public ArrayList<Story> getStoryList(){
        return storyList;
    }
}

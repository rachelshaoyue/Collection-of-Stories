package Model;

import java.util.HashMap;

public class Collection {
    private int ID;

    private String name;
    private HashMap<Integer, Story> collection = new HashMap<>();

    public Collection(){
        name = "untitled";
    }

    public Collection(int ID, String name){
        this.ID = ID;
        this.name = name;
    }

    public int getID(){
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public String getName(){
        return name;
    }

    public void rename(String name){
        this.name = name;
    }

    public HashMap<Integer, Story> getCollection(){
        return collection;
    }

    public void add(Story story){
        collection.put(story.getID(), story);
    }

    public void remove(Story story){
        collection.remove(story.getID());
    }
}

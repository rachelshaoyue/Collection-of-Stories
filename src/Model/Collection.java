package Model;

import java.util.HashMap;

public class Collection {
    private final int ID = counter;
    private static int counter = 0;

    private String name;
    private HashMap<Integer, Story> collection;

    public Collection(){
        name = "untitled";
        counter++;
    }

    public int getID(){
        return ID;
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

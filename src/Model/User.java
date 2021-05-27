package Model;

import java.util.HashMap;

public class User {
    private String name;
    private HashMap<Integer, Collection> collectionList = new HashMap<>();

    public User(String name){
        this.name = name;
    }

    public HashMap<Integer, Collection> getCollectionList(){
        return collectionList;
    }

    public void addCollection(String name){
        Collection newCollection = new Collection();
        collectionList.put(newCollection.getID(), newCollection);
    }

    public void removeCollection(int collectionID){
        collectionList.remove(collectionID);
    }
}

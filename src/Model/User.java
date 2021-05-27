package Model;

import java.util.HashMap;

public class User {
    private final String username;
    private final String password;
    private transient HashMap<Integer, Collection> collectionList = new HashMap<>();

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public HashMap<Integer, Collection> getCollectionList(){
        return collectionList;
    }

    public void addCollection(){
        Collection newCollection = new Collection();
        collectionList.put(newCollection.getID(), newCollection);
    }

    public void addCollection(Collection collection){
        collectionList.put(collection.getID(), collection);
    }

    public void removeCollection(int collectionID){
        collectionList.remove(collectionID);
    }
}

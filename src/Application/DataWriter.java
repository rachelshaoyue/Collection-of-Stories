package Application;

import Model.Collection;
import Model.Story;
import Model.User;

import java.io.*;

public class DataWriter {
    private final String DATA_FILES = "src/DataFiles/";
    private final String COLLECTION_FILE = DATA_FILES + "%s's Collections/%s.csv";


    public void addAccount(User user){
        try {
            String fileName = DATA_FILES + "accounts.csv";
            FileWriter fileWriter = new FileWriter(fileName,true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write("\n" + user.getUsername() + "," + user.getPassword());
            bw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void addCollection(String username, Collection collection){
        try {
            int ID = 0;
            while(true){
                File file = new File(String.format(COLLECTION_FILE, username, ID));
                if(file.exists()){
                    ID++;
                }else{
                    collection.setID(ID);
                    break;
                }
            }
            FileWriter fileWriter = new FileWriter(String.format(COLLECTION_FILE, username, ID));
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(collection.getID() + "," + collection.getName());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeCollection(String username, Collection collection){
        File file = new File(String.format(COLLECTION_FILE, username, collection.getID()));
        file.delete();
    }

    public void updateCollection(String username, Collection collection){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(String.format(COLLECTION_FILE, username, collection.getID()));
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(collection.getID() + "," + collection.getName());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCollectionList(String username){
        File file = new File(DATA_FILES + username + "'s Collections");
        //Creating a folder using mkdir() method
        boolean bool = file.mkdir();
        if(bool){
            System.out.println("Folder is created successfully");
        }else{
            System.err.println("Error Found!");
        }
    }

    public static void main(String[] args){
        DataWriter dataWriter = new DataWriter();
        dataWriter.addCollectionList(dataWriter.DATA_FILES + "/Accounts");
    }
}

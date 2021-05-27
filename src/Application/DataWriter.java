package Application;

import Model.Collection;
import Model.User;

import java.io.*;
import java.util.HashMap;

public class DataWriter {
    private final String DATA_FILES = "src/DataFiles/";
    private final String COLLECTION_FILE = DATA_FILES + "%s's Collections/%s.csv";
    private final String ACCOUNTS_FILE = DATA_FILES + "accounts.csv";
    private final String COLLECTION_LIST_FOLDER = DATA_FILES + "%s's Collections";


    public void addAccount(User user){
        try {
            String fileName = ACCOUNTS_FILE;
            FileWriter fileWriter = new FileWriter(fileName,true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write("\n" + user.getUsername() + "," + user.getPassword());
            bw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void addCollectionList(String username){
        File file = new File(String.format(COLLECTION_LIST_FOLDER, username));
        //Creating a folder using mkdir() method
        boolean bool = file.mkdir();
        if(bool){
            System.out.println("Folder is created successfully");
        }else{
            System.err.println("Error Found!");
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

    public void deleteAccount(HashMap<String, User> users, String username){
        updateAccount(users);
        File folder = new File(String.format(COLLECTION_LIST_FOLDER, username));
        File[] files = folder.listFiles();
        if(files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        folder.delete();
    }

    public void updateAccount(HashMap<String, User> users){
        try {
            String fileName = ACCOUNTS_FILE;
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write("Username,Password");
            for(User user: users.values()){
                bw.write("\n" + user.getUsername() + "," + user.getPassword());
            }
            bw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
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

    public static void main(String[] args){
        DataWriter dataWriter = new DataWriter();
        dataWriter.addCollectionList(dataWriter.DATA_FILES + "/Accounts");
    }
}

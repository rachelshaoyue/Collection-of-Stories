package Application;

import Model.Collection;
import Model.User;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class DataReader {
    private final String DATA_FILES = "src/DataFiles/";

    public HashMap<String, User> readUsers(){
        HashMap<String, User> users = new HashMap<>();
        try {
            File myObj = new File(DATA_FILES + "accounts.csv");
            Scanner scanner = new Scanner(myObj);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] fields = data.split(",");
                User user = new User(fields[0], fields[1]);
                users.put(user.getUsername(), user);
                readCollections(user);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void readCollections(User user) throws FileNotFoundException {
        File folder = new File(DATA_FILES + user.getUsername() + "'s Collections");
        File[] files = folder.listFiles();
        assert files != null;
        for (File file : files) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] fields = data.split(",");
                user.addCollection(new Collection(Integer.parseInt(fields[0]), fields[1]));
            }
            scanner.close();
        }
    }

    public static void main(String[] args) throws Exception {
        DataReader reader = new DataReader();
        reader.readUsers();
    }
}

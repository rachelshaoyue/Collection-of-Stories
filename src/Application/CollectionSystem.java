package Application;

import Controller.CommandLine;
import Model.User;

import java.util.HashMap;

public class CollectionSystem {

    public static void main(String[] args) throws Exception {
        DataReader dataReader = new DataReader();
        HashMap<String, User> users = dataReader.readUsers();
        CommandLine commandLine = new CommandLine(users);
        commandLine.start();
    }
}

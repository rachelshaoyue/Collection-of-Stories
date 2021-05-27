package Controller;

import Model.Request.*;
import Model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class CommandLine {
    private HashMap<String, User> systemUsers;
    private User currentUser;

    public CommandLine(HashMap<String, User> systemUsers){
        this.systemUsers = systemUsers;
    }

    public void start() throws Exception {
        System.out.println("Welcome to the Collection System");
        BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.print("\nType in 1 to sign in or 2 to sign up\n\nEnter your input: ");
            String userInput = standardInput.readLine();
            if (userInput.equals("1") || userInput.equals("2")) {
                if(login(standardInput, userInput)){
                    break;
                }
            }
        }
        readLoop(standardInput);
    }

    public boolean login(BufferedReader standardInput, String input) {
        Request request = null;
        while(true) {
            try{
                sleep(50);
                if (input.equals("1")) {
                    System.out.print("\nEnter Your Username: ");
                    String username = standardInput.readLine();
                    System.out.print("\nEnter Your Password: ");
                    String password = standardInput.readLine();
                    request = new SignInReq(username, password, systemUsers);
                } else {
                    System.out.print("\nType Your Username: ");
                    String username = standardInput.readLine();
                    System.out.print("\nType Your Password: ");
                    String password = standardInput.readLine();
                    request = new SignUpReq(username, password, systemUsers);
                }
                assert request != null;
                request.execute();
                if(request instanceof SignInReq){
                    SignInReq req = (SignInReq) request;
                    currentUser = req.getCurrentUser();
                }else{
                    SignUpReq req = (SignUpReq) request;
                    currentUser = req.getCurrentUser();
                }
                    System.out.println(request.getResponse());
                    return true;
            } catch (InterruptedException e) {
                //ignored
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void readLoop(BufferedReader standardInput) throws Exception {
        while(true){
            try {
                sleep(50);
                System.out.print("Enter Request: ");
                String userInput = standardInput.readLine();
                parseInput(userInput);
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    public void parseInput(String input) throws Exception {
        Request request = new HelpReq("");

        String[] fields = input.split(" ");
        switch (fields[0]) {
            case "/help":
                if (fields.length > 1) {
                    request = new HelpReq(fields[1]);
                } else {
                    request = new HelpReq("");
                }
                break;
            case "/addCollection":
                request = new AddCollectionReq(currentUser);
                break;
            case "/removeCollection":
                if (fields.length == 2) {
                    try {
                        int ID = Integer.parseInt(fields[1]);
                        request = new RemoveCollectionReq(currentUser, ID);
                        break;
                    }catch (NumberFormatException n){
                        displayHelp();
                        break;
                    }
                }else{
                    displayHelp();
                    break;
                }
            case "/viewCollections":
                request = new ViewCollectionsReq(currentUser);
                break;
            case "/renameCollection":
                if (fields.length == 3) {
                    try {
                        int ID = Integer.parseInt(fields[1]);
                        String newName = fields[2];
                        request = new RenameCollectionReq(currentUser, ID, newName);
                        break;
                    } catch (NumberFormatException n){
                        displayHelp();
                        break;
                    }
                }else{
                    displayHelp();
                    break;
                }
            default:
                displayHelp();
                break;
        }
        request.execute();
        try {
            System.out.println(request.getResponse());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void displayHelp(){
        System.err.println("\nInvalid Request\n");
    }
}

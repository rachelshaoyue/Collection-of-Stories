package Controller;

import Model.Collection;
import Model.Request.HelpReq;
import Model.Request.Request;
import Model.Request.SignInReq;
import Model.Request.SignUpReq;
import Model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CommandLine {
    private HashMap<String[], User> systemUsers;
    private HashMap<Integer, Collection> collectionList = new HashMap<>();

    public CommandLine(HashMap<String[], User> systemUsers){
        this.systemUsers = systemUsers;
    }

    public void start() throws Exception {
        System.out.println("Welcome to the Collection System\n");
        BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.print("Type in 1 to sign in or 2 to sign up\nEnter your input: ");
            String userInput = standardInput.readLine();
            if (userInput.equals("1") || userInput.equals("2")) {
                if(login(standardInput, userInput)){
                    break;
                }
            }
        }
        readLoop(standardInput);
    }

    public boolean login(BufferedReader standardInput, String input) throws IOException {
        Request request = null;
        while(true) {
            if (input.equals("1")) {
                System.out.print("\nEnter Your Username: ");
                String username = standardInput.readLine();
                System.out.print("\nEnter Your Password: ");
                String password = standardInput.readLine();
                request = new SignInReq(username, password, systemUsers, collectionList);
            } else if (input.equals("2")) {
                System.out.print("\nType Your Username: ");
                String username = standardInput.readLine();
                System.out.print("\nType Your Password: ");
                String password = standardInput.readLine();
                request = new SignUpReq(username, password, systemUsers, collectionList);
            }
            assert request != null;
            request.execute();
            try {
                System.out.println(request.getResponse());
                return true;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void readLoop(BufferedReader standardInput) throws Exception {
        while(true){
            try {
                System.out.print("Enter Request: ");
                String userInput = standardInput.readLine();
                parseInput(userInput);
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    public void parseInput(String input) throws Exception {
        Request request;

        String[] fields = input.split(" ");
        switch(fields[0]){
            case "/help":
                if(fields.length > 1) {
                    request = new HelpReq(fields[1]);
                }else{
                    request = new HelpReq("");
                }
                break;
            default:
                System.out.println("Invalid Request\n");
                request = new HelpReq("");
                break;
        }
        request.execute();
        if(request.getResponse() != null) {
            System.out.println(request.getResponse());
        }
    }
}

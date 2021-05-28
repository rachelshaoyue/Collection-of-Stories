package Controller;

import Model.*;
import Model.Request.*;
import Model.Request.Collection.*;
import Model.Request.Account.*;
import Model.Request.Story.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

public class CommandLine {
    private HashMap<String, User> systemUsers;
    private User currentUser;
    private BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in));

    public CommandLine(HashMap<String, User> systemUsers){
        this.systemUsers = systemUsers;
    }

    public void start() throws Exception {
        System.out.println("\nWelcome to the Collection System");
        while(true) {
            System.out.print("\nType in 1 to sign in or 2 to sign up or\n\nEnter your input: ");
            String userInput = standardInput.readLine();
            if (userInput.equals("1") || userInput.equals("2")) {
                if(login(userInput)){
                    break;
                }
            }
        }
        readLoop();
    }

    public boolean login(String input) {
        Request request;
        while(true) {
            try{
                sleep(30);
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

    public void readLoop() throws Exception {
        while(true){
            try {
                sleep(30);
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
            case "/deleteAccount":
                if (fields.length == 3) {
                        String username = fields[1];
                        String password = fields[2];
                        request = new DeleteAccountReq(username, password, systemUsers);
                        currentUser = null;
                        break;
                }else{
                    displayHelp();
                    break;
                }
            case "/signOff":
                request = new SignOffReq();
                currentUser = null;
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
            case "/addStory":
                if (fields.length == 2) {
                    try {
                        int ID = Integer.parseInt(fields[1]);
                        Story story = addStory(ID);
                        System.out.println("hello");
                        request = new AddStoryReq(currentUser, story, ID);
                        System.out.println("hello");
                        break;
                    }catch (NumberFormatException n){
                        n.printStackTrace();
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
        if(currentUser == null){
            start();
        }
    }
    
    public void displayHelp(){
        System.err.println("\nInvalid Request\n");
    }

    public Story addStory(int collectionID) throws IOException {
        String title;
        Category category = null;

        while(true) {
            System.out.print("Enter title:");
            title = standardInput.readLine();
            if(!title.isEmpty()){
                break;
            }
            System.out.println("Title required");
        }
        while(true) {
            HashMap<String, Category> categories = new HashMap<>();
            System.out.println("Category:");
            for(Category option : EnumSet.allOf(Category.class)){
                System.out.println("\t" + option.toString());
                categories.put(option.toString(), option);
            }
            System.out.println("Enter category:");
            String input = standardInput.readLine();
            if(categories.containsKey(input)){
                category = categories.get(input);
                break;
            }
            System.out.println("Category required");
        }
        ArrayList<Creator> creatorList = new ArrayList<>();
        while(true) {
            System.out.println("Enter creator's first and last names or click ENTER to continue");
            String creatorName = standardInput.readLine();
            if(creatorName.isEmpty()){
                break;
            }
            if(creatorList.contains(creatorName)){
                System.out.println("The list already contains this creator.");
            }
            Creator creator = new Creator(creatorName);
            creatorList.add(creator);
        }
        HashSet<String> genreSet = new HashSet<>();
        while(true) {
            System.out.println("Enter genre or click ENTER to continue");
            String genre = standardInput.readLine();
            if(genre.isEmpty()){
                break;
            }
            if(genreSet.contains(genre)){
                System.out.println("The list already contains this genre.");
            }
            genreSet.add(genre);
        }
        HashSet<String> sourceSet = new HashSet<>();
        while(true) {
            System.out.println("Enter source of where you can view this story or click ENTER to continue");
            String source = standardInput.readLine();
            if(source.isEmpty()){
                break;
            }
            if(sourceSet.contains(source)){
                System.out.println("The list already contains this genre.");
            }
            sourceSet.add(source);
        }

        String[] values = new String[]{"", "", "0", "", ""};
        String[] messages = new String[]{"# of seasons", "# of episodes", "duration in minutes", "# of volumes", "# of pages"};
        for(int i=0; i<5; i++){
            while(true) {
                System.out.print("Enter " + messages[i] + " or press ENTER to continue");
                if (i == 2) {
                    System.out.println(":");
                    String value = standardInput.readLine();
                    if(!value.isEmpty()) {
                        try {
                            int duration = Integer.parseInt(value);
                            values[i] = value;
                            break;
                        } catch (NumberFormatException n) {
                            System.out.println("Please type an integer");
                        }
                    }else{
                        break;
                    }
                } else {
                    System.out.println("or type <Text> (e.g. Ongoing):");
                    String value = standardInput.readLine();
                    values[i] = value;
                    break;
                }
            }
        }
        Length length = new Length(values[0], values[1], Integer.parseInt(values[2]), values[3], values[4]);

        System.out.println("Enter your rating or click ENTER to continue");
        String rating = standardInput.readLine();

        System.out.println("Enter your review or click ENTER to continue");
        String review = standardInput.readLine();

        Story story = new Story(title, category, rating, creatorList, length, sourceSet,
                review, genreSet);
        return  story;
    }


}

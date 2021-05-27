package Model.Request;

public class HelpReq implements Request{
    private final String argument;
    private String message;

    public HelpReq(String argument){
        this.argument = argument;
    }

    @Override
    public void execute() {
        message = "";
        switch (argument) {
            case "deleteAccount" -> {
                message += "Delete your account\n";
                message += "\tType in /deleteAccount <Username> <Password> to delete your account\n";
            }
            case "signOff" -> {
                message += "Sign off\n";
                message += "\tType in /signOff to sign off your account\n";
            }
            case "viewCollections" -> {
                message += "View your collections\n";
                message += "\tType in /viewCollections to view your collections\n";
            }
            case "addCollection" -> {
                message += "Make a new collection\n";
                message += "\tType in /addCollection to make a new collection\n";
            }
            case "removeCollection" -> {
                message += "Remove a collection\n";
                message += "\tType in /removeCollection <Collection ID> to remove a collection\n";
            }
            case "renameCollection" -> {
                message += "Rename a collection\n";
                message += "\tType in /renameCollection <Collection ID> <New Name> to rename a collection\n";
            }
            default -> {
                message += "Help Menu\n";
                message += "\tType in /help <Request Argument> to see more info!\n";
                message += "\tRequest Arguments: [deleteAccount] [signOff] [viewCollections] [addCollection] [removeCollection] [renameCollection]\n";
            }
        }
    }

    @Override
    public String getResponse() {
        return message;
    }
}

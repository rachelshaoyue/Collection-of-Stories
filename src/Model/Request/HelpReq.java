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
            case "newAccount" -> {
                message += "Create a New Account\n";
                message += "\tType in /newAccount <Your Name> to create your account\n";
            }
            case "delAccount" -> {
                message += "Delete Your Account\n";
                message += "\tType in /delAccount <Your Name> to delete your account\n";
            }
            case "switchAccount" -> {
                message += "Switch the Account\n";
                message += "\tType in /switchAccount <Name> to switch the account\n";
            }
            default -> {
                message += "Help Menu\n";
                message += "\tType in /help <Request Argument> to see more info!\n";
                message += "\tRequest Arguments: [newAccount] [delAccount] [switchAccount]\n";
            }
        }
    }

    @Override
    public String getResponse() {
        return message;
    }
}

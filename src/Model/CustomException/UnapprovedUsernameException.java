package Model.CustomException;

public class UnapprovedUsernameException extends Exception{
    public UnapprovedUsernameException(String message){
        super(message);
    }
}

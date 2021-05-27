package Model.CustomException;

public class UnAuthenticatedException extends Exception{
    public UnAuthenticatedException(String message){
        super(message);
    }
}

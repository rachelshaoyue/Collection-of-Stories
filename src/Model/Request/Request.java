package Model.Request;

public interface Request {
    void execute();
    String getResponse() throws Exception;
}

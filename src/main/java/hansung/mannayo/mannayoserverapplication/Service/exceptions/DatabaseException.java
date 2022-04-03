package hansung.mannayo.mannayoserverapplication.Service.exceptions;

public class DatabaseException extends RuntimeException{

    private static final long serialVersionUID = 1l;
    public DatabaseException(String message) {
        super(message);
    }
}

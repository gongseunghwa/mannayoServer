package hansung.mannayo.mannayoserverapplication.exceptions;

public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(Object nickName){
        super("Resource not found. NickName : " + nickName );
    }
}

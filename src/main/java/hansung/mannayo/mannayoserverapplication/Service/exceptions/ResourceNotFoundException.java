package hansung.mannayo.mannayoserverapplication.Service.exceptions;

public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(Object nickName){
        super("Resource not found. NickName : " + nickName );
    }
}

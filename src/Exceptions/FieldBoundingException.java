package Exceptions;

public class FieldBoundingException extends Exception{
    public FieldBoundingException(String message){
        super(message + " Please follow this format {## ## ## ##}");
    }
}

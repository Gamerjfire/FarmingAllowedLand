package Exceptions;

public class NotSized4Exception extends Exception{
    public NotSized4Exception(String message){
        super(message + " Please follow this format \"## ## ## ##\"");
    }
}

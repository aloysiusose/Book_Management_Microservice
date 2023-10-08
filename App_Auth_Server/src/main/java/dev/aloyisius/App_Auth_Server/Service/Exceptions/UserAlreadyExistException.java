package dev.aloyisius.App_Auth_Server.Service.Exceptions;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String message) {
        super(message);
    }
}

package co.com.alianza.fiduciaria.exception;

import org.springframework.http.HttpStatus;

public class ClienteException extends RuntimeException{

    private HttpStatus errorCode;

    public ClienteException(String message){
        super(message);
    }

    public ClienteException(String message, HttpStatus errorCode){
        super(message);
    }

    public HttpStatus getErrorCode(){
        return  this.errorCode;
    }
}

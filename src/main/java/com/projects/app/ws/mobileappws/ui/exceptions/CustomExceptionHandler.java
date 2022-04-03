package com.projects.app.ws.mobileappws.ui.exceptions;

import java.util.Date;

import com.projects.app.ws.mobileappws.ui.model.response.ErrorResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

  @ExceptionHandler(value={Exception.class})
  public ResponseEntity<Object> handleAnyExceptions(Exception ex,WebRequest request){
  
      ErrorResponse errorResponse = new ErrorResponse();
      errorResponse.setTimestamp(new Date());
      errorResponse.setMessage(ex.getLocalizedMessage());
      return new ResponseEntity<>(errorResponse,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
  }
  //handle Null Pointer Exception and UserName Not Found
  @ExceptionHandler(value={NullPointerException.class,UserNameNotFoundException.class})
  public ResponseEntity<Object> handleMoreExceptions(Exception ex,WebRequest request){
  
      ErrorResponse errorResponse = new ErrorResponse();
      errorResponse.setTimestamp(new Date());
      errorResponse.setMessage(ex.getMessage());
    System.out.println(ex);
      return new ResponseEntity<>(errorResponse,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /*
  //handle only User Service Pointer Exception
    @ExceptionHandler(value={UserNameNotFoundException.class})
    public ResponseEntity<Object> handleUserService(UserNameNotFoundException ex,WebRequest request){
    
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp((java.sql.Date) new Date());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
  */


}

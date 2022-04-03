package com.projects.app.ws.mobileappws.ui.exceptions;

public class UserNameNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UserNameNotFoundException() {}

  public UserNameNotFoundException(String message){
    super(message);
  }
  
}

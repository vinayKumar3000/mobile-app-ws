package com.projects.app.ws.mobileappws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {

  @NotNull(message="First Name cannot be null")
  private String firstName;
  
  @NotNull(message="Last Name cannot be null")
  private String lastName;
  
  @NotNull(message="Email Name cannot be null")
  @Email
  private String email;
  
  @NotNull(message="Password Name cannot be null")
  @Size(min=4,max=8,message="Password must be at least 4 characters")
  private String password;



  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  
}

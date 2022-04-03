package com.projects.app.ws.mobileappws.ui.services;

import com.projects.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.projects.app.ws.mobileappws.ui.model.response.UserRest;

public interface UserService {
  
  public UserRest createUser(UserDetailsRequestModel userDetails);

}

package com.projects.app.ws.mobileappws.ui.services;

import java.util.HashMap;
import java.util.Map;

import com.projects.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.projects.app.ws.mobileappws.ui.model.response.UserRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


  Map<String,UserRest> users;

  Utils utils;

  public UserServiceImpl() {

  }

  @Autowired
  public UserServiceImpl(Utils utils) {
    this.utils=utils;
  }

  @Override
  public UserRest createUser(UserDetailsRequestModel userDetails) {
    
    UserRest userRest = new UserRest();
    userRest.setFirstName(userDetails.getFirstName());
    userRest.setLastName(userDetails.getLastName());
    userRest.setEmail(userDetails.getEmail());
    String uId=utils.generateId();
    userRest.setUserId(uId);

    if(users==null){
      users=new HashMap<>();
    }
    users.put(uId, userRest);

    return userRest;
  }
  
}

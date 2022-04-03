package com.projects.app.ws.mobileappws.ui.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.projects.app.ws.mobileappws.ui.exceptions.UserNameNotFoundException;
import com.projects.app.ws.mobileappws.ui.model.request.UpdateUserDetailsRequestModel;
import com.projects.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.projects.app.ws.mobileappws.ui.model.response.UserRest;
import com.projects.app.ws.mobileappws.ui.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") //http://localhost:8080/users
public class UserController {
  
  @Autowired
  UserService userService;

  Map<String,UserRest> users;

  //required = false is used for String paramter as primitive cannot be converted into null
  @GetMapping
  public String getUsers(@RequestParam(value="page",defaultValue="1") int page,@RequestParam(value="limit",defaultValue="50") int limit,@RequestParam(value="sort",defaultValue="desc",required=false) String sort) {
    return "Users -- " + "page :" + page + "limit " + limit + "sort "+ sort;
  }
/*
  @GetMapping(path="/{userId}")
  public String getUser(@PathVariable String userId) {
    return "Get User" + userId;
  }
  @GetMapping(path="/{userId}",produces ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
  public UserRest getUser(@PathVariable String userId) {
  UserRest userRest = new UserRest();
  userRest.setFirstName("vinay");
  userRest.setLastName("kumar");
  userRest.setEmail("vinay@gmail.com");
  return userRest;
}
  */
  
  @GetMapping(path="/{userId}",produces ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

    if(users.containsKey(userId)){
      return new ResponseEntity<>(users.get(userId),HttpStatus.OK);
    }else{
      throw new UserNameNotFoundException("username not found triggered");
    }
    
  }

  /*
  mvn install

  ls target/*.*

  java -jar target/*.*
  
  */

  @PostMapping(produces ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails){
   
    if(users==null){
      users=new HashMap<String, UserRest>();
    }
    UserRest userRest=userService.createUser(userDetails);
    users.put(userRest.getUserId(), userRest);   
    return new ResponseEntity<>(userRest,HttpStatus.OK);
  }

  @PutMapping(path="/{userId}",produces ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<UserRest> updateUser(@PathVariable String userId,@RequestBody UpdateUserDetailsRequestModel updateUserDetailsRequestModel){

    if(users.containsKey(userId)==false){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }else{
      UserRest userRest = users.get(userId);
      userRest.setFirstName(updateUserDetailsRequestModel.getFirstName());
      userRest.setLastName(updateUserDetailsRequestModel.getLastName());
      users.put(userId, userRest);
      return new ResponseEntity<>(users.get(userId),HttpStatus.OK);
    }
  }

  @DeleteMapping(path="/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable String userId){
    
    if(users.containsKey(userId)==false){
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }else{
      users.remove(userId);
      return new ResponseEntity<>(HttpStatus.OK);
    }

  }

}

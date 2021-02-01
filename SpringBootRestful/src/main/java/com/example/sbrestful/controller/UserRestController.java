package com.example.sbrestful.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.sbrestful.entities.User;

@RestController
@RequestMapping("/rest")
public class UserRestController {
  
   public static HashMap<Integer, User> mapUser = new HashMap<Integer, User>();
    static {
      mapUser.put(1, new User(1, "Dat", "123456"));
      mapUser.put(2, new User(2, "Admin", "admin"));
      mapUser.put(3, new User(3, "Funny", "123456"));
      mapUser.put(4, new User(4, "Ping", "1234"));
      mapUser.put(5, new User(5, "user5", "1234"));
      mapUser.put(6, new User(6, "user6", "1234"));
    }
    
    /* ---------------- GET ALL USER ------------------------ */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
      List<User> listUser = new ArrayList<User>(mapUser.values());
      return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
    }
    
    /* ---------------- GET USER BY ID ------------------------ */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
      User user = mapUser.get(id);
      if (user != null) {
        return new ResponseEntity<Object>(user, HttpStatus.OK);
      }
      return new ResponseEntity<Object>("Not Found User", HttpStatus.NO_CONTENT);
    }
    
    /* ---------------- CREATE NEW USER ------------------------ */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
	  public ResponseEntity<String> createUser(@RequestBody User user) {
	    if (mapUser.containsKey(user.getId())) {
	      return new ResponseEntity<String>("User Already Exist!", HttpStatus.CONFLICT);
	    }
	    mapUser.put(user.getId(), user);
	    return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
	  }
    /* ---------------- DELETE USER ------------------------ */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
      User user = mapUser.get(id);
      if (user == null) {
        return new ResponseEntity<String>("Not Found User", HttpStatus.OK);
      }
      
      mapUser.remove(id);
      return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
    }
    
    /* ---------------- UPDATE USER ------------------------ */
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody User user) {
      User oldUser = mapUser.get(user.getId());
      if (oldUser == null) {
        return new ResponseEntity<String>("Not Found User", HttpStatus.NO_CONTENT);
      }
      
      // replace old user by new user.
      mapUser.put(user.getId(), user);
      return new ResponseEntity<String>("Updated!", HttpStatus.OK);
    }
    
}
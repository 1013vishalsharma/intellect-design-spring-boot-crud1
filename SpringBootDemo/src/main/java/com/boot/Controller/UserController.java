package com.boot.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.User;
import com.boot.model.UserRequest;
import com.boot.model.UserResponse;
import com.boot.model.updateUserRequest;

@RestController
@RequestMapping("/api")
public class UserController {

	
	List <User> userDataList = new ArrayList<User>();
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserRequest user) throws Exception{
		UserResponse resp = new UserResponse();
		for(int i=0;i<userDataList.size();i++){
			User savedUser = userDataList.get(i);
			if(savedUser.getEmail().equals(user.getEmail())){
				if(savedUser.isActive() == user.isActive()){
					resp.setResMsg("user is already present");
					resp.setUserId(savedUser.getId());
					resp.setErroCode(HttpStatus.CONFLICT);
					return ResponseEntity.ok(resp);
				}
				else if(!savedUser.isActive){
					savedUser.setActive(true);
					resp.setResMsg("user was deactiive and has been activated");
					resp.setUserId(savedUser.getId());
					resp.setErroCode(HttpStatus.OK);
					return ResponseEntity.ok(resp);
				}
			}
		}
		User savedUser = new User();
		savedUser.setId(user.getId());
		savedUser.setActive(user.isActive());
		savedUser.setBirthDate(new SimpleDateFormat("dd-MMM-yyy").parse(user.getBirthDate()));
		savedUser.setEmail(user.getEmail());
		savedUser.setfName(user.getfName());
		savedUser.setlName(user.getlName());
		savedUser.setPinCode(Integer.parseInt(user.getPinCode()));
		userDataList.add(savedUser);
		
		resp.setResMsg("user has been created");
		resp.setUserId(savedUser.getId());
		resp.setErroCode(HttpStatus.CREATED);
		return ResponseEntity.ok(resp);
	}
	
	
	@RequestMapping(value = "/update/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody updateUserRequest updateRequest) throws Exception{
		boolean found = false;
		UserResponse resp = new UserResponse();
		for(int i=0;i<userDataList.size();i++){
			User savedUser = userDataList.get(i);
			if(savedUser.getId().equals(id)){
				savedUser.setBirthDate(new SimpleDateFormat("dd-MMM-yyy").parse(updateRequest.getBirthDate()));
				savedUser.setPinCode(Integer.parseInt(updateRequest.getPinCode()));
				found = true;
				System.out.println(savedUser.toString());
			}
		}
		
		if(found==false){
			resp.setResMsg("user is not present");
			resp.setUserId(id);
			resp.setErroCode(HttpStatus.BAD_REQUEST);
			return ResponseEntity.ok(resp);
		}
		else{
			
			resp.setResMsg("user has been updated");
			resp.setUserId(id);
			resp.setErroCode(HttpStatus.OK);
			return ResponseEntity.ok(resp);
		//return ResponseEntity.status(HttpStatus.OK).build();
		}
	}
	
	@RequestMapping(value = "/delete/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable String id){
		boolean found = false;
		ListIterator<User> itr = userDataList.listIterator();
		while(itr.hasNext()){
			User user = itr.next();
			if(user.getId().equals(id)){
				user.setActive(false);
				found = true;
				System.out.println(user.toString());
			}
		}
		UserResponse resp = new UserResponse();
		if(found == false){
			resp.setResMsg("user is not present");
			resp.setUserId(id);
			resp.setErroCode(HttpStatus.BAD_REQUEST);
			return ResponseEntity.ok(resp);
		}
		else{
			resp.setResMsg("user has been deactivated");
			resp.setUserId(id);
			resp.setErroCode(HttpStatus.OK);
			return ResponseEntity.ok(resp);
		}
	}
}

package com.dangalgames.webengage.utility.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dangalgames.webengage.utility.model.User;
import com.dangalgames.webengage.utility.service.UserService;

@RestController
public class UserResource {

	@Autowired
	private UserService service;

	@PostMapping("/users")
	public ResponseEntity  storeUser(@RequestBody User user) throws IOException {
		Boolean response = service.storeUser(user);
		
		if(response == true) {
		return ResponseEntity.ok(HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
			
		}
			
	}
	
}
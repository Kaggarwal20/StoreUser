package com.dangalgames.webengage.utility.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dangalgames.webengage.utility.configuration.Config;
import com.dangalgames.webengage.utility.model.Response;
import com.dangalgames.webengage.utility.model.User;
import com.dangalgames.webengage.utility.service.UserService;

@RestController
public class UserResource {

	@Autowired
	private UserService service;
	
	@Autowired
	private Config config;
	
	
	

	@PostMapping("/users")
	public ResponseEntity<Response> storeUser(@RequestBody User user, @RequestHeader String token) throws Exception {
		Boolean response = false;;


		if (token.equals(config.getToken())) {

			 response = service.storeUser(user);
		}

		if(response == true) {
			return ResponseEntity.ok().body(new Response("SUCCESS"));
			
		}else {
			return ResponseEntity.badRequest().body(new Response("FAIL"));
			
			
		}

	}

}
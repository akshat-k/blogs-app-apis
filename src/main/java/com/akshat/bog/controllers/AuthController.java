package com.akshat.bog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshat.bog.exceptions.APIException;
import com.akshat.bog.payloads.JwtAuthRequest;
import com.akshat.bog.payloads.JwtAuthResponse;
import com.akshat.bog.payloads.UserDto;
import com.akshat.bog.security.CustomUserDetailService;
import com.akshat.bog.security.JwtTokenHelper;
import com.akshat.bog.services.UserServices;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserServices userServices;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

		System.out.println();
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetail = this.userDetailsService.loadUserByUsername(request.getUsername());
		System.out.println("akshat ------->  " + userDetail.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetail);
		System.out.println("KAtyayan  ----- > " + token);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		System.out.println(authenticationToken.toString());
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new APIException("UserName or Password is wrong");
		}

	}
	
	// Register New USer API
	
	@PostMapping("/register")
	public ResponseEntity<UserDto>  registerUser(@RequestBody UserDto userDto){
		
		UserDto registeredNewUser = this.userServices.registerNewUser(userDto);
		
		return new ResponseEntity<UserDto>(registeredNewUser,HttpStatus.CREATED);
	}
}

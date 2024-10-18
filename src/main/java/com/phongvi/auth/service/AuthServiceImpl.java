package com.phongvi.auth.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.phongvi.auth.dto.GoogleTokenDTO;
import com.phongvi.auth.dto.LoginDTO;
import com.phongvi.auth.dto.LoginResponseDTO;
import com.phongvi.user.Provider;
import com.phongvi.user.User;
import com.phongvi.user.UserRepository;
import com.phongvi.user.UserRole;
import com.phongvi.user.UserStatus;
import com.phongvi.user.service.UserService;
import com.phongvi.utils.JwtUtils;
import com.phongvi.utils.Utils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
public class AuthServiceImpl implements AuthService {
	
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	private final UserRepository userRepository;
	private final GoogleIdTokenVerifier verifier;
	private final UserService userService;
	
	public AuthServiceImpl(@Value("${app.googleClientId}") String clientId, UserRepository userRepository, 
			UserService userService, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.userService = userService;
		this.jwtUtils = jwtUtils;
		NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
		this.verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
	}
	
	@Override
	public ResponseEntity<?> authenticateUser(LoginDTO loginRequest) {
		Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        
        LoginResponseDTO loginResponse = LoginResponseDTO.builder()
        		.username(userDetails.getUsername())
        		.jwtToken(jwtToken)
        		.roles(roles)
        		.build();
        
        var response = new HashMap<String, Object>();
        response.put("data", loginResponse);
        response.put("timestamp", Utils.getCurrentTimestamp());
        response.put("status", HttpStatus.OK.value());
        
        return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> authenticateGoogleUser(GoogleTokenDTO tokenDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private User verifyIDToken(String idToken) {
		try {
			GoogleIdToken idTokenObj = verifier.verify(idToken);
			if (idTokenObj == null) {
				return null;
			}
			
			GoogleIdToken.Payload payload = idTokenObj.getPayload();
			String firstName = (String) payload.get("given_name");
			String lastName = (String) payload.get("family_name");
			String email = payload.getEmail();
			String pictureUrl = (String) payload.get("picture");
			
			return User.builder()
					.email(email)
					.fullname(firstName + " " + lastName)
					.profileImg(pictureUrl)
					.provider(Provider.GOOGLE)
					.build();
			
		} catch (GeneralSecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	private User createOrUpdateUser(User user) {
		Optional<User> existingUserOptional = userRepository.findByEmail(user.getEmail());
		if (existingUserOptional.isEmpty()) {
			user.setUsername(user.getEmail());
			user.setRole(UserRole.CUSTOMER);
			user.setStatus(UserStatus.ACTIVE);
			user.setProvider(Provider.GOOGLE);
			user.setCreatedAt(Utils.getCurrentTimestamp());
			user.setLastChangedAt(Utils.getCurrentTimestamp());;
			user.setLastChangedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			return userService.createUser(user);
		}
		
		User existingUser = existingUserOptional.get();
		existingUser.setFullname(user.getFullname());
		existingUser.setProfileImg(user.getProfileImg());
		return userRepository.save(existingUser);
	}

}

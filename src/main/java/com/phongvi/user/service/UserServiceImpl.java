package com.phongvi.user.service;

import org.springframework.stereotype.Service;

import com.phongvi.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository repository;
	private final UserMappingService mappingService;
	
	
}

package com.github.wenzhu.maven.user.service;

import com.github.wenzhu.maven.user.model.User;

public interface IUserService {
	public void add(User user);
	
	public User loadByUsername(String username);
}

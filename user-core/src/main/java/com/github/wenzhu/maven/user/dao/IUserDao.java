package com.github.wenzhu.maven.user.dao;

import com.github.wenzhu.maven.user.model.User;

public interface IUserDao {
	public void add(User user);
	public User loadByUsername(String username);
}

package com.github.wenzhu.maven.user.service;

import com.github.wenzhu.maven.user.dao.IUserDao;
import com.github.wenzhu.maven.user.model.User;

public class UserService implements IUserService {
	private IUserDao userDao;

	public UserService(IUserDao userDao) {
		super();
		this.userDao = userDao;
	}

	public void add(User user) {
		userDao.add(user);
	}

	public User loadByUsername(String username) {
		return userDao.loadByUsername(username);
	}

}

package com.github.wenzhu.maven.user.service;

import static org.easymock.EasyMock.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.wenzhu.maven.user.dao.IUserDao;
import com.github.wenzhu.maven.user.model.User;
import com.github.wenzhu.maven.user.service.IUserService;
import com.github.wenzhu.maven.user.service.UserService;
import com.github.wenzhu.maven.user.util.EntitiesHelper;

public class TestUserService {
	private IUserDao userDao;
	private IUserService userService;
	private User baseUser;
	
	@Before
	public void setUp() {
		userDao = createStrictMock(IUserDao.class);
		userService = new UserService(userDao);
		baseUser = new User("admin","123","admin");
	}
	
	@Test
	public void testAdd() {
		userDao.add(baseUser);
		expectLastCall();
		replay(userDao);
		userService.add(baseUser);
	}
	
	@Test
	public void testLoadByUsername() {
		expect(userDao.loadByUsername("admin")).andReturn(baseUser);
		replay(userDao);
		User tu = userService.loadByUsername("admin");
		EntitiesHelper.assertUser(tu,baseUser);
	}
	
	@After
	public void tearDown() {
		verify(userDao);
	}
}

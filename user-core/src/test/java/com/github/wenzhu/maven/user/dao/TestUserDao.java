package com.github.wenzhu.maven.user.dao;

import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.wenzhu.maven.user.dao.IUserDao;
import com.github.wenzhu.maven.user.dao.UserDao;
import com.github.wenzhu.maven.user.model.User;
import com.github.wenzhu.maven.user.util.AbstractDbUnitTestCase;
import com.github.wenzhu.maven.user.util.EntitiesHelper;

public class TestUserDao extends AbstractDbUnitTestCase{
	private IUserDao userDao;
	
	@Before
	public void setUp() throws DataSetException, IOException, SQLException {
		userDao = new UserDao();
		bakcupOneTable("t_user");
	}
	
	@Test
	public void testLoad() throws IOException, DatabaseUnitException, SQLException {
		IDataSet ds = createDateSet("t_user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
		User tu = userDao.loadByUsername("admin");
		EntitiesHelper.assertUser(tu);
	}
	
	@Test
	public void testAdd() throws IOException, DatabaseUnitException, SQLException {
		IDataSet ds = createDateSet("t_user");
		DatabaseOperation.TRUNCATE_TABLE.execute(dbunitCon, ds);
		User user = new User("admin","123","管理员");
		userDao.add(user);
		Assert.assertTrue(user.getId()>0);
		
		User tu = userDao.loadByUsername("admin");
		EntitiesHelper.assertUser(tu,user);
	}
	
	@After
	public void tearDown() throws DatabaseUnitException, SQLException, IOException {
		resumeTable();
	}
}

package com.ssm.pt.service.spring;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.pt.dao.IUserDao;
import com.ssm.pt.model.User;
import com.ssm.pt.service.IUserService;

/**

 * @author asus

 *

 */
@Service("userService")
public class UserServiceImp implements IUserService {
	@Resource
	private IUserDao userDao;
	
	public User getUserById(int userId) {
		
		return this.userDao.selectByPrimaryKey(userId);
	}

}
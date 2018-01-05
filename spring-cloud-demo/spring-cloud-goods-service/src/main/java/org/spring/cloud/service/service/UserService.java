package org.spring.cloud.service.service;

import java.util.List;

import org.spring.cloud.service.model.rxshopPo.Users;
import org.spring.cloud.service.model.stockPo.User;


public interface UserService {
	public List<User> getUser();
	public List<Users> getUsers();
}

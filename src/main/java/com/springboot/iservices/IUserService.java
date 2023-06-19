package com.springboot.iservices;

import com.springboot.models.User;

public interface IUserService {
	void initUser();
	void register(User user);

}

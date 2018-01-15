package com.tuozixuan.springbootmybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuozixuan.springbootmybatis.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/updateName")
	public String updateName() {
		userService.updateName();
		return "updateName";
	}
}

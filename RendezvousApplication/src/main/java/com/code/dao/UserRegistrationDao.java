package com.code.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.code.model.User;

@Component
public interface UserRegistrationDao {
	public int saveUserRegistration(User user);
	public User findByEmail(String email);
//	public User findEmail(String email);
	public List<User> listofUsers();
	public User findByUserId(int userId);
	public void updateUserRegistration(User user);
	public void deleteUserRegistration(int userId);
	public boolean checkEmail(String email);
	public void savePassword(String email, String password);
}

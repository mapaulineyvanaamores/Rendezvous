package com.code.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.code.model.User;

@Repository
public class UserRegistrationDaoImpl implements UserRegistrationDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int saveUserRegistration(User user) {
		String sql = "INSERT INTO user (email,fullname,password) VALUES('" + user.getEmail() 
				+ "' , '"+ user.getFullName() 
				+ "' , '" + user.getPassword() + "')";
		return jdbcTemplate.update(sql);
	}

//	@Override
//	public User findEmail(String email) {
//		String sql="SELECT fullname, password, email FROM user WHERE email = ?";
//		User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),email);
//		return user;
//	}
	
	@Override
	public User findByEmail(String email) {
		String sql="SELECT * FROM user WHERE email ='"+ email + "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<User>(){
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					User user = new User();
					user.setUserId(rs.getInt("userId"));
					user.setFullName(rs.getString("fullname"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					return user;
				}
				return null;
			}
		});	
	}

	@Override
	public List<User> listofUsers() {
		String sql="SELECT * FROM user";
		List<User> listofUsers = jdbcTemplate.query(sql, new RowMapper<User> () {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setFullName(rs.getString("fullname"));
				user.setEmail(rs.getString("email"));
				return user;
			}
		});
		return listofUsers;
	}

	@Override
	public User findByUserId(int userId) {
		String sql="SELECT * FROM user WHERE userId='" + userId + "'";
		return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class));
	}

	@Override
	public void updateUserRegistration(User user) {
		String sql="UPDATE user SET fullname=?, email=? WHERE userId=?";
		jdbcTemplate.update(sql,user.getFullName(), user.getEmail(), user.getUserId());
	}

	@Override
	public void deleteUserRegistration(int userId) {
//		String sql="DELETE FROM user WHERE userId='" + userId + "'";
//		jdbcTemplate.update(sql);
		String sql="DELETE FROM user WHERE userId=?";
		jdbcTemplate.update(sql, userId);
	}

	@Override
	public boolean checkEmail(String email) {
		return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT 1 FROM user WHERE email=?)",Boolean.class,email);
	}

	@Override
	public void savePassword(String email, String password) {
		String sql="UPDATE user SET password=? WHERE email=?";
		jdbcTemplate.update(sql,password,email);
	}

}
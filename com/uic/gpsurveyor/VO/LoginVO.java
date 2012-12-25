/**
 * 
 */
package com.uic.gpsurveyor.VO;

import java.sql.Timestamp;

/**
 * @author rajath
 *
 */
public class LoginVO {

	private String username;
	private String password;
	private String usertype;
	private Timestamp last_login;

	public LoginVO()
	{
		username = null;
		password = null;
		usertype = null;
		last_login = null;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the usertype
	 */
	public String getUsertype() {
		return usertype;
	}
	/**
	 * @param usertype the usertype to set
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	/**
	 * @return the last_login
	 */
	public Timestamp getLast_login() {
		return last_login;
	}
	/**
	 * @param last_login the last_login to set
	 */
	public void setLast_login(Timestamp last_login) {
		this.last_login = last_login;
	}
}

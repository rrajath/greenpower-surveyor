/**
 * 
 */
package com.uic.gpsurveyor.VO;

import java.sql.Timestamp;

/**
 * @author rajath
 *
 */
public class UserInfoVO {

	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String phone;
	private String userType;
	private String managerId;
	private String free_field_1;
	private String free_field_2;
	private String free_field_3;
	private String del_flg;
	private String r_cre_id;
	private Timestamp r_cre_time;
	private String r_mod_id;
	private Timestamp r_mod_time;

	/**
	 * Constructor
	 */
	public UserInfoVO()
	{
		firstName = "";
		lastName = "";
		email = "";
		username = "";
		password = "";
		phone = "";
		userType = "";
		managerId = "";
		free_field_1 = "";
		free_field_2 = "";
		free_field_3 = "";
		del_flg = "";
		r_cre_id = "";
		r_cre_time = null;
		r_mod_id = "";
		r_mod_time = null;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the managerId
	 */
	public String getManagerId() {
		return managerId;
	}
	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	/**
	 * @return the free_field_1
	 */
	public String getFree_field_1() {
		return free_field_1;
	}
	/**
	 * @param free_field_1 the free_field_1 to set
	 */
	public void setFree_field_1(String free_field_1) {
		this.free_field_1 = free_field_1;
	}
	/**
	 * @return the free_field_2
	 */
	public String getFree_field_2() {
		return free_field_2;
	}
	/**
	 * @param free_field_2 the free_field_2 to set
	 */
	public void setFree_field_2(String free_field_2) {
		this.free_field_2 = free_field_2;
	}
	/**
	 * @return the free_field_3
	 */
	public String getFree_field_3() {
		return free_field_3;
	}
	/**
	 * @param free_field_3 the free_field_3 to set
	 */
	public void setFree_field_3(String free_field_3) {
		this.free_field_3 = free_field_3;
	}
	/**
	 * @return the del_flg
	 */
	public String getDel_flg() {
		return del_flg;
	}
	/**
	 * @param del_flg the del_flg to set
	 */
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	/**
	 * @return the r_cre_id
	 */
	public String getR_cre_id() {
		return r_cre_id;
	}
	/**
	 * @param r_cre_id the r_cre_id to set
	 */
	public void setR_cre_id(String r_cre_id) {
		this.r_cre_id = r_cre_id;
	}
	/**
	 * @return the r_mod_id
	 */
	public String getR_mod_id() {
		return r_mod_id;
	}
	/**
	 * @param r_mod_id the r_mod_id to set
	 */
	public void setR_mod_id(String r_mod_id) {
		this.r_mod_id = r_mod_id;
	}
	/**
	 * @return the r_cre_time
	 */
	public Timestamp getR_cre_time() {
		return r_cre_time;
	}
	/**
	 * @param r_cre_time the r_cre_time to set
	 */
	public void setR_cre_time(Timestamp r_cre_time) {
		this.r_cre_time = r_cre_time;
	}
	/**
	 * @return the r_mod_time
	 */
	public Timestamp getR_mod_time() {
		return r_mod_time;
	}
	/**
	 * @param r_mod_time the r_mod_time to set
	 */
	public void setR_mod_time(Timestamp r_mod_time) {
		this.r_mod_time = r_mod_time;
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
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
}

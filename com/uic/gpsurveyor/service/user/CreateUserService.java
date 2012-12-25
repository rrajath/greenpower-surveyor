/**
 * 
 */
package com.uic.gpsurveyor.service.user;

import java.sql.Timestamp;
import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.LoginDAO;
import com.uic.gpsurveyor.DAO.UserInfoDAO;
import com.uic.gpsurveyor.VO.LoginVO;
import com.uic.gpsurveyor.VO.UserInfoVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.util.Scratchpad;

/**
 * @author rajath
 *
 */
public class CreateUserService {

	String firstName;
	String lastName;
	String username;
	String password;
	String confirmPassword;

	public void execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();

		// Perform validations
		/**
		 * TODO: validate all inputs
		 */

		UserInfoVO objUserInfoVO = new UserInfoVO();

		// Set the UserInfo object with the inputs from the Create User form
		if(ht.get("UserInfoVO")!=null)
			objUserInfoVO = (UserInfoVO)ht.get("UserInfoVO");

		String userType = objUserInfoVO.getUserType();
		if(userType.equals("Manager"))
			userType = "M";
		else
			userType = "S";
		objUserInfoVO.setUserType(userType);
		objUserInfoVO.setManagerId(ht.get("managerId").toString());

		// Inserting a record into login_table once a user is created.
		UserInfoDAO objUserInfoDAO = new UserInfoDAO();
		objUserInfoDAO.create(objUserInfoVO, conn);

		Scratchpad.ht.put("notification", "New User Created");

		LoginVO objLoginVO = new LoginVO();
		objLoginVO.setUsername(objUserInfoVO.getUsername());
		objLoginVO.setPassword(objUserInfoVO.getPassword());
		objLoginVO.setUsertype(objUserInfoVO.getUserType());
		objLoginVO.setLast_login(new Timestamp(new java.util.Date().getTime()));

		LoginDAO objLoginDAO = new LoginDAO();
		objLoginDAO.create(objLoginVO, conn);

		Scratchpad.ht.put("UserInfo", objUserInfoVO);
	}
}

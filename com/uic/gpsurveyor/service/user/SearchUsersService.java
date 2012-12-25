/**
 * 
 */
package com.uic.gpsurveyor.service.user;

import java.util.ArrayList;
import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.UserInfoDAO;
import com.uic.gpsurveyor.VO.UserInfoVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.validator.GPValidator;

/**
 * @author rajath
 *
 */
public class SearchUsersService {

	public static ArrayList execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();
		ArrayList alUserList;

		try {
			UserInfoVO objUserInfoVO = new UserInfoVO();
			if(ht.get("UserInfoVO")!=null)
			{
				objUserInfoVO = (UserInfoVO)ht.get("UserInfoVO");
			}

			// Performing validations on all fields
			validateFields(objUserInfoVO);

			UserInfoDAO objUserInfoDAO = new UserInfoDAO();
			alUserList = objUserInfoDAO.search(objUserInfoVO, conn);
		} catch (Exception e) {
			throw e;
		}

		// return this array list to the calling method and then display the list
		return alUserList;
	}

	public static void validateFields(UserInfoVO objUserInfoVO) throws Exception
	{
		// Performing string validations
		GPValidator.isAlpha("Username", objUserInfoVO.getUsername());
		GPValidator.isAlpha("First Name", objUserInfoVO.getFirstName());
		GPValidator.isAlpha("Last Name", objUserInfoVO.getLastName());
		GPValidator.isAlpha("Manager ID", objUserInfoVO.getManagerId());
	}
}

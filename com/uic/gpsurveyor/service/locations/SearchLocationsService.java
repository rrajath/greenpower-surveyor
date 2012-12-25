/**
 * 
 */
package com.uic.gpsurveyor.service.locations;

import java.util.ArrayList;
import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.LocationsDAO;
import com.uic.gpsurveyor.VO.LocationsVO;
import com.uic.gpsurveyor.connection.Connection;

/**
 * @author rajath
 *
 */
public class SearchLocationsService {

	public static ArrayList execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();
		ArrayList alLocationList;

		try {
			LocationsVO objLocationsVO = new LocationsVO();
			if(ht.get("LocationsVO")!=null)
			{
				objLocationsVO = (LocationsVO)ht.get("LocationsVO");
			}

			// Performing validations on all fields
			//			validateFields(objLocationsVO);

			LocationsDAO objLocationsDAO = new LocationsDAO();
			alLocationList = objLocationsDAO.search(objLocationsVO, conn);
		} catch (Exception e) {
			throw e;
		}

		// return this array list to the calling method and then display the list
		return alLocationList;
	}

	public static void validateFields(LocationsVO objUserInfoVO) throws Exception
	{
		// Performing string validations
		/*		GPValidator.isAlpha("Username", objUserInfoVO.getUsername());
		GPValidator.isAlpha("First Name", objUserInfoVO.getFirstName());
		GPValidator.isAlpha("Last Name", objUserInfoVO.getLastName());
		GPValidator.isAlpha("Manager ID", objUserInfoVO.getManagerId());*/
	}
}

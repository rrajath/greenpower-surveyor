/**
 * 
 */
package com.uic.gpsurveyor.service.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.LocationsDAO;
import com.uic.gpsurveyor.VO.LocationsVO;
import com.uic.gpsurveyor.connection.Connection;

/**
 * @author rajath
 *
 */
public class GenerateInitialListService {

	public static ArrayList execute(Hashtable ht) throws ClassNotFoundException, SQLException
	{
		java.sql.Connection conn = new Connection().getConnection();

		LocationsVO objLocationsVO = new LocationsVO();

		LocationsDAO objLocationsDAO = new LocationsDAO();

		ArrayList alLocationList = new ArrayList();
		alLocationList = objLocationsDAO.search(objLocationsVO, conn);

		return alLocationList;
	}
}

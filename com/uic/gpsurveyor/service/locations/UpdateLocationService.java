/**
 * 
 */
package com.uic.gpsurveyor.service.locations;

import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.LocationsDAO;
import com.uic.gpsurveyor.VO.LocationsVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.util.Scratchpad;
import com.uic.gpsurveyor.validator.GPValidator;

/**
 * @author rajath
 *
 */
public class UpdateLocationService {

	public static void execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();

		try {

			LocationsVO objLocationsVO = new LocationsVO();

			if(ht.get("LocationsVO")!=null)
			{
				objLocationsVO = (LocationsVO)ht.get("LocationsVO");
			}

			// Performing all validations
			validateFields(objLocationsVO);

			LocationsDAO objLocationsDAO = new LocationsDAO();
			objLocationsDAO.update(objLocationsVO, conn);

			Scratchpad.ht.put("notification", "Location Updated");
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public static void validateFields(LocationsVO objLocationsVO) throws Exception
	{
		// Performing mandatory field validations
		GPValidator.mandatoryValidator("City Code", String.valueOf(objLocationsVO.getCityCode()));
		GPValidator.mandatoryValidator("City Name", objLocationsVO.getCityName());
		GPValidator.mandatoryValidator("County", objLocationsVO.getCounty());
		GPValidator.mandatoryValidator("State", objLocationsVO.getState());

		// Performing string validations
		GPValidator.isAlpha("City Code", objLocationsVO.getCityCode());
		GPValidator.isAlpha("City Name", objLocationsVO.getCityName());
		GPValidator.isAlpha("County", objLocationsVO.getCounty());
		GPValidator.isAlpha("State", objLocationsVO.getState());
	}
}

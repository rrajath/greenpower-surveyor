/**
 * 
 */
package com.uic.gpsurveyor.service.solar;

import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.SolarPowerDAO;
import com.uic.gpsurveyor.VO.SolarPowerVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.util.Scratchpad;
import com.uic.gpsurveyor.validator.GPValidator;

/**
 * @author rajath
 *
 */
public class UpdateSolarDataService {

	public static void execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();

		try {

			SolarPowerVO objSolarPowerVO = new SolarPowerVO();

			if(ht.get("SolarPowerVO")!=null)
			{
				objSolarPowerVO = (SolarPowerVO)ht.get("SolarPowerVO");
			}

			// Performing all validations
			//			validateFields(objSolarPowerVO);

			SolarPowerDAO objSolarPowerDAO = new SolarPowerDAO();
			objSolarPowerDAO.update(objSolarPowerVO, conn);

			Scratchpad.ht.put("notification", "Solar Data Updated");
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public static void validateFields(SolarPowerVO objSolarPowerVO) throws Exception
	{
		// Performing mandatory field validations
		GPValidator.mandatoryValidator("Average Temperature", String.valueOf(objSolarPowerVO.getAvgTemp()));
		GPValidator.mandatoryValidator("Highest Temperature", String.valueOf(objSolarPowerVO.getHighestTemp()));
		GPValidator.mandatoryValidator("Lowest Temperature", String.valueOf(objSolarPowerVO.getLowestTemp()));
		GPValidator.mandatoryValidator("Cell Count", String.valueOf(objSolarPowerVO.getCellCount()));
		GPValidator.mandatoryValidator("Area", String.valueOf(objSolarPowerVO.getArea()));
		GPValidator.mandatoryValidator("City", String.valueOf(objSolarPowerVO.getCity()));
		GPValidator.mandatoryValidator("County", String.valueOf(objSolarPowerVO.getCounty()));
		GPValidator.mandatoryValidator("State", String.valueOf(objSolarPowerVO.getState()));

		// Performing numeric validations
		GPValidator.isNumber("Average Temperature", String.valueOf(objSolarPowerVO.getAvgTemp()));
		GPValidator.isNumber("Highest Temperature", String.valueOf(objSolarPowerVO.getHighestTemp()));
		GPValidator.isNumber("Lowest Temperature", String.valueOf(objSolarPowerVO.getLowestTemp()));
		GPValidator.isNumber("Cell Count", String.valueOf(objSolarPowerVO.getCellCount()));
		GPValidator.isNumber("Area", String.valueOf(objSolarPowerVO.getArea()));

		// Performing string validations
		GPValidator.isAlpha("City", objSolarPowerVO.getCity());
		GPValidator.isAlpha("County", objSolarPowerVO.getCounty());
		GPValidator.isAlpha("State", objSolarPowerVO.getState());
	}
}

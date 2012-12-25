/**
 * 
 */
package com.uic.gpsurveyor.service.wind;

import java.util.Hashtable;

import com.uic.gpsurveyor.VO.CostMaintVO;
import com.uic.gpsurveyor.VO.WindPowerVO;
import com.uic.gpsurveyor.util.Scratchpad;

/**
 * @author rajath
 *
 */
public class WindPowerService {

	/**
	 * This method is used to calculate the wind power given the parameters:
	 * 		- Area
	 * 		- Air Density
	 * 		- Wind Speed
	 * @param objWindPowerVO
	 * @param conn
	 * @return Wind Power computed
	 * @throws Exception
	 */
	public float computeWindPower(Hashtable ht) throws Exception
	{
		/*
		 * Formula for computing wind power: 0.5*Area*air_pressure*(windspeed^3)
		 * where,
		 * 		Area = area of the cross section in square metres
		 * 		air_pressure in kg per cubic metre
		 * 		Power obtained will be in terms of MegaWatts
		 */
		float fPower = 0.0f;

		float area = 0.0f;
		float sweptArea = 0.0f;
		float rotorLength = 0.0f;

		if(ht.get("WindPowerVO") == null)
			throw new Exception("Wind Data missing");
		if(ht.get("CostMaintVO") == null)
			throw new Exception("Misc Data Missing");

		WindPowerVO objWindPowerVO = new WindPowerVO();
		objWindPowerVO = (WindPowerVO)ht.get("WindPowerVO");

		CostMaintVO objCostMaintVO = new CostMaintVO();
		objCostMaintVO = (CostMaintVO)ht.get("CostMaintVO");

		rotorLength = objCostMaintVO.getRotorLength();
		area = objWindPowerVO.getArea();
		sweptArea = 22*rotorLength*rotorLength/7;

		/**
		 * TODO When a manager generates a wind survey report, it gives the list of locations with the corresponding power generated.
		 * This has to be done for each manufacturer. So this will run in a loop containing all the manufacturers to get the rotor length.
		 * With each rotor length, we calculate the swept area and also the distance between two rotors.
		 */
		// Distance between 2 windmills must be 5 times the rotor diameter.
		float windmillSeparation = rotorLength*2*5;
		float airDensity = objWindPowerVO.getAirDensity();
		float windSpeed = objWindPowerVO.getWindSpeed();

		// Number of windmills that can be installed
		int noOfWindmills = 0;

		float tmp = (float) (Math.sqrt(area)-1);
		tmp = tmp/windmillSeparation;
		tmp = tmp * tmp;

		noOfWindmills = (int)Math.ceil(tmp);

		fPower = (float)0.5*sweptArea*airDensity*windSpeed*windSpeed*windSpeed;

		float fTotalPower = fPower*noOfWindmills;

		Scratchpad.ht.put("TotalWindPower", fTotalPower);
		Scratchpad.ht.put("NoOfWindmills", noOfWindmills);

		return fTotalPower;
	}

	public float computeWindCost(Hashtable ht)
	{
		float fCost = 0.0f;

		CostMaintVO objCostMaintVO = new CostMaintVO();
		objCostMaintVO = (CostMaintVO)ht.get("CostMaintVO");

		int noOfWindmills = (int)Scratchpad.ht.get("NoOfWindmills");

		fCost = noOfWindmills*objCostMaintVO.getCost();

		Scratchpad.ht.put("TotalCost", fCost);

		return fCost;
	}

	public float computeWindRanking(Hashtable ht) throws Exception {
		/*
		 * Formula for computing wind ranking: totalPower/totalCost
		 */

		float fTotalPower;
		float fTotalCost;
		try
		{
			fTotalPower = (float)ht.get("TotalPower");
			fTotalCost = (float)ht.get("TotalCost");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception("FATAL ERROR: data missing. Check log for details");
		}

		float fRank;
		try
		{
			fRank = fTotalPower/fTotalCost;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("FATAL ERROR while computing rank. Check log for details.");
		}
		return fRank;
	}
}
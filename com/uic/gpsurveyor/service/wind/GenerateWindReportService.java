/**
 * 
 */
package com.uic.gpsurveyor.service.wind;

import java.util.ArrayList;
import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.CostMaintDAO;
import com.uic.gpsurveyor.DAO.WindPowerDAO;
import com.uic.gpsurveyor.VO.CostMaintVO;
import com.uic.gpsurveyor.VO.WindPowerVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.util.Scratchpad;

/**
 * @author rajath
 *
 */
public class GenerateWindReportService {

	public static void execute() throws Exception
	{
		Hashtable ht = new Hashtable();

		java.sql.Connection conn = new Connection().getConnection();

		WindPowerVO objWindPowerVO = new WindPowerVO();
		WindPowerDAO objWindPowerDAO = new WindPowerDAO();
		CostMaintVO objCostMaintVO = new CostMaintVO();
		CostMaintDAO objCostMaintDAO = new CostMaintDAO();

		ArrayList alWindPowerList = new ArrayList();
		alWindPowerList = objWindPowerDAO.search(objWindPowerVO, conn);

		objCostMaintVO.setType("wind");
		ArrayList alCostMaintList = new ArrayList();
		alCostMaintList = objCostMaintDAO.search(objCostMaintVO, conn);

		for(int i=0;i<alWindPowerList.size();i++)
		{
			objWindPowerVO = (WindPowerVO)alWindPowerList.get(i);
			for(int j=0;j<alCostMaintList.size();j++)
			{
				objCostMaintVO = (CostMaintVO)alCostMaintList.get(j);

				WindPowerService objWindPowerService = new WindPowerService();

				ht.put("WindPowerVO", objWindPowerVO);
				ht.put("CostMaintVO", objCostMaintVO);

				float fTotalPower = 0.0f;

				// Total Power Generated for this model of windmill
				fTotalPower = objWindPowerService.computeWindPower(ht);

				float fTotalCost = 0.0f;
				int NoOfWindmills = (int)Scratchpad.ht.get("NoOfWindmills");

				// Total Cost required to install this model of windmill
				fTotalCost = NoOfWindmills*objCostMaintVO.getCost();
			}
		}


	}
}

/**
 * 
 */
package com.uic.gpsurveyor.service.inventory;

import java.util.ArrayList;
import java.util.Hashtable;

import com.uic.gpsurveyor.DAO.CostMaintDAO;
import com.uic.gpsurveyor.VO.CostMaintVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.validator.GPValidator;

/**
 * @author rajath
 *
 */
public class SearchInventoryService {

	public static ArrayList execute(Hashtable ht) throws Exception
	{
		java.sql.Connection conn = new Connection().getConnection();
		ArrayList alCostMaint;

		try {
			CostMaintVO objCostMaintVO = new CostMaintVO();
			if(ht.get("CostMaintVO")!=null)
			{
				objCostMaintVO = (CostMaintVO)ht.get("CostMaintVO");
			}

			// Performing validations on all fields
			validateFields(objCostMaintVO);

			CostMaintDAO objCostMaintDAO = new CostMaintDAO();
			alCostMaint = objCostMaintDAO.search(objCostMaintVO, conn);
		} catch (Exception e) {
			throw e;
		}

		// return this array list to the calling method and then display the list
		return alCostMaint;
	}

	public static void validateFields(CostMaintVO objCostMaintVO) throws Exception
	{
		// Performing string validations
		GPValidator.isAlpha("Type", objCostMaintVO.getType());
		GPValidator.isAlpha("Model", objCostMaintVO.getModel());
		GPValidator.isAlpha("Manufacturer", objCostMaintVO.getManufacturer());
	}
}

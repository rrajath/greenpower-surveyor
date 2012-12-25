/**
 * 
 */
package com.uic.gpsurveyor.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.uic.gpsurveyor.VO.CostMaintVO;
import com.uic.gpsurveyor.VO.LoginVO;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.GenerateSequenceNumber;

/**
 * @author rajath
 *
 */
public class CostMaintDAO {

	/**
	 * 
	 */
	public CostMaintDAO()
	{

	}

	/**
	 * This method creates a record in cost_maintenance table
	 * @param objCostMaintVO
	 * @param conn
	 * @throws SQLException
	 */
	public void create(CostMaintVO objCostMaintVO, java.sql.Connection conn) throws SQLException
	{
		try {
			String queryString = "INSERT INTO cost_maintenance VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(queryString);

			// Generating a unique ID
			int iNextSeqNo = GenerateSequenceNumber.getNextSequenceNumber("cm_seq", conn);

			int i = 1;

			ps.setInt(i++, iNextSeqNo);
			ps.setString(i++, objCostMaintVO.getType());
			ps.setString(i++, objCostMaintVO.getManufacturer());
			ps.setString(i++, objCostMaintVO.getModel());
			ps.setFloat(i++, objCostMaintVO.getCost());
			ps.setString(i++, objCostMaintVO.getModelName());
			ps.setFloat(i++, objCostMaintVO.getService());
			ps.setFloat(i++, objCostMaintVO.getRotorLength());

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException(GPConstants.INSERT_ERROR);
		}
	}

	/**
	 * This method is used to search for record(s) in cost_maintenance table
	 * @param costMaintObj
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public ArrayList search(CostMaintVO costMaintObj, java.sql.Connection conn) throws SQLException
	{
		ArrayList alCostMaintList = new ArrayList();

		LoginVO loginInfo = new LoginVO();

		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT cmId, type, manufacturer, model, cost, name, service, rotor_length FROM cost_maintenance WHERE ");
		queryString.append(" 1 = 1 ");

		if(costMaintObj.getCmId() != 0)
		{
			queryString.append(" AND cmId = ? ");
		}

		if(!costMaintObj.getType().equals(""))
		{
			queryString.append(" AND type = ? ");
		}

		if(!costMaintObj.getManufacturer().equals(""))
		{
			queryString.append(" AND manufacturer = ? ");
		}

		if(!costMaintObj.getModel().equals(""))
		{
			queryString.append(" AND model = ? ");
		}

		if(!costMaintObj.getModelName().equals(""))
		{
			queryString.append(" AND name = ? ");
		}

		if(costMaintObj.getCost() != 0.0f)
		{
			queryString.append(" AND cost = ? ");
		}

		if(costMaintObj.getService() != 0.0f)
		{
			queryString.append(" AND service = ? ");
		}

		if(costMaintObj.getRotorLength() != 0.0f)
		{
			queryString.append(" AND rotor_length = ? ");
		}

		PreparedStatement ps = conn.prepareStatement(queryString.toString());

		int i = 1;

		if(costMaintObj.getCmId() != 0)
		{
			ps.setInt(i++, costMaintObj.getCmId());
		}

		if(!costMaintObj.getType().equals(""))
		{
			ps.setString(i++, costMaintObj.getType());
		}

		if(!costMaintObj.getManufacturer().equals(""))
		{
			ps.setString(i++, costMaintObj.getManufacturer());
		}

		if(!costMaintObj.getModel().equals(""))
		{
			ps.setString(i++, costMaintObj.getModel());
		}

		if(!costMaintObj.getModelName().equals(""))
		{
			ps.setString(i++, costMaintObj.getModelName());
		}

		if(costMaintObj.getCost() != 0.0f)
		{
			ps.setFloat(i++, costMaintObj.getCost());
		}

		if(costMaintObj.getService() != 0.0f)
		{
			ps.setFloat(i++, costMaintObj.getService());
		}

		if(costMaintObj.getRotorLength() != 0.0f)
		{
			ps.setFloat(i++, costMaintObj.getRotorLength());
		}

		ResultSet rs = ps.executeQuery();

		if(!rs.next())
		{
			throw new SQLException(GPConstants.NO_RECORDS_FOUND);
		}
		else
		{
			do
			{
				CostMaintVO objCostMaintVO = new CostMaintVO();

				objCostMaintVO.setCmId(rs.getInt(1));
				objCostMaintVO.setType(rs.getString(2));
				objCostMaintVO.setManufacturer(rs.getString(3));
				objCostMaintVO.setModel(rs.getString(4));
				objCostMaintVO.setCost(rs.getFloat(5));
				objCostMaintVO.setModelName(rs.getString(6));
				objCostMaintVO.setService(rs.getFloat(7));
				objCostMaintVO.setRotorLength(rs.getFloat(8));

				alCostMaintList.add(objCostMaintVO);
			}while(rs.next());
		}
		return alCostMaintList;
	}

	/**
	 * This method is used to update a record in cost_maintenance table
	 * @param objCostMaintVO
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public int update(CostMaintVO objCostMaintVO, java.sql.Connection conn) throws SQLException
	{
		/*
		 * NOTE: IN THE GUI, MAKE CMID A LABEL AND NOT AN EDITABLE TEXT FIELD
		 */

		int rowsAffected = 0;
		//		cmId, type, manufacturer, model, cost, name, service
		String queryString = new String("UPDATE cost_maintenance SET " +
				" type = ?, " +
				" manufacturer = ?, " +
				" model = ?, " +
				" cost = ?, " +
				" name = ?, " +
				" service = ?, " +
				" rotor_length = ? " +
				" WHERE cmId = ?");

		try {
			PreparedStatement ps = conn.prepareStatement(queryString);

			int i = 1;

			ps.setString(i++, objCostMaintVO.getType());
			ps.setString(i++, objCostMaintVO.getManufacturer());
			ps.setString(i++, objCostMaintVO.getModel());
			ps.setFloat(i++, objCostMaintVO.getCost());
			ps.setString(i++, objCostMaintVO.getModelName());
			ps.setFloat(i++, objCostMaintVO.getService());
			ps.setFloat(i++, objCostMaintVO.getRotorLength());
			ps.setInt(i++, objCostMaintVO.getCmId());

			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(GPConstants.UPDATE_ERROR);
		}

		return rowsAffected;
	}
}

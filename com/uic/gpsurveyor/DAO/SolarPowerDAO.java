/**
 * 
 */
package com.uic.gpsurveyor.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.uic.gpsurveyor.VO.SolarPowerVO;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.GenerateSequenceNumber;

/**
 * @author rajath
 *
 */
public class SolarPowerDAO {

	public SolarPowerDAO()
	{

	}

	/**
	 * This method is used to create a record in tidal_power_table
	 * @param objSolarPowerVO
	 * @param conn
	 * @throws SQLException
	 */
	public void create(SolarPowerVO objSolarPowerVO, java.sql.Connection conn) throws SQLException
	{
		try {
			String queryString = "INSERT INTO solar_power_table VALUES (" +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, " +
					"?, ?)";

			PreparedStatement ps = conn.prepareStatement(queryString);

			int iNextSeqNo = GenerateSequenceNumber.getNextSequenceNumber("sp_seq", conn);

			int i = 1;

			ps.setInt(i++, iNextSeqNo);
			ps.setFloat(i++, objSolarPowerVO.getAvgTemp());
			ps.setFloat(i++, objSolarPowerVO.getHighestTemp());
			ps.setFloat(i++, objSolarPowerVO.getLowestTemp());
			ps.setInt(i++, objSolarPowerVO.getCellCount());
			ps.setFloat(i++, objSolarPowerVO.getArea());
			ps.setString(i++, objSolarPowerVO.getCity());
			ps.setString(i++, objSolarPowerVO.getCounty());
			ps.setString(i++, objSolarPowerVO.getState());
			ps.setString(i++, objSolarPowerVO.getFree_field_1());
			ps.setString(i++, objSolarPowerVO.getFree_field_2());
			ps.setString(i++, objSolarPowerVO.getFree_field_3());
			ps.setString(i++, GPConstants.DEL_FLG_NO);
			ps.setString(i++, objSolarPowerVO.getR_cre_id());
			ps.setTimestamp(i++, new Timestamp(new java.util.Date().getTime()));
			ps.setString(i++, objSolarPowerVO.getR_mod_id());
			ps.setTimestamp(i++, new Timestamp(new java.util.Date().getTime()));

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException(GPConstants.INSERT_ERROR);
		}
	}

	/**
	 * This method is used to search for record(s) in solar_power_table
	 * @param solarPowerObj
	 * @param conn
	 * @return ArrayList containing SolarPowerVO objects
	 * @throws SQLException
	 */
	public ArrayList search(SolarPowerVO solarPowerObj, java.sql.Connection conn) throws SQLException
	{
		ArrayList alSolarPowerList = new ArrayList();

		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT " +
				"spId, avg_temp, highest_temp, lowest_temp, cell_count, " +
				"area, city, county, state, free_field_1, " +
				"free_field_2, free_field_3, del_flg, r_cre_id, r_cre_time, " +
				"r_mod_id, r_mod_time " +
				"FROM solar_power_table WHERE ");
		queryString.append(" 1 = 1 ");

		if(solarPowerObj.getSpId() != 0)
		{
			queryString.append(" AND wpId = ? ");
		}

		if(solarPowerObj.getAvgTemp() != 0.0f)
		{
			queryString.append(" AND length = ? ");
		}

		if(solarPowerObj.getHighestTemp() != 0.0f)
		{
			queryString.append(" AND density = ? ");
		}

		if(solarPowerObj.getLowestTemp() != 0.0f)
		{
			queryString.append(" AND height = ? ");
		}

		if(solarPowerObj.getCellCount() != 0)
		{
			queryString.append(" AND flow_rate = ? ");
		}

		if(solarPowerObj.getArea() != 0.0f)
		{
			queryString.append(" AND area = ? ");
		}

		if(!solarPowerObj.getCity().equals(""))
		{
			queryString.append(" AND city = ? ");
		}

		if(!solarPowerObj.getCounty().equals(""))
		{
			queryString.append(" AND county = ? ");
		}

		if(!solarPowerObj.getState().equals(""))
		{
			queryString.append(" AND state = ? ");
		}

		if(!solarPowerObj.getFree_field_1().equals(""))
		{
			queryString.append(" AND free_field_1 = ? ");
		}

		if(!solarPowerObj.getFree_field_2().equals(""))
		{
			queryString.append(" AND free_field_2 = ? ");
		}

		if(!solarPowerObj.getFree_field_3().equals(""))
		{
			queryString.append(" AND free_field_3 = ? ");
		}

		if(!solarPowerObj.getDel_flg().equals(""))
		{
			queryString.append(" AND del_flg = ? ");
		}

		if(!solarPowerObj.getR_cre_id().equals(""))
		{
			queryString.append(" AND r_cre_id = ? ");
		}

		if(solarPowerObj.getR_cre_time() != null)
		{
			queryString.append(" AND r_cre_time = ? ");
		}

		if(!solarPowerObj.getR_mod_id().equals(""))
		{
			queryString.append(" AND r_mod_id = ? ");
		}

		if(solarPowerObj.getR_mod_time() != null)
		{
			queryString.append(" AND r_mod_time = ? ");
		}

		try
		{
			PreparedStatement ps = conn.prepareStatement(queryString.toString());

			int i = 1;

			if(solarPowerObj.getSpId() != 0)
			{
				ps.setInt(i++, solarPowerObj.getSpId());
			}

			if(solarPowerObj.getAvgTemp() != 0.0f)
			{
				ps.setFloat(i++, solarPowerObj.getAvgTemp());
			}

			if(solarPowerObj.getHighestTemp() != 0.0f)
			{
				ps.setFloat(i++, solarPowerObj.getHighestTemp());
			}

			if(solarPowerObj.getLowestTemp() != 0.0f)
			{
				ps.setFloat(i++, solarPowerObj.getLowestTemp());
			}

			if(solarPowerObj.getCellCount() != 0.0f)
			{
				ps.setFloat(i++, solarPowerObj.getCellCount());
			}

			if(solarPowerObj.getArea() != 0.0f)
			{
				ps.setFloat(i++, solarPowerObj.getArea());
			}

			if(!solarPowerObj.getCity().equals(""))
			{
				ps.setString(i++, solarPowerObj.getCity());
			}

			if(!solarPowerObj.getCounty().equals(""))
			{
				ps.setString(i++, solarPowerObj.getCounty());
			}

			if(!solarPowerObj.getState().equals(""))
			{
				ps.setString(i++, solarPowerObj.getCounty());
			}

			if(!solarPowerObj.getFree_field_1().equals(""))
			{
				ps.setString(i++, solarPowerObj.getFree_field_1());
			}

			if(!solarPowerObj.getFree_field_2().equals(""))
			{
				ps.setString(i++, solarPowerObj.getFree_field_2());
			}

			if(!solarPowerObj.getFree_field_3().equals(""))
			{
				ps.setString(i++, solarPowerObj.getFree_field_3());
			}

			if(!solarPowerObj.getDel_flg().equals(""))
			{
				ps.setString(i++, solarPowerObj.getDel_flg());
			}

			if(!solarPowerObj.getR_cre_id().equals(""))
			{
				ps.setString(i++, solarPowerObj.getR_cre_id());
			}

			if(solarPowerObj.getR_cre_time() != null)
			{
				ps.setTimestamp(i++, solarPowerObj.getR_cre_time());
			}

			if(!solarPowerObj.getR_mod_id().equals(""))
			{
				ps.setString(i++, solarPowerObj.getR_mod_id());
			}

			if(solarPowerObj.getR_mod_time() != null)
			{
				ps.setTimestamp(i++, solarPowerObj.getR_mod_time());
			}

			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				SolarPowerVO objSolarPowerVO = new SolarPowerVO();

				int j = 1;

				objSolarPowerVO.setSpId(rs.getInt(j++));
				objSolarPowerVO.setAvgTemp(rs.getFloat(j++));
				objSolarPowerVO.setHighestTemp(rs.getFloat(j++));
				objSolarPowerVO.setLowestTemp(rs.getFloat(j++));
				objSolarPowerVO.setCellCount(rs.getInt(j++));
				objSolarPowerVO.setArea(rs.getFloat(j++));
				objSolarPowerVO.setCity(rs.getString(j++));
				objSolarPowerVO.setCounty(rs.getString(j++));
				objSolarPowerVO.setState(rs.getString(j++));
				objSolarPowerVO.setFree_field_1(rs.getString(j++));
				objSolarPowerVO.setFree_field_2(rs.getString(j++));
				objSolarPowerVO.setFree_field_3(rs.getString(j++));
				objSolarPowerVO.setDel_flg(rs.getString(j++));
				objSolarPowerVO.setR_cre_id(rs.getString(j++));
				objSolarPowerVO.setR_cre_time(rs.getTimestamp(j++));
				objSolarPowerVO.setR_mod_id(rs.getString(j++));
				objSolarPowerVO.setR_mod_time(rs.getTimestamp(j++));

				alSolarPowerList.add(objSolarPowerVO);
			}
		}
		catch(SQLException e)
		{
			throw new SQLException(GPConstants.NO_RECORDS_FOUND);
		}
		return alSolarPowerList;
	}

	/**
	 * This method updates a record in the database
	 * @param objSolarPowerVO
	 * @param conn
	 * @return number of rows updated
	 * @throws SQLException
	 */
	public int update(SolarPowerVO objSolarPowerVO, java.sql.Connection conn) throws SQLException
	{
		/*
		 * NOTE: IN THE GUI, MAKE CMID A LABEL AND NOT AN EDITABLE TEXT FIELD
		 */

		int rowsAffected = 0;
		//		cmId, type, manufacturer, model, cost, name, service
		String queryString = new String("UPDATE solar_power_table SET " +
				" avg_temp = ?, " +
				" highest_temp = ?, " +
				" lowest_temp = ?, " +
				" cell_count = ?, " +
				" area = ?, " +
				" city = ?, " +
				" county = ?, " +
				" state = ?, " +
				" free_field_1 = ?, " +
				" free_field_2 = ?, " +
				" free_field_3 = ?, " +
				" del_flg = ?, " +
				" r_cre_id = ?, " +
				" r_cre_time = ?, " +
				" r_mod_id = ?, " +
				" r_mod_time = ? " +
				" WHERE spId = ?");

		try {
			PreparedStatement ps = conn.prepareStatement(queryString);

			int i = 1;

			ps.setFloat(i++, objSolarPowerVO.getAvgTemp());
			ps.setFloat(i++, objSolarPowerVO.getHighestTemp());
			ps.setFloat(i++, objSolarPowerVO.getLowestTemp());
			ps.setInt(i++, objSolarPowerVO.getCellCount());
			ps.setFloat(i++, objSolarPowerVO.getArea());
			ps.setString(i++, objSolarPowerVO.getCity());
			ps.setString(i++, objSolarPowerVO.getCounty());
			ps.setString(i++, objSolarPowerVO.getState());
			ps.setString(i++, objSolarPowerVO.getFree_field_1());
			ps.setString(i++, objSolarPowerVO.getFree_field_2());
			ps.setString(i++, objSolarPowerVO.getFree_field_3());
			ps.setString(i++, objSolarPowerVO.getDel_flg());
			ps.setString(i++, objSolarPowerVO.getR_cre_id());
			ps.setTimestamp(i++, objSolarPowerVO.getR_cre_time());
			ps.setString(i++, objSolarPowerVO.getR_mod_id());
			ps.setTimestamp(i++, new Timestamp(new java.util.Date().getTime()));
			ps.setFloat(i++, objSolarPowerVO.getSpId());

			rowsAffected = ps.executeUpdate();

			if(rowsAffected == 0)
			{
				throw new SQLException(GPConstants.UPDATE_ERROR);
			}
		} catch (SQLException e) {
			throw new SQLException(GPConstants.UPDATE_ERROR);
		}

		return rowsAffected;
	}
}

/**
 * 
 */
package com.uic.gpsurveyor.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.uic.gpsurveyor.VO.WindPowerVO;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.GenerateSequenceNumber;

/**
 * @author rajath
 *
 */
public class WindPowerDAO {

	/**
	 * Constructor
	 */
	public WindPowerDAO()
	{

	}

	/**
	 * This method is used to create a record in the database
	 * @param objWindPowerVO
	 * @param conn
	 * @throws SQLException
	 */
	public void create(WindPowerVO objWindPowerVO, java.sql.Connection conn) throws SQLException
	{
		try {
			int iNextSeqNumber = GenerateSequenceNumber.getNextSequenceNumber("wp_seq", conn);

			String queryString = "INSERT INTO wind_power_table VALUES (" +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, " +
					"?)";

			PreparedStatement ps = conn.prepareStatement(queryString);

			ps.setInt(1, iNextSeqNumber);
			ps.setFloat(2, objWindPowerVO.getWindSpeed());
			ps.setFloat(3, objWindPowerVO.getAirDensity());
			ps.setFloat(4, objWindPowerVO.getHumidity());
			ps.setFloat(5, objWindPowerVO.getArea());
			ps.setString(6, objWindPowerVO.getCity());
			ps.setString(7, objWindPowerVO.getCounty());
			ps.setString(8, objWindPowerVO.getState());
			ps.setString(9, objWindPowerVO.getFree_field_1());
			ps.setString(10, objWindPowerVO.getFree_field_2());
			ps.setString(11, objWindPowerVO.getFree_field_3());
			ps.setString(12, GPConstants.DEL_FLG_NO);
			ps.setString(13, objWindPowerVO.getR_cre_id());
			ps.setTimestamp(14, new Timestamp(new java.util.Date().getTime()));
			ps.setString(15, objWindPowerVO.getR_mod_id());
			ps.setTimestamp(16, new Timestamp(new java.util.Date().getTime()));

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(GPConstants.INSERT_ERROR);
		}
	}

	/**
	 * This method is used to search for record(s) in wind_power_table
	 * @param windPowerObj
	 * @param conn
	 * @return ArrayList containing a list of objects of the type WindPowerVO
	 * @throws SQLException
	 */
	public ArrayList search(WindPowerVO windPowerObj, java.sql.Connection conn) throws SQLException
	{
		ArrayList alWindPowerList = new ArrayList();

		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT " +
				"wpId, wind_speed, air_density, humidity, area, " +
				"city, county, state, free_field_1, free_field_2, " +
				"free_field_3, del_flg, r_cre_id, r_cre_time, r_mod_id, " +
				"r_mod_time " +
				"FROM wind_power_table WHERE ");
		queryString.append(" 1 = 1 ");

		if(windPowerObj.getWpId() != 0)
		{
			queryString.append(" AND wpId = ? ");
		}

		if(windPowerObj.getWindSpeed() != 0.0f)
		{
			queryString.append(" AND wind_speed = ? ");
		}

		if(windPowerObj.getAirDensity() != 0.0f)
		{
			queryString.append(" AND air_density = ? ");
		}

		if(windPowerObj.getHumidity() != 0.0f)
		{
			queryString.append(" AND humidity = ? ");
		}

		if(windPowerObj.getArea() != 0.0f)
		{
			queryString.append(" AND area = ? ");
		}

		if(windPowerObj.getCity() != null && !windPowerObj.getCity().equals(""))
		{
			queryString.append(" AND city = ? ");
		}

		if(windPowerObj.getCounty() != null && !windPowerObj.getCounty().equals(""))
		{
			queryString.append(" AND county = ? ");
		}

		if(windPowerObj.getState() != null && !windPowerObj.getState().equals(""))
		{
			queryString.append(" AND state = ? ");
		}

		if(windPowerObj.getFree_field_1() != null && !windPowerObj.getFree_field_1().equals(""))
		{
			queryString.append(" AND free_field_1 = ? ");
		}

		if(windPowerObj.getFree_field_2() != null && !windPowerObj.getFree_field_2().equals(""))
		{
			queryString.append(" AND free_field_2 = ? ");
		}

		if(windPowerObj.getFree_field_3() != null && !windPowerObj.getFree_field_3().equals(""))
		{
			queryString.append(" AND free_field_3 = ? ");
		}

		if(windPowerObj.getDel_flg() != null && !windPowerObj.getDel_flg().equals(""))
		{
			queryString.append(" AND del_flg = ? ");
		}

		if(windPowerObj.getR_cre_id() != null && !windPowerObj.getR_cre_id().equals(""))
		{
			queryString.append(" AND r_cre_id = ? ");
		}

		if(windPowerObj.getR_cre_time() != null)
		{
			queryString.append(" AND r_cre_time = ? ");
		}

		if(windPowerObj.getR_mod_id() != null && !windPowerObj.getR_mod_id().equals(""))
		{
			queryString.append(" AND r_mod_id = ? ");
		}

		if(windPowerObj.getR_mod_time() != null)
		{
			queryString.append(" AND r_mod_time = ? ");
		}

		try
		{
			PreparedStatement ps = conn.prepareStatement(queryString.toString());

			int i = 1;

			if(windPowerObj.getWpId() != 0)
			{
				ps.setInt(i++, windPowerObj.getWpId());
			}

			if(windPowerObj.getWindSpeed() != 0.0f)
			{
				ps.setFloat(i++, windPowerObj.getWindSpeed());
			}

			if(windPowerObj.getAirDensity() != 0.0f)
			{
				ps.setFloat(i++, windPowerObj.getAirDensity());
			}

			if(windPowerObj.getHumidity() != 0.0f)
			{
				ps.setFloat(i++, windPowerObj.getHumidity());
			}

			if(windPowerObj.getArea() != 0.0f)
			{
				ps.setFloat(i++, windPowerObj.getArea());
			}

			if(windPowerObj.getCity() != null && !windPowerObj.getCity().equals(""))
			{
				ps.setString(i++, windPowerObj.getCity());
			}

			if(windPowerObj.getCounty() != null && !windPowerObj.getCounty().equals(""))
			{
				ps.setString(i++, windPowerObj.getCounty());
			}

			if(windPowerObj.getState() != null && !windPowerObj.getState().equals(""))
			{
				ps.setString(i++, windPowerObj.getCounty());
			}

			if(windPowerObj.getFree_field_1() != null && !windPowerObj.getFree_field_1().equals(""))
			{
				ps.setString(i++, windPowerObj.getFree_field_1());
			}

			if(windPowerObj.getFree_field_2() != null && !windPowerObj.getFree_field_2().equals(""))
			{
				ps.setString(i++, windPowerObj.getFree_field_2());
			}

			if(windPowerObj.getFree_field_3() != null && !windPowerObj.getFree_field_3().equals(""))
			{
				ps.setString(i++, windPowerObj.getFree_field_3());
			}

			if(windPowerObj.getDel_flg() != null && !windPowerObj.getDel_flg().equals(""))
			{
				ps.setString(i++, windPowerObj.getDel_flg());
			}

			if(windPowerObj.getR_cre_id() != null && !windPowerObj.getR_cre_id().equals(""))
			{
				ps.setString(i++, windPowerObj.getR_cre_id());
			}

			if(windPowerObj.getR_cre_time() != null)
			{
				ps.setTimestamp(i++, windPowerObj.getR_cre_time());
			}

			if(windPowerObj.getR_mod_id() != null && !windPowerObj.getR_mod_id().equals(""))
			{
				ps.setString(i++, windPowerObj.getR_mod_id());
			}

			if(windPowerObj.getR_mod_time() != null)
			{
				ps.setTimestamp(i++, windPowerObj.getR_mod_time());
			}

			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				WindPowerVO objWindPowerVO = new WindPowerVO();

				objWindPowerVO.setWpId(rs.getInt(1));
				objWindPowerVO.setWindSpeed(rs.getFloat(2));
				objWindPowerVO.setAirDensity(rs.getFloat(3));
				objWindPowerVO.setHumidity(rs.getFloat(4));
				objWindPowerVO.setArea(rs.getFloat(5));
				objWindPowerVO.setCity(rs.getString(6));
				objWindPowerVO.setCounty(rs.getString(7));
				objWindPowerVO.setState(rs.getString(8));
				objWindPowerVO.setFree_field_1(rs.getString(9));
				objWindPowerVO.setFree_field_2(rs.getString(10));
				objWindPowerVO.setFree_field_3(rs.getString(11));
				objWindPowerVO.setDel_flg(rs.getString(12));
				objWindPowerVO.setR_cre_id(rs.getString(13));
				objWindPowerVO.setR_cre_time(rs.getTimestamp(14));
				objWindPowerVO.setR_mod_id(rs.getString(15));
				objWindPowerVO.setR_mod_time(rs.getTimestamp(16));

				alWindPowerList.add(objWindPowerVO);
			}
		}
		catch(SQLException e)
		{
			throw new SQLException(GPConstants.NO_RECORDS_FOUND);
		}
		return alWindPowerList;
	}

	/**
	 * This method is used to update a record in wind_power_table
	 * @param objWindPowerVO
	 * @param conn
	 * @return number of rows updated
	 * @throws SQLException
	 */
	public int update(WindPowerVO objWindPowerVO, java.sql.Connection conn) throws SQLException
	{
		/*
		 * NOTE: IN THE GUI, MAKE CMID A LABEL AND NOT AN EDITABLE TEXT FIELD
		 */

		int rowsAffected = 0;
		//		cmId, type, manufacturer, model, cost, name, service
		String queryString = new String("UPDATE wind_power_table SET " +
				" wind_speed = ?, " +
				" air_density = ?, " +
				" humidity = ?, " +
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
				" WHERE wpId = ?");

		try {
			PreparedStatement ps = conn.prepareStatement(queryString);

			int i = 1;

			ps.setFloat(i++, objWindPowerVO.getWindSpeed());
			ps.setFloat(i++, objWindPowerVO.getAirDensity());
			ps.setFloat(i++, objWindPowerVO.getHumidity());
			ps.setFloat(i++, objWindPowerVO.getArea());
			ps.setString(i++, objWindPowerVO.getCity());
			ps.setString(i++, objWindPowerVO.getCounty());
			ps.setString(i++, objWindPowerVO.getState());
			ps.setString(i++, objWindPowerVO.getFree_field_1());
			ps.setString(i++, objWindPowerVO.getFree_field_2());
			ps.setString(i++, objWindPowerVO.getFree_field_3());
			ps.setString(i++, objWindPowerVO.getDel_flg());
			ps.setString(i++, objWindPowerVO.getR_cre_id());
			ps.setTimestamp(i++, objWindPowerVO.getR_cre_time());
			ps.setString(i++, objWindPowerVO.getR_mod_id());
			ps.setTimestamp(i++, objWindPowerVO.getR_mod_time());
			ps.setInt(i++, objWindPowerVO.getWpId());

			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(GPConstants.UPDATE_ERROR);
		}

		return rowsAffected;
	}
}

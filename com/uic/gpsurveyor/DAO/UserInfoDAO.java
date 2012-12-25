/**
 * 
 */
package com.uic.gpsurveyor.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.uic.gpsurveyor.VO.UserInfoVO;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

/**
 * @author rajath
 *
 */
public class UserInfoDAO {

	/**
	 * Constructor
	 */
	public UserInfoDAO()
	{

	}

	public void create(UserInfoVO objUserInfoVO, java.sql.Connection conn) throws SQLException
	{
		try {
			String queryString = "INSERT INTO user_info VALUES (" +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, " +
					"?) ";

			PreparedStatement ps = conn.prepareStatement(queryString);

			// Generating a unique ID

			int i = 1;
			ps.setString(i++, objUserInfoVO.getFirstName());
			ps.setString(i++, objUserInfoVO.getLastName());
			ps.setString(i++, objUserInfoVO.getEmail());
			ps.setString(i++, objUserInfoVO.getUsername());
			ps.setString(i++, objUserInfoVO.getPassword());
			ps.setString(i++, objUserInfoVO.getPhone());
			ps.setString(i++, objUserInfoVO.getUserType());
			ps.setString(i++, objUserInfoVO.getManagerId());
			ps.setString(i++, objUserInfoVO.getFree_field_1());
			ps.setString(i++, objUserInfoVO.getFree_field_2());
			ps.setString(i++, objUserInfoVO.getFree_field_3());
			ps.setString(i++, GPConstants.DEL_FLG_NO);
			ps.setString(i++, Scratchpad.ht.get("userId").toString());
			ps.setTimestamp(i++, new Timestamp(new java.util.Date().getTime()));
			ps.setString(i++, Scratchpad.ht.get("userId").toString());
			ps.setTimestamp(i++, new Timestamp(new java.util.Date().getTime()));

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException(GPConstants.INSERT_ERROR);
		}
	}

	/**
	 * This method is used to search for record(s) in wind_power_table
	 * @param userInfoObj
	 * @param conn
	 * @return ArrayList containing a list of objects of the type WindPowerVO
	 * @throws SQLException
	 */
	public ArrayList search(UserInfoVO userInfoObj, java.sql.Connection conn) throws SQLException
	{
		ArrayList alUserInfoList = new ArrayList();

		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT " +
				"first_name, last_name, email, username, password, " +
				"phone, usertype, manager_id, free_field_1, free_field_2, " +
				"free_field_3, del_flg, r_cre_id, r_cre_time, r_mod_id, " +
				"r_mod_time FROM user_info WHERE ");
		queryString.append(" 1 = 1 ");

		if(!userInfoObj.getFirstName().equals(""))
		{
			queryString.append(" AND first_name = ? ");
		}

		if(!userInfoObj.getLastName().equals(""))
		{
			queryString.append(" AND last_name = ? ");
		}

		if(!userInfoObj.getEmail().equals(""))
		{
			queryString.append(" AND email = ? ");
		}

		if(!userInfoObj.getUsername().equals(""))
		{
			queryString.append(" AND username = ? ");
		}

		if(!userInfoObj.getPassword().equals(""))
		{
			queryString.append(" AND password = ? ");
		}

		if(!userInfoObj.getPhone().equals(""))
		{
			queryString.append(" AND phone = ? ");
		}

		if(!userInfoObj.getUserType().equals(""))
		{
			queryString.append(" AND usertype = ? ");
		}

		if(!userInfoObj.getManagerId().equals(""))
		{
			queryString.append(" AND managerId = ? ");
		}

		if(!userInfoObj.getFree_field_1().equals(""))
		{
			queryString.append(" AND free_field_1 = ? ");
		}

		if(!userInfoObj.getFree_field_2().equals(""))
		{
			queryString.append(" AND free_field_2 = ? ");
		}

		if(!userInfoObj.getFree_field_3().equals(""))
		{
			queryString.append(" AND free_field_3 = ? ");
		}

		if(!userInfoObj.getDel_flg().equals(""))
		{
			queryString.append(" AND del_flg = ? ");
		}

		if(!userInfoObj.getR_cre_id().equals(""))
		{
			queryString.append(" AND r_cre_id = ? ");
		}

		if(userInfoObj.getR_cre_time() != null)
		{
			queryString.append(" AND r_cre_time = ? ");
		}

		if(!userInfoObj.getR_mod_id().equals(""))
		{
			queryString.append(" AND r_mod_id = ? ");
		}

		if(userInfoObj.getR_mod_time() != null)
		{
			queryString.append(" AND r_mod_time = ? ");
		}

		try
		{
			PreparedStatement ps = conn.prepareStatement(queryString.toString());

			int i = 1;

			if(!userInfoObj.getFirstName().equals(""))
			{
				ps.setString(i++, userInfoObj.getFirstName());
			}

			if(!userInfoObj.getLastName().equals(""))
			{
				ps.setString(i++, userInfoObj.getLastName());
			}

			if(!userInfoObj.getEmail().equals(""))
			{
				ps.setString(i++, userInfoObj.getEmail());
			}

			if(!userInfoObj.getUsername().equals(""))
			{
				ps.setString(i++, userInfoObj.getUsername());
			}

			if(!userInfoObj.getPassword().equals(""))
			{
				ps.setString(i++, userInfoObj.getPassword());
			}

			if(!userInfoObj.getPhone().equals(""))
			{
				ps.setString(i++, userInfoObj.getPhone());
			}

			if(!userInfoObj.getUserType().equals(""))
			{
				ps.setString(i++, userInfoObj.getUserType());
			}

			if(!userInfoObj.getManagerId().equals(""))
			{
				ps.setString(i++, userInfoObj.getManagerId());
			}

			if(!userInfoObj.getFree_field_1().equals(""))
			{
				ps.setString(i++, userInfoObj.getFree_field_1());
			}

			if(!userInfoObj.getFree_field_2().equals(""))
			{
				ps.setString(i++, userInfoObj.getFree_field_2());
			}

			if(!userInfoObj.getFree_field_3().equals(""))
			{
				ps.setString(i++, userInfoObj.getFree_field_3());
			}

			if(!userInfoObj.getDel_flg().equals(""))
			{
				ps.setString(i++, userInfoObj.getDel_flg());
			}

			if(!userInfoObj.getR_cre_id().equals(""))
			{
				ps.setString(i++, userInfoObj.getR_cre_id());
			}

			if(userInfoObj.getR_cre_time() != null)
			{
				ps.setTimestamp(i++, userInfoObj.getR_cre_time());
			}

			if(!userInfoObj.getR_mod_id().equals(""))
			{
				ps.setString(i++, userInfoObj.getR_mod_id());
			}

			if(userInfoObj.getR_mod_time() != null)
			{
				ps.setTimestamp(i++, userInfoObj.getR_mod_time());
			}

			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				UserInfoVO objUserInfoVO = new UserInfoVO();

				int j = 1;

				objUserInfoVO.setFirstName(rs.getString(j++));
				objUserInfoVO.setLastName(rs.getString(j++));
				objUserInfoVO.setEmail(rs.getString(j++));
				objUserInfoVO.setUsername(rs.getString(j++));
				objUserInfoVO.setPassword(rs.getString(j++));
				objUserInfoVO.setPhone(rs.getString(j++));
				objUserInfoVO.setUserType(rs.getString(j++));
				objUserInfoVO.setManagerId(rs.getString(j++));
				objUserInfoVO.setFree_field_1(rs.getString(j++));
				objUserInfoVO.setFree_field_2(rs.getString(j++));
				objUserInfoVO.setFree_field_3(rs.getString(j++));
				objUserInfoVO.setDel_flg(rs.getString(j++));
				objUserInfoVO.setR_cre_id(rs.getString(j++));
				objUserInfoVO.setR_cre_time(rs.getTimestamp(j++));
				objUserInfoVO.setR_mod_id(rs.getString(j++));
				objUserInfoVO.setR_mod_time(rs.getTimestamp(j++));

				alUserInfoList.add(objUserInfoVO);
			}
		}
		catch(SQLException e)
		{
			throw new SQLException(GPConstants.NO_RECORDS_FOUND);
		}
		return alUserInfoList;
	}

	/**
	 * This method is used to update a record in wind_power_table
	 * @param objUserInfoVO
	 * @param conn
	 * @return number of rows updated
	 * @throws SQLException
	 */
	public int update(UserInfoVO objUserInfoVO, java.sql.Connection conn) throws SQLException
	{

		int rowsAffected = 0;

		String queryString = new String("UPDATE user_info SET " +
				" first_name = ?, " +
				" last_name = ?, " +
				" email = ?, " +
				" password = ?, " +
				" phone = ?, " +
				" usertype = ?, " +
				" manager_id = ?, " +
				" free_field_1 = ?, " +
				" free_field_2 = ?, " +
				" free_field_3 = ?, " +
				" del_flg = ?, " +
				" r_cre_id = ?, " +
				" r_cre_time = ?, " +
				" r_mod_id = ?, " +
				" r_mod_time = ? " +
				" WHERE username = ?");

		try {
			PreparedStatement ps = conn.prepareStatement(queryString);

			int i = 1;

			ps.setString(i++, objUserInfoVO.getFirstName());
			ps.setString(i++, objUserInfoVO.getLastName());
			ps.setString(i++, objUserInfoVO.getEmail());
			ps.setString(i++, objUserInfoVO.getPassword());
			ps.setString(i++, objUserInfoVO.getPhone());
			ps.setString(i++, objUserInfoVO.getUserType());
			ps.setString(i++, objUserInfoVO.getManagerId());
			ps.setString(i++, objUserInfoVO.getFree_field_1());
			ps.setString(i++, objUserInfoVO.getFree_field_2());
			ps.setString(i++, objUserInfoVO.getFree_field_3());
			ps.setString(i++, objUserInfoVO.getDel_flg());
			ps.setString(i++, objUserInfoVO.getR_cre_id());
			ps.setTimestamp(i++, objUserInfoVO.getR_cre_time());
			ps.setString(i++, objUserInfoVO.getR_mod_id());
			ps.setTimestamp(i++, objUserInfoVO.getR_mod_time());
			ps.setString(i++, objUserInfoVO.getUsername());

			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(GPConstants.UPDATE_ERROR);
		}

		return rowsAffected;
	}
}
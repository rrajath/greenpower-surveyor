/**
 * 
 */
package com.uic.gpsurveyor.VO;

import java.sql.Timestamp;

/**
 * @author rajath
 *
 */
public class WindPowerVO {

	private int wpId;
	private float windSpeed;
	private float airDensity;
	private float humidity;
	private float area;
	private String city;
	private String county;
	private String state;
	private String free_field_1;
	private String free_field_2;
	private String free_field_3;
	private String del_flg;
	private String r_cre_id;
	private Timestamp r_cre_time;
	private String r_mod_id;
	private Timestamp r_mod_time;

	/**
	 * @param windSpeed
	 * @param airDensity
	 * @param humidity
	 * @param area
	 * @param city
	 * @param county
	 * @param state
	 * @param free_field_1
	 * @param free_field_2
	 * @param free_field_3
	 * @param del_flg
	 * @param r_cre_id
	 * @param r_cre_time
	 * @param r_mod_id
	 * @param r_mod_time
	 */
	public WindPowerVO() {
		wpId = 0;
		windSpeed = 0.0f;
		airDensity = 0.0f;
		humidity = 0.0f;
		area = 0.0f;
		city = "";
		county = "";
		state = "";
		free_field_1 = "";
		free_field_2 = "";
		free_field_3 = "";
		del_flg = "";
		r_cre_id = "";
		r_cre_time = null;
		r_mod_id = "";
		r_mod_time = null;
	}

	/**
	 * @return the windSpeed
	 */
	public float getWindSpeed() {
		return windSpeed;
	}
	/**
	 * @param windSpeed the windSpeed to set
	 */
	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}
	/**
	 * @return the airDensity
	 */
	public float getAirDensity() {
		return airDensity;
	}
	/**
	 * @param airDensity the airDensity to set
	 */
	public void setAirDensity(float airDensity) {
		this.airDensity = airDensity;
	}
	/**
	 * @return the humidity
	 */
	public float getHumidity() {
		return humidity;
	}
	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the free_field_1
	 */
	public String getFree_field_1() {
		return free_field_1;
	}
	/**
	 * @param free_field_1 the free_field_1 to set
	 */
	public void setFree_field_1(String free_field_1) {
		this.free_field_1 = free_field_1;
	}
	/**
	 * @return the free_field_2
	 */
	public String getFree_field_2() {
		return free_field_2;
	}
	/**
	 * @param free_field_2 the free_field_2 to set
	 */
	public void setFree_field_2(String free_field_2) {
		this.free_field_2 = free_field_2;
	}
	/**
	 * @return the free_field_3
	 */
	public String getFree_field_3() {
		return free_field_3;
	}
	/**
	 * @param free_field_3 the free_field_3 to set
	 */
	public void setFree_field_3(String free_field_3) {
		this.free_field_3 = free_field_3;
	}
	/**
	 * @return the del_flg
	 */
	public String getDel_flg() {
		return del_flg;
	}
	/**
	 * @param del_flg the del_flg to set
	 */
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	/**
	 * @return the r_cre_id
	 */
	public String getR_cre_id() {
		return r_cre_id;
	}
	/**
	 * @param r_cre_id the r_cre_id to set
	 */
	public void setR_cre_id(String r_cre_id) {
		this.r_cre_id = r_cre_id;
	}
	/**
	 * @return the r_mod_id
	 */
	public String getR_mod_id() {
		return r_mod_id;
	}
	/**
	 * @param r_mod_id the r_mod_id to set
	 */
	public void setR_mod_id(String r_mod_id) {
		this.r_mod_id = r_mod_id;
	}
	/**
	 * @return the area
	 */
	public float getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(float area) {
		this.area = area;
	}

	/**
	 * @return the wpId
	 */
	public int getWpId() {
		return wpId;
	}

	/**
	 * @param wpId the wpId to set
	 */
	public void setWpId(int wpId) {
		this.wpId = wpId;
	}

	/**
	 * @return the r_cre_time
	 */
	public Timestamp getR_cre_time() {
		return r_cre_time;
	}

	/**
	 * @return the r_mod_time
	 */
	public Timestamp getR_mod_time() {
		return r_mod_time;
	}

	/**
	 * @param r_cre_time the r_cre_time to set
	 */
	public void setR_cre_time(Timestamp r_cre_time) {
		this.r_cre_time = r_cre_time;
	}

	/**
	 * @param r_mod_time the r_mod_time to set
	 */
	public void setR_mod_time(Timestamp r_mod_time) {
		this.r_mod_time = r_mod_time;
	}
}

/**
 * 
 */
package com.uic.gpsurveyor.VO;

import java.sql.Timestamp;

/**
 * @author rajath
 *
 */
public class TidalPowerVO {

	private int tpId;
	private float length;
	private float density;
	private float height;
	private float flow_rate;
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
	 * @author rajath
	 * Constructor
	 */
	public TidalPowerVO()
	{
		tpId = 0;
		length = 0.0f;
		density = 0.0f;
		height = 0.0f;
		flow_rate = 0.0f;
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
	 * @return the length
	 */
	public float getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(float length) {
		this.length = length;
	}
	/**
	 * @return the density
	 */
	public float getDensity() {
		return density;
	}
	/**
	 * @param density the density to set
	 */
	public void setDensity(float density) {
		this.density = density;
	}
	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	/**
	 * @return the flow_rate
	 */
	public float getFlow_rate() {
		return flow_rate;
	}
	/**
	 * @param flow_rate the flow_rate to set
	 */
	public void setFlow_rate(float flow_rate) {
		this.flow_rate = flow_rate;
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
	 * @return the r_cre_time
	 */
	public Timestamp getR_cre_time() {
		return r_cre_time;
	}
	/**
	 * @param r_cre_time the r_cre_time to set
	 */
	public void setR_cre_time(Timestamp r_cre_time) {
		this.r_cre_time = r_cre_time;
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
	 * @return the r_mod_time
	 */
	public Timestamp getR_mod_time() {
		return r_mod_time;
	}
	/**
	 * @param r_mod_time the r_mod_time to set
	 */
	public void setR_mod_time(Timestamp r_mod_time) {
		this.r_mod_time = r_mod_time;
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
	 * @return the tpId
	 */
	public int getTpId() {
		return tpId;
	}

	/**
	 * @param tpId the tpId to set
	 */
	public void setTpId(int tpId) {
		this.tpId = tpId;
	}
}

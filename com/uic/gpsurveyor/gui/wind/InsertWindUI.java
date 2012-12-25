package com.uic.gpsurveyor.gui.wind;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.uic.gpsurveyor.VO.WindPowerVO;
import com.uic.gpsurveyor.gui.main.ManagerUI;
import com.uic.gpsurveyor.gui.main.SurveyorUI;
import com.uic.gpsurveyor.service.common.GetRealTimeDataService;
import com.uic.gpsurveyor.service.wind.InsertWindDataService;
import com.uic.gpsurveyor.service.wind.UpdateWindDataService;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;
import com.uic.gpsurveyor.validator.GPValidator;

public class InsertWindUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtWindSpeed;
	private JTextField txtAirDensity;
	private JTextField txtHumidity;
	private JTextField txtArea;
	private JTextField txtCity;
	private JTextField txtCounty;
	private JTextField txtState;

	private boolean bSubmitFlag = true;

	/**
	 * Create the frame.
	 */
	public void showInsertWindPage() {
		setTitle("Insert Wind Data");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(GPConstants.WINDOW_X, GPConstants.WINDOW_Y, GPConstants.WINDOW_WIDTH, GPConstants.WINDOW_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 20, 6));

		JButton btnBack = new JButton("BACK");
		panel.add(btnBack);

		JButton btnRealTimeData = new JButton("GET REAL TIME DATA");
		panel.add(btnRealTimeData);

		JLabel lblWindSpeed = new JLabel("WIND SPEED");
		panel.add(lblWindSpeed);

		txtWindSpeed = new JTextField();
		panel.add(txtWindSpeed);
		txtWindSpeed.setColumns(10);

		JLabel lblAirDensity = new JLabel("AIR DENSITY");
		panel.add(lblAirDensity);

		txtAirDensity = new JTextField();
		panel.add(txtAirDensity);
		txtAirDensity.setColumns(10);

		JLabel lblHumidity = new JLabel("HUMIDITY");
		panel.add(lblHumidity);

		txtHumidity = new JTextField();
		panel.add(txtHumidity);
		txtHumidity.setColumns(10);

		JLabel lblArea = new JLabel("AREA (in sq.miles)");
		panel.add(lblArea);

		txtArea = new JTextField();
		panel.add(txtArea);
		txtArea.setColumns(10);

		JLabel lblCity = new JLabel("CITY");
		panel.add(lblCity);

		txtCity = new JTextField();
		panel.add(txtCity);
		txtCity.setColumns(10);

		JLabel lblCounty = new JLabel("COUNTY");
		panel.add(lblCounty);

		txtCounty = new JTextField();
		panel.add(txtCounty);
		txtCounty.setColumns(10);

		JLabel lblState = new JLabel("STATE");
		panel.add(lblState);

		txtState = new JTextField();
		panel.add(txtState);
		txtState.setColumns(10);

		/*
		 * Loading previous values - START
		 */
		WindPowerVO objWindPowerVO = new WindPowerVO();
		if(Scratchpad.ht.get("WindPowerVO") != null)
		{
			objWindPowerVO = (WindPowerVO)Scratchpad.ht.get("WindPowerVO");

			txtWindSpeed.setText(String.valueOf(objWindPowerVO.getWindSpeed()));
			txtAirDensity.setText(String.valueOf(objWindPowerVO.getAirDensity()));
			txtHumidity.setText(String.valueOf(objWindPowerVO.getHumidity()));
			txtArea.setText(String.valueOf(objWindPowerVO.getArea()));
			txtCity.setText(String.valueOf(objWindPowerVO.getCity()));
			txtCounty.setText(String.valueOf(objWindPowerVO.getCounty()));
			txtState.setText(String.valueOf(objWindPowerVO.getState()));

			Scratchpad.ht.put("wpId", objWindPowerVO.getWpId());

			bSubmitFlag = false;
		}
		/*
		 * Loading previous values - END
		 */

		JButton btnSubmit = new JButton("SUBMIT");
		JButton btnUpdate = new JButton("UPDATE");
		if(bSubmitFlag)
			panel.add(btnSubmit);
		else
			panel.add(btnUpdate);

		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Hashtable ht = new Hashtable();

				WindPowerVO objWindPowerVO = new WindPowerVO();

				try {
					validateFields();

					objWindPowerVO.setWindSpeed(Float.parseFloat(txtWindSpeed.getText()));
					objWindPowerVO.setAirDensity(Float.parseFloat(txtAirDensity.getText()));
					objWindPowerVO.setHumidity(Float.parseFloat(txtHumidity.getText()));
					objWindPowerVO.setArea(Float.parseFloat(txtArea.getText()));
					objWindPowerVO.setCity(txtCity.getText());
					objWindPowerVO.setCounty(txtCounty.getText());
					objWindPowerVO.setState(txtState.getText());

					ht.put("WindPowerVO", objWindPowerVO);

					InsertWindDataService.execute(ht);

					setVisible(false);

					if(Scratchpad.ht.get("login_user_type").equals(GPConstants.USER_TYPE_MANAGER))
					{
						ManagerUI mui = new ManagerUI();
						mui.showManagerPage();
					}
					else
					{
						SurveyorUI sui = new SurveyorUI();
						sui.showSurveyorPage();
					}
				} catch (ClassNotFoundException | SQLException e) {
					if(e.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					if(e.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Hashtable ht = new Hashtable();

				WindPowerVO objWindPowerVO_new = new WindPowerVO();

				try {
					validateFields();

					objWindPowerVO_new.setWindSpeed(Float.parseFloat(txtWindSpeed.getText()));
					objWindPowerVO_new.setAirDensity(Float.parseFloat(txtAirDensity.getText()));
					objWindPowerVO_new.setHumidity(Float.parseFloat(txtHumidity.getText()));
					objWindPowerVO_new.setArea(Float.parseFloat(txtArea.getText()));
					objWindPowerVO_new.setCity(txtCity.getText());
					objWindPowerVO_new.setCounty(txtCounty.getText());
					objWindPowerVO_new.setState(txtState.getText());
					objWindPowerVO_new.setWpId(Integer.parseInt(Scratchpad.ht.get("wpId").toString()));

					ht.put("WindPowerVO", objWindPowerVO_new);

					UpdateWindDataService.execute(ht);

					setVisible(false);

					if(Scratchpad.ht.get("login_user_type").equals(GPConstants.USER_TYPE_MANAGER))
					{
						ManagerUI mui = new ManagerUI();
						mui.showManagerPage();
					}
					else
					{
						SurveyorUI sui = new SurveyorUI();
						sui.showSurveyorPage();
					}
				} catch (ClassNotFoundException | SQLException e) {
					if(e.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					if(e.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JButton btnClear = new JButton("CLEAR");
		panel.add(btnClear);

		// Clear data in all fields
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				txtWindSpeed.setText("");
				txtAirDensity.setText("");
				txtHumidity.setText("");
				txtArea.setText("");
				txtCity.setText("");
				txtCounty.setText("");
				txtState.setText("");
			}
		});

		// Fetches the real time data from a third party API
		btnRealTimeData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				try {
					if(txtCity.getText().equals(""))
					{
						setVisible(true);
						JOptionPane.showMessageDialog(null, "Select city to get data");
					}
					else
					{
						Hashtable ht = new Hashtable();
						ht = GetRealTimeDataService.execute(txtCity.getText());
						if(ht != null)
						{
							setVisible(true);
							txtWindSpeed.setText(ht.get("windSpeed").toString());
							txtAirDensity.setText(ht.get("airDensity").toString());
							txtHumidity.setText(ht.get("humidity").toString());
						}
						else
						{
							setVisible(true);
							JOptionPane.showMessageDialog(null, "Could not obtain real time data");
						}
					}
				} catch (XPathExpressionException | IOException
						| ParserConfigurationException | SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// Back button. Takes back to Surveyor home page.
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				setVisible(false);
				SearchWindDataUI sui = new SearchWindDataUI();
				sui.showSearchPage();
			}
		});


	}
	public void validateFields() throws Exception
	{
		try {
			// Performing mandatory field validations
			GPValidator.mandatoryValidator("Wind Speed", txtWindSpeed.getText());
			GPValidator.mandatoryValidator("Air Density", txtAirDensity.getText());
			GPValidator.mandatoryValidator("Humidity", txtHumidity.getText());
			GPValidator.mandatoryValidator("Area", txtArea.getText());
			GPValidator.mandatoryValidator("City", txtCity.getText());
			GPValidator.mandatoryValidator("County", txtCounty.getText());
			GPValidator.mandatoryValidator("State", txtState.getText());

			// Performing numeric validations
			GPValidator.isNumber("Wind Speed", txtWindSpeed.getText());
			GPValidator.isNumber("Air Density", txtAirDensity.getText());
			GPValidator.isNumber("Humidity", txtHumidity.getText());
			GPValidator.isNumber("Area", txtArea.getText());

			// Performing string validations
			GPValidator.isAlpha("City", txtCity.getText());
			GPValidator.isAlpha("County", txtCounty.getText());
			GPValidator.isAlpha("State", txtState.getText());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}

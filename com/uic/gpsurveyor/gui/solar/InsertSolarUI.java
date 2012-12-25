package com.uic.gpsurveyor.gui.solar;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.uic.gpsurveyor.VO.SolarPowerVO;
import com.uic.gpsurveyor.gui.main.ManagerUI;
import com.uic.gpsurveyor.gui.main.SurveyorUI;
import com.uic.gpsurveyor.service.solar.InsertSolarDataService;
import com.uic.gpsurveyor.service.solar.UpdateSolarDataService;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;
import com.uic.gpsurveyor.validator.GPValidator;

public class InsertSolarUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtHighestTemp;
	private JTextField txtLowestTemp;
	private JTextField txtAvgTemp;
	private JTextField txtCellCount;
	private JTextField txtArea;
	private JTextField txtCity;
	private JTextField txtCounty;
	private JTextField txtState;

	private final boolean bSubmitFlag = true;
	/**
	 * Create the frame.
	 */
	public void showInsertSolarPage() {
		setTitle("Insert Solar Data");
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

		JLabel lblHighestTemp = new JLabel("HIGHEST TEMP");
		panel.add(lblHighestTemp);

		txtHighestTemp = new JTextField();
		panel.add(txtHighestTemp);
		txtHighestTemp.setColumns(10);

		JLabel lblLowestTemp = new JLabel("LOWEST TEMP");
		panel.add(lblLowestTemp);

		txtLowestTemp = new JTextField();
		panel.add(txtLowestTemp);
		txtLowestTemp.setColumns(10);

		JLabel lblAvgTemp = new JLabel("AVERAGE TEMP");
		panel.add(lblAvgTemp);

		txtAvgTemp = new JTextField();
		panel.add(txtAvgTemp);
		txtAvgTemp.setColumns(10);

		JLabel lblCellCount = new JLabel("VOLTAIC CELL COUNT");
		panel.add(lblCellCount);

		txtCellCount = new JTextField();
		panel.add(txtCellCount);
		txtCellCount.setColumns(10);

		JLabel lblArea = new JLabel("AREA");
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

		JButton btnSubmit = new JButton("SUBMIT");
		JButton btnUpdate = new JButton("UPDATE");
		if(bSubmitFlag)
			panel.add(btnSubmit);
		else
			panel.add(btnUpdate);

		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hashtable ht = new Hashtable();

				SolarPowerVO objSolarPower = new SolarPowerVO();

				try {
					validateFields();

					objSolarPower.setAvgTemp(Float.parseFloat(txtAvgTemp.getText()));
					objSolarPower.setHighestTemp(Float.parseFloat(txtHighestTemp.getText()));
					objSolarPower.setLowestTemp(Float.parseFloat(txtLowestTemp.getText()));
					objSolarPower.setCellCount(Integer.parseInt(txtCellCount.getText()));
					objSolarPower.setArea(Float.parseFloat(txtArea.getText()));
					objSolarPower.setCity(txtCity.getText());
					objSolarPower.setCounty(txtCounty.getText());
					objSolarPower.setState(txtState.getText());

					ht.put("SolarPowerVO", objSolarPower);

					InsertSolarDataService.execute(ht);

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
				} catch (ClassNotFoundException | SQLException se) {
					if(se.getMessage()==null)
					{
						se.printStackTrace();
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					}
					else
						JOptionPane.showMessageDialog(null, se.getMessage());
				} catch (Exception ex) {
					ex.printStackTrace();
					if(ex.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}

		});

		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Hashtable ht = new Hashtable();

				SolarPowerVO objSolarPower = new SolarPowerVO();

				objSolarPower.setAvgTemp(Float.parseFloat(txtAvgTemp.getText()));
				objSolarPower.setHighestTemp(Float.parseFloat(txtHighestTemp.getText()));
				objSolarPower.setLowestTemp(Float.parseFloat(txtLowestTemp.getText()));
				objSolarPower.setCellCount(Integer.parseInt(txtCellCount.getText()));
				objSolarPower.setArea(Float.parseFloat(txtArea.getText()));
				objSolarPower.setCity(txtCity.getText());
				objSolarPower.setCounty(txtCounty.getText());
				objSolarPower.setState(txtState.getText());

				ht.put("SolarPowerVO", objSolarPower);

				try {
					UpdateSolarDataService.execute(ht);

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

		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtHighestTemp.setText("");
				txtLowestTemp.setText("");
				txtAvgTemp.setText("");
				txtCellCount.setText("");
				txtArea.setText("");
				txtCity.setText("");
				txtCounty.setText("");
				txtState.setText("");
			}
		});

		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				SearchSolarDataUI ssui = new SearchSolarDataUI();
				ssui.showSearchPage();
			}
		});
	}

	public void validateFields() throws Exception
	{
		try {
			GPValidator.mandatoryValidator("Highest Temperature", txtHighestTemp.getText());
			GPValidator.mandatoryValidator("Lowest Temperature", txtLowestTemp.getText());
			GPValidator.mandatoryValidator("Average Temperature", txtAvgTemp.getText());
			GPValidator.mandatoryValidator("Voltaic Cell Count", txtCellCount.getText());
			GPValidator.mandatoryValidator("Area", txtArea.getText());
			GPValidator.mandatoryValidator("City", txtCity.getText());
			GPValidator.mandatoryValidator("County", txtCounty.getText());
			GPValidator.mandatoryValidator("State", txtState.getText());

			GPValidator.isNumber("Highest Temperature", txtHighestTemp.getText());
			GPValidator.isNumber("Lowest Temperature", txtLowestTemp.getText());
			GPValidator.isNumber("Average Temperature", txtAvgTemp.getText());
			GPValidator.isNumber("Voltaic Cell Count", txtCellCount.getText());
			GPValidator.isNumber("Area", txtArea.getText());

			GPValidator.isAlpha("City", txtCity.getText());
			GPValidator.isAlpha("County", txtCounty.getText());
			GPValidator.isAlpha("State", txtState.getText());

			if(!txtHighestTemp.getText().equals("") && !txtLowestTemp.getText().equals(""))
			{
				if(Float.parseFloat(txtHighestTemp.getText()) < Float.parseFloat(txtLowestTemp.getText()))
				{
					throw new Exception("Highest Temperature must be greater than lowest temperature");
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}
	}

}
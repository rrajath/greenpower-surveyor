package com.uic.gpsurveyor.gui.tidal;

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

import com.uic.gpsurveyor.VO.TidalPowerVO;
import com.uic.gpsurveyor.gui.main.ManagerUI;
import com.uic.gpsurveyor.gui.main.SurveyorUI;
import com.uic.gpsurveyor.service.solar.UpdateSolarDataService;
import com.uic.gpsurveyor.service.tidal.InsertTidalDataService;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;
import com.uic.gpsurveyor.validator.GPValidator;

public class InsertTidalUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtLength;
	private JTextField txtDensity;
	private JTextField txtHeight;
	private JTextField txtFlowRate;
	private JTextField txtCity;
	private JTextField txtCounty;
	private JTextField txtState;

	private final boolean bSubmitFlag = true;
	/**
	 * Create the frame.
	 */
	public void showInsertTidalPage() {
		setTitle("Insert Tidal Data");
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

		JLabel lblLength = new JLabel("LENGTH");
		panel.add(lblLength);

		txtLength = new JTextField();
		panel.add(txtLength);
		txtLength.setColumns(10);

		JLabel lblDensity = new JLabel("DENSITY");
		panel.add(lblDensity);

		txtDensity = new JTextField();
		panel.add(txtDensity);
		txtDensity.setColumns(10);

		JLabel lblHeight = new JLabel("HEIGHT");
		panel.add(lblHeight);

		txtHeight = new JTextField();
		panel.add(txtHeight);
		txtHeight.setColumns(10);

		JLabel lblFlowRate = new JLabel("FLOW RATE");
		panel.add(lblFlowRate);

		txtFlowRate = new JTextField();
		panel.add(txtFlowRate);
		txtFlowRate.setColumns(10);

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

				TidalPowerVO objTidalPowerVO = new TidalPowerVO();

				try {
					validateFields();

					objTidalPowerVO.setLength(Float.parseFloat(txtLength.getText()));
					objTidalPowerVO.setDensity(Float.parseFloat(txtDensity.getText()));
					objTidalPowerVO.setHeight(Float.parseFloat(txtHeight.getText()));
					objTidalPowerVO.setFlow_rate(Float.parseFloat(txtFlowRate.getText()));
					objTidalPowerVO.setCity(txtCity.getText());
					objTidalPowerVO.setCounty(txtCounty.getText());
					objTidalPowerVO.setState(txtState.getText());

					ht.put("TidalPowerVO", objTidalPowerVO);

					InsertTidalDataService.execute(ht);

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
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, se.getMessage());
				} catch (Exception ex) {
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

				try {
					TidalPowerVO objTidalPowerVO = new TidalPowerVO();

					validateFields();

					objTidalPowerVO.setLength(Float.parseFloat(txtLength.getText()));
					objTidalPowerVO.setDensity(Float.parseFloat(txtDensity.getText()));
					objTidalPowerVO.setHeight(Float.parseFloat(txtHeight.getText()));
					objTidalPowerVO.setFlow_rate(Float.parseFloat(txtFlowRate.getText()));
					objTidalPowerVO.setCity(txtCity.getText());
					objTidalPowerVO.setCounty(txtCounty.getText());
					objTidalPowerVO.setState(txtState.getText());

					ht.put("TidalPowerVO", objTidalPowerVO);

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

				txtLength.setText("");
				txtDensity.setText("");
				txtHeight.setText("");
				txtFlowRate.setText("");
				txtCity.setText("");
				txtCounty.setText("");
				txtState.setText("");
			}
		});

		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				SearchTidalDataUI stui = new SearchTidalDataUI();
				stui.showSearchPage();
			}

		});
	}
	public void validateFields() throws Exception
	{
		try {
			// Performing mandatory field validations
			GPValidator.mandatoryValidator("Length", txtLength.getText());
			GPValidator.mandatoryValidator("Density", txtDensity.getText());
			GPValidator.mandatoryValidator("Height", txtHeight.getText());
			GPValidator.mandatoryValidator("Flow Rate", txtFlowRate.getText());
			GPValidator.mandatoryValidator("City", txtCity.getText());
			GPValidator.mandatoryValidator("County", txtCounty.getText());
			GPValidator.mandatoryValidator("State", txtState.getText());

			// Performing numeric validations
			GPValidator.isNumber("Length", txtLength.getText());
			GPValidator.isNumber("Density", txtDensity.getText());
			GPValidator.isNumber("Height", txtHeight.getText());
			GPValidator.isNumber("Flow Rate", txtFlowRate.getText());

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
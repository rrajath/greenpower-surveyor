/**
 * 
 */
package com.uic.gpsurveyor.gui.locations;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.uic.gpsurveyor.VO.LocationsVO;
import com.uic.gpsurveyor.gui.inventory.SearchInventoryUI;
import com.uic.gpsurveyor.gui.main.ManagerUI;
import com.uic.gpsurveyor.service.locations.CreateLocationService;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

/**
 * @author rajath
 *
 */
public class LocationsUI extends JFrame{

	private JPanel contentPane;
	private JTextField txtCityCode;
	private JTextField txtCityName;
	private JTextField txtCounty;
	private JTextField txtState;

	private boolean bSubmitFlag = true;
	private ArrayList alCostMaint;

	/**
	 * Create the frame.
	 */
	public void showCreateLocationsPage() {
		setTitle("Locations");
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
		panel.setLayout(new GridLayout(0, 2, 10, 8));

		JButton btnBack = new JButton("BACK");
		panel.add(btnBack);
		JLabel lbldummy = new JLabel();
		panel.add(lbldummy);

		JLabel lblCityCode = new JLabel("CITY CODE");
		panel.add(lblCityCode);

		txtCityCode = new JTextField();
		panel.add(txtCityCode);
		txtCityCode.setColumns(10);

		JLabel lblCityName = new JLabel("CITY NAME");
		panel.add(lblCityName);

		txtCityName = new JTextField();
		panel.add(txtCityName);
		txtCityName.setColumns(10);

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
		btnSubmit.setPreferredSize(new Dimension(20,100));

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setPreferredSize(new Dimension(20,100));

		if(Scratchpad.ht.get("LocationsVO") != null)
		{
			LocationsVO objLocationsVO = new LocationsVO();
			objLocationsVO = (LocationsVO)Scratchpad.ht.get("LocationsVO");

			txtCityCode.setText(objLocationsVO.getCityCode());
			txtCityName.setText(objLocationsVO.getCityName());
			txtCounty.setText(objLocationsVO.getCounty());
			txtState.setText(objLocationsVO.getState());

			bSubmitFlag = false;
		}

		if(bSubmitFlag)
			panel.add(btnSubmit);
		else
			panel.add(btnUpdate);

		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Hashtable ht = new Hashtable();

				LocationsVO objLocationsInfo = new LocationsVO();
				objLocationsInfo.setCityCode(txtCityCode.getText());
				objLocationsInfo.setCityName(txtCityName.getText());
				objLocationsInfo.setCounty(txtCounty.getText());
				objLocationsInfo.setState(txtState.getText());

				ht.put("LocationsVO", objLocationsInfo);

				CreateLocationService objCreateLocationService = new CreateLocationService();
				try {
					objCreateLocationService.execute(ht);
				} catch (ClassNotFoundException | SQLException e) {
					if(e.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch (Exception e) {
					if(e.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}

				// Back to Surveyor Home Page
				setVisible(false);
				ManagerUI mgui1 =new ManagerUI();
				mgui1.showManagerPage();
				mgui1.setVisible(true);
			}
		});

		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtCityName.setText("");
				txtCounty.setText("");
				txtCityCode.setText("");
				txtState.setText("");
			}
		});
		panel.add(btnReset);

		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				SearchInventoryUI mui =new SearchInventoryUI();
				mui.showSearchPage();
				mui.setVisible(true);
				//	showPanel();

			}
		});
	}
}

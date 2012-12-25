package com.uic.gpsurveyor.gui.inventory;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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

import com.uic.gpsurveyor.VO.CostMaintVO;
import com.uic.gpsurveyor.VO.LocationsVO;
import com.uic.gpsurveyor.gui.locations.LocationListUI;
import com.uic.gpsurveyor.gui.locations.LocationsUI;
import com.uic.gpsurveyor.gui.main.MaintenanceUI;
import com.uic.gpsurveyor.gui.main.ManagerUI;
import com.uic.gpsurveyor.service.inventory.SearchInventoryService;
import com.uic.gpsurveyor.service.locations.SearchLocationsService;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;
import com.uic.gpsurveyor.validator.GPValidator;

public class SearchInventoryUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtModel;
	private JTextField txtType;
	private JTextField txtManufacturer;

	private JTextField txtCityCode;
	private JTextField txtCityName;
	private JTextField txtCounty;
	private JTextField txtState;

	private ArrayList alCostMaint;
	/**
	 * Create the frame.
	 */
	public void showSearchPage() {
		setTitle("Search Inventory");
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
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JButton btnBack = new JButton("BACK");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 0;
		panel.add(btnBack, gbc_btnBack);

		JLabel lblSearchCrit = new JLabel("SEARCH INVENTORY");
		GridBagConstraints gbc_lblEnterAnyOf = new GridBagConstraints();
		gbc_lblEnterAnyOf.anchor = GridBagConstraints.WEST;
		gbc_lblEnterAnyOf.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterAnyOf.gridx = 2;
		gbc_lblEnterAnyOf.gridy = 1;
		panel.add(lblSearchCrit, gbc_lblEnterAnyOf);

		JLabel lblModel = new JLabel("MODEL");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblModel, gbc_lblNewLabel);

		txtModel = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		panel.add(txtModel, gbc_textField);
		txtModel.setColumns(10);

		JLabel lblType = new JLabel("TYPE");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.insets = new Insets(0, 0, 5, 0);
		gbc_lblType.gridx = 1;
		gbc_lblType.gridy = 3;
		panel.add(lblType, gbc_lblType);

		txtType = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		panel.add(txtType, gbc_textField_2);
		txtType.setColumns(10);

		JLabel lblManufacturer = new JLabel("MANUFACTURER");
		GridBagConstraints gbc_lblManufacturer = new GridBagConstraints();
		gbc_lblManufacturer.anchor = GridBagConstraints.EAST;
		gbc_lblManufacturer.insets = new Insets(0, 0, 5, 5);
		gbc_lblManufacturer.gridx = 1;
		gbc_lblManufacturer.gridy = 4;
		panel.add(lblManufacturer, gbc_lblManufacturer);

		txtManufacturer = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.anchor = GridBagConstraints.WEST;
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 4;
		panel.add(txtManufacturer, gbc_textField_3);
		txtManufacturer.setColumns(10);

		JButton btnSearch = new JButton("SEARCH");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 5;
		panel.add(btnSearch, gbc_btnSearch);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Hashtable htParams = new Hashtable();

				try {
					validateInventoryFields();

					CostMaintVO objCostMaintVO = new CostMaintVO();
					objCostMaintVO.setType(txtType.getText());
					objCostMaintVO.setModel(txtModel.getText());
					objCostMaintVO.setManufacturer(txtModel.getText());

					htParams.put("CostMaintVO", objCostMaintVO);

					alCostMaint = SearchInventoryService.execute(htParams);

					InventoryListUI objInventoryListUI = new InventoryListUI();
					objInventoryListUI.showInventoryList(alCostMaint);
					setVisible(false);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					if(e.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					if(e.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JButton btnReset = new JButton("RESET");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 5, 0);
		gbc_btnReset.anchor = GridBagConstraints.WEST;
		gbc_btnReset.gridx = 2;
		gbc_btnReset.gridy = 5;
		panel.add(btnReset, gbc_btnReset);

		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtModel.setText("");
				txtManufacturer.setText("");
				txtType.setText("");
			}
		});

		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				ManagerUI mui =new ManagerUI();
				mui.showManagerPage();
				mui.setVisible(true);
				//	showPanel();

			}
		});

		JButton btnAddInventory = new JButton("Add Inventory");
		GridBagConstraints gbc_btnAddInventory = new GridBagConstraints();
		gbc_btnAddInventory.gridx = 2;
		gbc_btnAddInventory.gridy = 5;
		panel.add(btnAddInventory, gbc_btnAddInventory);

		JLabel lblSearchLocations = new JLabel("SEARCH LOCATIONS");
		GridBagConstraints gbc_lblSearchLocations = new GridBagConstraints();
		gbc_lblSearchLocations.anchor = GridBagConstraints.WEST;
		//		gbc_lblSearchLocations.insets = new Insets(0, 0, 5, 0);
		gbc_lblSearchLocations.gridx = 2;
		gbc_lblSearchLocations.gridy = 7;
		panel.add(lblSearchLocations, gbc_lblSearchLocations);

		JLabel lblCityCode = new JLabel("CITY CODE");
		GridBagConstraints gbc_lblCityCode = new GridBagConstraints();
		gbc_lblCityCode.anchor = GridBagConstraints.EAST;
		gbc_lblCityCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCityCode.gridx = 1;
		gbc_lblCityCode.gridy = 8;
		panel.add(lblCityCode, gbc_lblCityCode);

		txtCityCode = new JTextField();
		GridBagConstraints gbc_CityCode = new GridBagConstraints();
		gbc_CityCode.anchor = GridBagConstraints.WEST;
		gbc_CityCode.insets = new Insets(0, 0, 5, 0);
		gbc_CityCode.gridx = 2;
		gbc_CityCode.gridy = 8;
		panel.add(txtCityCode, gbc_CityCode);
		txtCityCode.setColumns(10);

		JLabel lblCityName = new JLabel("CITY NAME");
		GridBagConstraints gbc_lblCityName = new GridBagConstraints();
		gbc_lblCityName.anchor = GridBagConstraints.EAST;
		gbc_lblCityName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCityName.gridx = 1;
		gbc_lblCityName.gridy = 9;
		panel.add(lblCityName, gbc_lblCityName);

		txtCityName = new JTextField();
		GridBagConstraints gbc_txtCityName = new GridBagConstraints();
		gbc_txtCityName.anchor = GridBagConstraints.WEST;
		gbc_txtCityName.insets = new Insets(0, 0, 5, 0);
		gbc_txtCityName.gridx = 2;
		gbc_txtCityName.gridy = 9;
		panel.add(txtCityName, gbc_txtCityName);
		txtCityName.setColumns(10);

		JLabel lblCounty = new JLabel("COUNTY");
		GridBagConstraints gbc_lblCounty = new GridBagConstraints();
		gbc_lblCounty.anchor = GridBagConstraints.EAST;
		gbc_lblCounty.insets = new Insets(0, 0, 5, 5);
		gbc_lblCounty.gridx = 1;
		gbc_lblCounty.gridy = 10;
		panel.add(lblCounty, gbc_lblCounty);

		txtCounty = new JTextField();
		GridBagConstraints gbc_txtCounty = new GridBagConstraints();
		gbc_txtCounty.insets = new Insets(0, 0, 5, 0);
		gbc_txtCounty.anchor = GridBagConstraints.WEST;
		gbc_txtCounty.gridx = 2;
		gbc_txtCounty.gridy = 10;
		panel.add(txtCounty, gbc_txtCounty);
		txtCounty.setColumns(10);

		JLabel lblState = new JLabel("STATE");
		GridBagConstraints gbc_lblState = new GridBagConstraints();
		gbc_lblState.anchor = GridBagConstraints.EAST;
		gbc_lblState.insets = new Insets(0, 0, 5, 5);
		gbc_lblState.gridx = 1;
		gbc_lblState.gridy = 11;
		panel.add(lblState, gbc_lblState);

		txtState = new JTextField();
		GridBagConstraints gbc_txtState = new GridBagConstraints();
		gbc_txtState.insets = new Insets(0, 0, 5, 0);
		gbc_txtState.anchor = GridBagConstraints.WEST;
		gbc_txtState.gridx = 2;
		gbc_txtState.gridy = 11;
		panel.add(txtState, gbc_txtState);
		txtState.setColumns(10);

		JButton btnSearchLocations = new JButton("Search Locations");
		GridBagConstraints gbc_btnSearchLocations = new GridBagConstraints();
		gbc_btnSearchLocations.gridx = 1;
		gbc_btnSearchLocations.gridy = 12;
		panel.add(btnSearchLocations, gbc_btnSearchLocations);

		JButton btnAddLocations = new JButton("Add Locations");
		GridBagConstraints gbc_btnAddLocations = new GridBagConstraints();
		gbc_btnAddLocations.gridx = 2;
		gbc_btnAddLocations.gridy = 12;
		panel.add(btnAddLocations, gbc_btnAddLocations);

		btnSearchLocations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Hashtable htParams = new Hashtable();

				try {
					validateLocationFields();

					LocationsVO objLocationsVO = new LocationsVO();
					objLocationsVO.setCityCode(txtCityCode.getText());
					objLocationsVO.setCityName(txtCityName.getText());
					objLocationsVO.setCounty(txtCounty.getText());
					objLocationsVO.setState(txtState.getText());

					htParams.put("LocationsVO", objLocationsVO);

					ArrayList alLocations = new ArrayList();

					alLocations = SearchLocationsService.execute(htParams);

					LocationListUI objLocationListUI = new LocationListUI();
					objLocationListUI.showLocationsList(alLocations);
					setVisible(false);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					if(e.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					if(e.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		btnAddLocations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				if(Scratchpad.ht.get("LocationsVO") != null)
				{
					Scratchpad.ht.remove("LocationsVO");
				}
				LocationsUI lui =new LocationsUI();
				lui.showCreateLocationsPage();
				lui.setVisible(true);
				//	showPanel();

			}
		});

		btnAddInventory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				if(Scratchpad.ht.get("CostMaint") != null)
				{
					Scratchpad.ht.remove("CostMaint");
				}
				MaintenanceUI mgui1 =new MaintenanceUI();
				mgui1.showSettingsPage();
				mgui1.setVisible(true);
				//	showPanel();

			}
		});

	}

	public void validateLocationFields() throws Exception
	{
		GPValidator.isAlpha("City Code", txtCityCode.getText());
		GPValidator.isAlpha("City Name", txtCityName.getText());
		GPValidator.isAlpha("County", txtCounty.getText());
		GPValidator.isAlpha("State", txtState.getText());
	}

	public void validateInventoryFields() throws Exception
	{
		GPValidator.isAlpha("Model", txtModel.getText());
		GPValidator.isAlpha("Manufacturer", txtManufacturer.getText());
		GPValidator.isAlpha("Type", txtType.getText());
	}
}

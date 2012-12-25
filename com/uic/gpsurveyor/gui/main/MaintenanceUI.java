package com.uic.gpsurveyor.gui.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.uic.gpsurveyor.VO.CostMaintVO;
import com.uic.gpsurveyor.gui.inventory.SearchInventoryUI;
import com.uic.gpsurveyor.service.inventory.CreateInventoryService;
import com.uic.gpsurveyor.service.inventory.UpdateInventoryService;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

public class MaintenanceUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtType;
	private JTextField txtManufacturer;
	private JTextField txtModel;
	private JTextField txtCost;
	private JTextField txtService;
	private JTextField txtRotorLength;
	private JTextField txtModelName;

	private boolean bSubmitFlag = true;

	/**
	 * Create the frame.
	 */
	public void showSettingsPage() {
		setTitle("Maintenance");
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
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JButton btnBack = new JButton("BACK");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 2;
		gbc_btnBack.gridy = 0;
		panel.add(btnBack, gbc_btnBack);

		JLabel lblType = new JLabel("TYPE");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 2;
		gbc_lblType.gridy = 1;
		panel.add(lblType, gbc_lblType);

		txtType = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		panel.add(txtType, gbc_textField);
		txtType.setColumns(10);

		JLabel lblManufacturer = new JLabel("MANUFACTURER");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblManufacturer, gbc_lblNewLabel);

		txtManufacturer = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 2;
		panel.add(txtManufacturer, gbc_textField_1);
		txtManufacturer.setColumns(10);

		JLabel lblModel = new JLabel("MODEL");
		GridBagConstraints gbc_lblModel = new GridBagConstraints();
		gbc_lblModel.anchor = GridBagConstraints.EAST;
		gbc_lblModel.insets = new Insets(0, 0, 5, 5);
		gbc_lblModel.gridx = 2;
		gbc_lblModel.gridy = 3;
		panel.add(lblModel, gbc_lblModel);

		txtModel = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 3;
		panel.add(txtModel, gbc_textField_2);
		txtModel.setColumns(10);

		JLabel lblCost = new JLabel("COST");
		GridBagConstraints gbc_lblCost = new GridBagConstraints();
		gbc_lblCost.anchor = GridBagConstraints.EAST;
		gbc_lblCost.insets = new Insets(0, 0, 5, 5);
		gbc_lblCost.gridx = 2;
		gbc_lblCost.gridy = 4;
		panel.add(lblCost, gbc_lblCost);

		txtCost = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.WEST;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 4;
		panel.add(txtCost, gbc_textField_3);
		txtCost.setColumns(10);

		JLabel lblService = new JLabel("SERVICE");
		GridBagConstraints gbc_lblService = new GridBagConstraints();
		gbc_lblService.anchor = GridBagConstraints.EAST;
		gbc_lblService.insets = new Insets(0, 0, 5, 5);
		gbc_lblService.gridx = 2;
		gbc_lblService.gridy = 5;
		panel.add(lblService, gbc_lblService);

		txtService = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.anchor = GridBagConstraints.WEST;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 5;
		panel.add(txtService, gbc_textField_4);
		txtService.setColumns(10);

		JLabel lblRotorLength = new JLabel("ROTOR LENGTH");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 6;
		panel.add(lblRotorLength, gbc_lblNewLabel_1);

		txtRotorLength = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.anchor = GridBagConstraints.WEST;
		gbc_textField_5.gridx = 3;
		gbc_textField_5.gridy = 6;
		panel.add(txtRotorLength, gbc_textField_5);
		txtRotorLength.setColumns(10);

		JLabel lblModelName = new JLabel("MODEL NAME");
		GridBagConstraints gbc_lblModel_1 = new GridBagConstraints();
		gbc_lblModel_1.anchor = GridBagConstraints.EAST;
		gbc_lblModel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblModel_1.gridx = 2;
		gbc_lblModel_1.gridy = 7;
		panel.add(lblModelName, gbc_lblModel_1);

		txtModelName = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.anchor = GridBagConstraints.WEST;
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.gridx = 3;
		gbc_textField_6.gridy = 7;
		panel.add(txtModelName, gbc_textField_6);
		txtModelName.setColumns(10);

		JButton btnSubmit = new JButton("SUBMIT");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 0, 5);
		gbc_btnSubmit.gridx = 2;
		gbc_btnSubmit.gridy = 8;
		//		panel.add(btnSubmit, gbc_btnSubmit);

		JButton btnUpdate = new JButton("UPDATE");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 0, 5);
		gbc_btnUpdate.gridx = 2;
		gbc_btnUpdate.gridy = 8;

		CostMaintVO objCostMaintVO = new CostMaintVO();

		if(Scratchpad.ht.get("CostMaint") != null)
		{
			objCostMaintVO = (CostMaintVO)Scratchpad.ht.get("CostMaint");

			txtType.setText(String.valueOf(objCostMaintVO.getType()));
			txtManufacturer.setText(String.valueOf(objCostMaintVO.getManufacturer()));
			txtModel.setText(String.valueOf(objCostMaintVO.getModel()));
			txtModelName.setText(String.valueOf(objCostMaintVO.getModelName()));
			txtCost.setText(String.valueOf(objCostMaintVO.getCost()));
			txtService.setText(String.valueOf(objCostMaintVO.getService()));
			txtRotorLength.setText(String.valueOf(objCostMaintVO.getRotorLength()));

			bSubmitFlag = false;
		}

		if(bSubmitFlag)
			panel.add(btnSubmit, gbc_btnSubmit);
		else
			panel.add(btnUpdate, gbc_btnUpdate);

		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Hashtable ht = new Hashtable();

				CostMaintVO objCostMaintVO = new CostMaintVO();
				objCostMaintVO.setCost(Float.parseFloat(txtCost.getText()));
				objCostMaintVO.setManufacturer(txtManufacturer.getText());
				objCostMaintVO.setModel(txtModel.getText());
				objCostMaintVO.setModelName(txtModelName.getText());
				objCostMaintVO.setRotorLength(Float.parseFloat(txtRotorLength.getText()));
				objCostMaintVO.setService(Float.parseFloat(txtService.getText()));
				objCostMaintVO.setType(txtType.getText());

				ht.put("CostMaintVO", objCostMaintVO);

				CreateInventoryService objCreateInventoryService = new CreateInventoryService();
				try {
					objCreateInventoryService.execute(ht);
					Scratchpad.ht.put("notification", "Inventory Created");
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

		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Hashtable ht = new Hashtable();

				CostMaintVO objCostMaintVO_new = new CostMaintVO();
				objCostMaintVO_new.setType(txtType.getText());
				objCostMaintVO_new.setCost(Float.parseFloat(txtCost.getText()));
				objCostMaintVO_new.setManufacturer(txtManufacturer.getText());
				objCostMaintVO_new.setModel(txtModel.getText());
				objCostMaintVO_new.setModelName(txtModelName.getText());
				objCostMaintVO_new.setRotorLength(Float.parseFloat(txtRotorLength.getText()));
				objCostMaintVO_new.setService(Float.parseFloat(txtService.getText()));
				objCostMaintVO_new.setCmId(((CostMaintVO)Scratchpad.ht.get("CostMaint")).getCmId());

				ht.put("CostMaintVO", objCostMaintVO_new);

				try {
					UpdateInventoryService.execute(ht);
					Scratchpad.ht.put("notification", "Inventory Updated");
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
			}
		});

		JButton btnReset = new JButton("RESET");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.anchor = GridBagConstraints.WEST;
		gbc_btnReset.gridx = 3;
		gbc_btnReset.gridy = 8;
		panel.add(btnReset, gbc_btnReset);

		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtCost.setText("");
				txtManufacturer.setText("");
				txtModel.setText("");
				txtModelName.setText("");
				txtRotorLength.setText("");
				txtService.setText("");
			}
		});

		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				SearchInventoryUI siui =new SearchInventoryUI();
				siui.showSearchPage();
				siui.setVisible(true);
			}
		});
	}

}

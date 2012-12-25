/**
 * 
 */
package com.uic.gpsurveyor.gui.wind;

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

import com.uic.gpsurveyor.VO.WindPowerVO;
import com.uic.gpsurveyor.gui.main.ManagerUI;
import com.uic.gpsurveyor.gui.main.SurveyorUI;
import com.uic.gpsurveyor.service.wind.SearchWindDataService;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;
import com.uic.gpsurveyor.validator.GPValidator;

/**
 * @author rajath
 *
 */
public class SearchWindDataUI extends JFrame{

	private JPanel contentPane;
	private JTextField txtCity;
	private JTextField txtCounty;
	private JTextField txtState;
	private ArrayList alWindPowerList;

	/**
	 * Create the frame.
	 */
	public void showSearchPage() {
		setTitle("Search Wind Data");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		JLabel lblSearchCrit = new JLabel("SEARCH WIND DATA");
		GridBagConstraints gbc_lblSearchCrit = new GridBagConstraints();
		gbc_lblSearchCrit.anchor = GridBagConstraints.WEST;
		gbc_lblSearchCrit.insets = new Insets(0, 0, 5, 0);
		gbc_lblSearchCrit.gridx = 2;
		gbc_lblSearchCrit.gridy = 1;
		panel.add(lblSearchCrit, gbc_lblSearchCrit);

		JLabel lblCity = new JLabel("City");
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.anchor = GridBagConstraints.EAST;
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.gridx = 1;
		gbc_lblCity.gridy = 2;
		panel.add(lblCity, gbc_lblCity);

		txtCity = new JTextField();
		GridBagConstraints gbc_txtCity = new GridBagConstraints();
		gbc_txtCity.anchor = GridBagConstraints.WEST;
		gbc_txtCity.insets = new Insets(0, 0, 5, 0);
		gbc_txtCity.gridx = 2;
		gbc_txtCity.gridy = 2;
		panel.add(txtCity, gbc_txtCity);
		txtCity.setColumns(10);

		JLabel lblCounty = new JLabel("County");
		GridBagConstraints gbc_lblCounty = new GridBagConstraints();
		gbc_lblCounty.anchor = GridBagConstraints.EAST;
		gbc_lblCounty.insets = new Insets(0, 0, 5, 5);
		gbc_lblCounty.gridx = 1;
		gbc_lblCounty.gridy = 3;
		panel.add(lblCounty, gbc_lblCounty);

		txtCounty = new JTextField();
		GridBagConstraints gbc_County = new GridBagConstraints();
		gbc_County.anchor = GridBagConstraints.WEST;
		gbc_County.insets = new Insets(0, 0, 5, 0);
		gbc_County.gridx = 2;
		gbc_County.gridy = 3;
		panel.add(txtCounty, gbc_County);
		txtCounty.setColumns(10);

		JLabel lblState = new JLabel("State");
		GridBagConstraints gbc_lblState = new GridBagConstraints();
		gbc_lblState.anchor = GridBagConstraints.EAST;
		gbc_lblState.insets = new Insets(0, 0, 5, 5);
		gbc_lblState.gridx = 1;
		gbc_lblState.gridy = 4;
		panel.add(lblState, gbc_lblState);

		txtState = new JTextField();
		GridBagConstraints gbc_txtState = new GridBagConstraints();
		gbc_txtState.insets = new Insets(0, 0, 5, 0);
		gbc_txtState.anchor = GridBagConstraints.WEST;
		gbc_txtState.gridx = 2;
		gbc_txtState.gridy = 4;
		panel.add(txtState, gbc_txtState);
		txtState.setColumns(10);

		JButton btnSearch = new JButton("SEARCH");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 6;
		panel.add(btnSearch, gbc_btnSearch);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Hashtable htParams = new Hashtable();

				try {
					validateFields();

					WindPowerVO objWindPowerVO = new WindPowerVO();
					objWindPowerVO.setCity(txtCity.getText());
					objWindPowerVO.setCounty(txtCounty.getText());
					objWindPowerVO.setState(txtState.getText());

					htParams.put("WindPowerVO", objWindPowerVO);

					alWindPowerList = SearchWindDataService.execute(htParams);

					WindPowerListUI objWindPowerListUI = new WindPowerListUI();
					objWindPowerListUI.showWindPowerList(alWindPowerList);
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
		gbc_btnReset.gridy = 6;
		panel.add(btnReset, gbc_btnReset);

		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtCity.setText("");
				txtCounty.setText("");
				txtState.setText("");
			}
		});

		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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

		JButton btnAdd = new JButton("ADD WIND DATA");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 6;
		panel.add(btnAdd, gbc_btnAdd);

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				if(Scratchpad.ht.get("WindPowerVO") != null)
				{
					Scratchpad.ht.remove("WindPowerVO");
				}
				InsertWindUI iui =new InsertWindUI();
				iui.showInsertWindPage();
				iui.setVisible(true);
				//	showPanel();

			}
		});
	}

	public void validateFields() throws Exception
	{
		GPValidator.isAlpha("City", txtCity.getText());
		GPValidator.isAlpha("County", txtCounty.getText());
		GPValidator.isAlpha("State", txtState.getText());
	}
}

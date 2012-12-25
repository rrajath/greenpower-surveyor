/**
 * 
 */
package com.uic.gpsurveyor.gui.user;

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

import com.uic.gpsurveyor.VO.UserInfoVO;
import com.uic.gpsurveyor.gui.main.ManagerUI;
import com.uic.gpsurveyor.service.user.SearchUsersService;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;
import com.uic.gpsurveyor.validator.GPValidator;

/**
 * @author rajath
 *
 */
public class SearchUsersUI extends JFrame{

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtManagerId;
	private ArrayList alUserList;

	/**
	 * Create the frame.
	 */
	public void showSearchPage() {
		setTitle("Search Users");
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

		JLabel lblSearchCrit = new JLabel("SEARCH USERS");
		GridBagConstraints gbc_lblEnterAnyOf = new GridBagConstraints();
		gbc_lblEnterAnyOf.anchor = GridBagConstraints.WEST;
		gbc_lblEnterAnyOf.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterAnyOf.gridx = 2;
		gbc_lblEnterAnyOf.gridy = 1;
		panel.add(lblSearchCrit, gbc_lblEnterAnyOf);

		JLabel lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 2;
		panel.add(lblUsername, gbc_lblUsername);

		txtUsername = new JTextField();
		GridBagConstraints gbc_Username = new GridBagConstraints();
		gbc_Username.anchor = GridBagConstraints.WEST;
		gbc_Username.insets = new Insets(0, 0, 5, 0);
		gbc_Username.gridx = 2;
		gbc_Username.gridy = 2;
		panel.add(txtUsername, gbc_Username);
		txtUsername.setColumns(10);

		JLabel lblFirstName = new JLabel("First Name");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.EAST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 1;
		gbc_lblFirstName.gridy = 3;
		panel.add(lblFirstName, gbc_lblFirstName);

		txtFirstName = new JTextField();
		GridBagConstraints gbc_FirstName = new GridBagConstraints();
		gbc_FirstName.anchor = GridBagConstraints.WEST;
		gbc_FirstName.insets = new Insets(0, 0, 5, 0);
		gbc_FirstName.gridx = 2;
		gbc_FirstName.gridy = 3;
		panel.add(txtFirstName, gbc_FirstName);
		txtFirstName.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.EAST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 1;
		gbc_lblLastName.gridy = 4;
		panel.add(lblLastName, gbc_lblLastName);

		txtLastName = new JTextField();
		GridBagConstraints gbc_LastName = new GridBagConstraints();
		gbc_LastName.insets = new Insets(0, 0, 5, 0);
		gbc_LastName.anchor = GridBagConstraints.WEST;
		gbc_LastName.gridx = 2;
		gbc_LastName.gridy = 4;
		panel.add(txtLastName, gbc_LastName);
		txtLastName.setColumns(10);

		JLabel lblManagerId = new JLabel("Manager ID");
		GridBagConstraints gbc_lblManagerId = new GridBagConstraints();
		gbc_lblManagerId.anchor = GridBagConstraints.EAST;
		gbc_lblManagerId.insets = new Insets(0, 0, 5, 5);
		gbc_lblManagerId.gridx = 1;
		gbc_lblManagerId.gridy = 5;
		panel.add(lblManagerId, gbc_lblManagerId);

		txtManagerId = new JTextField();
		GridBagConstraints gbc_ManagerId = new GridBagConstraints();
		gbc_ManagerId.insets = new Insets(0, 0, 5, 0);
		gbc_ManagerId.anchor = GridBagConstraints.WEST;
		gbc_ManagerId.gridx = 2;
		gbc_ManagerId.gridy = 5;
		panel.add(txtManagerId, gbc_ManagerId);
		txtManagerId.setColumns(10);

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

					UserInfoVO objuserInfoVO = new UserInfoVO();
					objuserInfoVO.setFirstName(txtFirstName.getText());
					objuserInfoVO.setLastName(txtLastName.getText());
					objuserInfoVO.setUsername(txtUsername.getText());
					objuserInfoVO.setManagerId(txtManagerId.getText());

					htParams.put("UserInfoVO", objuserInfoVO);

					alUserList = SearchUsersService.execute(htParams);

					UserListUI objUserListUI = new UserListUI();
					objUserListUI.showUserList(alUserList);
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
				txtUsername.setText("");
				txtLastName.setText("");
				txtFirstName.setText("");
				txtManagerId.setText("");
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

		JButton btnAdd = new JButton("Add User");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 6;
		panel.add(btnAdd, gbc_btnAdd);

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				if(Scratchpad.ht.get("UserInfoVO") != null)
				{
					Scratchpad.ht.remove("UserInfoVO");
				}
				CreateUserUI cui =new CreateUserUI();
				cui.showCreateUser();
				cui.setVisible(true);
				//	showPanel();

			}
		});
	}

	public void validateFields() throws Exception
	{
		GPValidator.isAlpha("First Name", txtFirstName.getText());
		GPValidator.isAlpha("Last Name", txtLastName.getText());
		GPValidator.isAlpha("Username", txtUsername.getText());
		GPValidator.isAlpha("Manager ID", txtManagerId.getText());
	}
}

package com.uic.gpsurveyor.gui.user;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.uic.gpsurveyor.VO.UserInfoVO;
import com.uic.gpsurveyor.gui.main.ManagerUI;
import com.uic.gpsurveyor.service.inventory.UpdateUserService;
import com.uic.gpsurveyor.service.user.CreateUserService;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;
import com.uic.gpsurveyor.validator.GPValidator;

public class CreateUserUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtLastName;
	private JTextField txtUsername;
	private JTextField txtFirstName;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private JTextField txtUserType;
	private JTextField txtPhone;
	private JTextField txtManagerId;
	private JComboBox cbUserType;

	private boolean bSubmitFlag;
	/**
	 * Create the frame.
	 */
	public void showCreateUser() {
		setTitle("Create User");
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

		JLabel lblFirstName = new JLabel("FIRST NAME");
		panel.add(lblFirstName);

		txtFirstName = new JTextField();
		panel.add(txtFirstName);
		txtFirstName.setColumns(10);

		JLabel lblLastName = new JLabel("LAST NAME");
		panel.add(lblLastName);

		txtLastName = new JTextField();
		panel.add(txtLastName);
		txtLastName.setColumns(10);

		JLabel lblUsername = new JLabel("USERNAME");
		panel.add(lblUsername);

		txtUsername = new JTextField();
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("PASSWORD");
		panel.add(lblPassword);

		txtPassword = new JPasswordField();
		panel.add(txtPassword);
		txtPassword.setColumns(10);

		JLabel lblConfirmPassword = new JLabel("CONFIRM PASSWORD");
		panel.add(lblConfirmPassword);

		txtConfirmPassword = new JPasswordField();
		panel.add(txtConfirmPassword);
		txtConfirmPassword.setColumns(10);

		JLabel lblUserType = new JLabel("USER TYPE");
		panel.add(lblUserType);

		cbUserType = new JComboBox();
		cbUserType.addItem("Manager");
		cbUserType.addItem("Surveyor");
		panel.add(cbUserType);

		/*txtUserType = new JTextField();
		panel.add(txtUserType);
		txtUserType.setColumns(10);*/

		JLabel lblEmail = new JLabel("EMAIL");
		panel.add(lblEmail);

		txtEmail = new JTextField();
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblPhone = new JLabel("PHONE");
		panel.add(lblPhone);

		txtPhone = new JTextField();
		panel.add(txtPhone);
		txtPhone.setColumns(10);

		JLabel lblManagerId = new JLabel("MANAGER ID");
		panel.add(lblManagerId);

		txtManagerId = new JTextField();
		txtManagerId.setText(Scratchpad.ht.get("userId").toString());
		panel.add(txtManagerId);
		txtManagerId.setColumns(10);
		txtManagerId.disable();

		JButton btnSubmit = new JButton("SUBMIT");
		//		btnSubmit.setPreferredSize(new Dimension(20,100));

		JButton btnUpdate = new JButton("UPDATE");
		//		btnUpdate.setPreferredSize(new Dimension(20,100));

		if(Scratchpad.ht.get("UserInfoVO") != null)
		{
			UserInfoVO objUserInfoVO = new UserInfoVO();
			objUserInfoVO = (UserInfoVO)Scratchpad.ht.get("UserInfoVO");

			txtLastName.setText(objUserInfoVO.getLastName());
			txtUsername.setText(objUserInfoVO.getUsername());
			txtFirstName.setText(objUserInfoVO.getFirstName());
			txtEmail.setText(objUserInfoVO.getEmail());
			txtPassword.setText(objUserInfoVO.getPassword());
			txtPhone.setText(objUserInfoVO.getPhone());
			txtManagerId.setText(objUserInfoVO.getManagerId());
			cbUserType.setSelectedItem(objUserInfoVO.getUserType()=="M"?"Manager":"Surveyor");

			bSubmitFlag = false;
		}

		if(bSubmitFlag)
			//			btnSubmit.setPreferredSize(new Dimension(20,100));
			panel.add(btnSubmit);
		else
			//			btnUpdate.setPreferredSize(new Dimension(20,100));
			panel.add(btnUpdate);

		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Hashtable ht = new Hashtable();

				try {
					validateFields();
					UserInfoVO objUserInfo = new UserInfoVO();
					objUserInfo.setFirstName(txtFirstName.getText());
					objUserInfo.setLastName(txtLastName.getText());
					objUserInfo.setUsername(txtUsername.getText());
					objUserInfo.setPassword(txtPassword.getText());
					objUserInfo.setUserType(cbUserType.getSelectedItem().toString());
					objUserInfo.setEmail(txtEmail.getText());
					objUserInfo.setPhone(txtPhone.getText());
					objUserInfo.setManagerId(txtManagerId.getText());

					ht.put("managerId", txtManagerId.getText());

					ht.put("UserInfoVO", objUserInfo);

					if(!txtPassword.getText().equals(txtConfirmPassword.getText()))
						JOptionPane.showMessageDialog(null, GPConstants.PASSWORDS_DONOT_MATCH);

					CreateUserService objCreateUserService = new CreateUserService();

					objCreateUserService.execute(ht);

					// Back to Surveyor Home Page
					setVisible(false);
					ManagerUI mgui1 =new ManagerUI();
					mgui1.showManagerPage();
					mgui1.setVisible(true);
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
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Hashtable ht = new Hashtable();

				UserInfoVO objUserInfoVO_new = new UserInfoVO();

				try {
					validateFields();

					objUserInfoVO_new.setFirstName(txtFirstName.getText());
					objUserInfoVO_new.setLastName(txtLastName.getText());
					objUserInfoVO_new.setUsername(txtUsername.getText());
					objUserInfoVO_new.setPassword(txtPassword.getText());
					objUserInfoVO_new.setUserType(cbUserType.getSelectedItem().toString());
					objUserInfoVO_new.setEmail(txtEmail.getText());
					objUserInfoVO_new.setPhone(txtPhone.getText());
					objUserInfoVO_new.setManagerId(txtManagerId.getText());

					ht.put("managerId", txtManagerId.getText());

					ht.put("UserInfoVO", objUserInfoVO_new);

					if(!txtPassword.getText().equals(txtConfirmPassword.getText()))
						JOptionPane.showMessageDialog(null, GPConstants.PASSWORDS_DONOT_MATCH);

					UpdateUserService objUpdateUserService = new UpdateUserService();

					objUpdateUserService.execute(ht);

					// Back to Surveyor Home Page
					setVisible(false);
					ManagerUI mgui1 =new ManagerUI();
					mgui1.showManagerPage();
					mgui1.setVisible(true);
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
			}
		});

		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtLastName.setText("");
				txtUsername.setText("");
				txtFirstName.setText("");
				txtEmail.setText("");
				txtPassword.setText("");
				txtUserType.setText("");
				txtPhone.setText("");
				txtManagerId.setText("");
			}
		});
		panel.add(btnReset);

		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				SearchUsersUI sui =new SearchUsersUI();
				sui.showSearchPage();
				sui.setVisible(true);
				//	showPanel();

			}
		});
	}
	public void validateFields() throws Exception
	{

		// MANDATORY FIELD VALIDATIONS
		GPValidator.mandatoryValidator("First Name", txtFirstName.getText());
		GPValidator.mandatoryValidator("Last Name", txtLastName.getText());
		GPValidator.mandatoryValidator("Username", txtUsername.getText());
		GPValidator.mandatoryValidator("Email", txtEmail.getText());
		GPValidator.mandatoryValidator("Password", txtPassword.getText());
		GPValidator.mandatoryValidator("Phone", txtPhone.getText());
		GPValidator.mandatoryValidator("Manager ID", txtManagerId.getText());

		GPValidator.isAlpha("First Name", txtFirstName.getText());
		GPValidator.isAlpha("Last Name", txtLastName.getText());
		GPValidator.isAlpha("Username", txtUsername.getText());
		GPValidator.isAlpha("Manager ID", txtManagerId.getText());
		GPValidator.isNumber("Phone", txtPhone.getText());

		GPValidator.validateString("Username", txtUsername.getText());

		GPValidator.validateEmail(txtEmail.getText());

		GPValidator.validatePassword(txtPassword.getText());
	}

}

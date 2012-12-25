package com.uic.gpsurveyor.gui.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.uic.gpsurveyor.VO.LoginVO;
import com.uic.gpsurveyor.main.LoginController;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

public class LoginUI extends JFrame {

	private final JPanel contentPane;
	private final JTextField txtLogin;
	private final JPasswordField txtPassword;

	private static Logger logger;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 * @throws IOException
	 * @throws SecurityException
	 */
	public LoginUI() throws SecurityException, IOException {
		setTitle("GreenPower Surveyor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(GPConstants.WINDOW_X, GPConstants.WINDOW_Y, GPConstants.WINDOW_WIDTH, GPConstants.WINDOW_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		setBounds(100,100,520,400);
		setResizable(false);
		JLabel lblScreen = new JLabel("GREEN POWER SURVEYOR",SwingConstants.CENTER);
		lblScreen.setFont((new Font("Calibri", Font.BOLD, 14)));
		lblScreen.setForeground(Color.blue);
		GridBagConstraints gbc_lbScreen = new GridBagConstraints();
		gbc_lbScreen.insets = new Insets(0, 0, 0, 100);
		gbc_lbScreen.gridx = 6;
		gbc_lbScreen.gridy = 1;
		panel.add(lblScreen, gbc_lbScreen);



		JLabel lblLogin = new JLabel("LOGIN");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.fill=GridBagConstraints.LINE_END;
		gbc_lblLogin.insets = new Insets(0, 100, 0, 0);
		gbc_lblLogin.gridx = 5;
		gbc_lblLogin.gridy = 3;
		panel.add(lblLogin, gbc_lblLogin);

		txtLogin = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 0, 200);
		gbc_textField.gridx = 6;
		gbc_textField.gridy = 3;
		panel.add(txtLogin, gbc_textField);
		txtLogin.setColumns(10);

		JLabel lblPassword = new JLabel("PASSWORD");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 100, 0, 0);
		gbc_lblPassword.gridx = 5;
		gbc_lblPassword.gridy = 4;
		panel.add(lblPassword, gbc_lblPassword);

		txtPassword = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill=GridBagConstraints.WEST;
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 0, 200);
		gbc_passwordField.gridx = 6;
		gbc_passwordField.gridy = 4;
		panel.add(txtPassword, gbc_passwordField);

		JButton btnSubmit = new JButton("SUBMIT");
		gbc_passwordField.fill=GridBagConstraints.WEST;
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 100, 0, 0);
		gbc_btnSubmit.gridx = 5;
		gbc_btnSubmit.gridy = 5;
		panel.add(btnSubmit, gbc_btnSubmit);

		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Hashtable ht = new Hashtable();

				try {
					LoginVO objLoginVO = new LoginVO();
					objLoginVO.setUsername(txtLogin.getText());
					objLoginVO.setPassword(txtPassword.getText());
					ht.put("LoginVO", objLoginVO);

					String sUserType = LoginController.execute(ht);
					Scratchpad.ht.put("userId", txtLogin.getText());
					Scratchpad.ht.put("login_user_type", sUserType);
					if(sUserType.equals(GPConstants.USER_TYPE_MANAGER))
					{
						// MANAGER HOME PAGE
						ManagerUI mgui=new ManagerUI();

						mgui.showManagerPage();
						setVisible(false);
						mgui.setVisible(true);
					}
					else
					{
						// SURVEYOR HOME PAGE
						SurveyorUI sgui=new SurveyorUI();

						sgui.showSurveyorPage();
						setVisible(false);
						sgui.setVisible(true);
					}
				} catch (Exception e) {
					if(e.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JButton btnReset = new JButton("RESET");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.fill= GridBagConstraints.EAST;
		gbc_btnReset.insets = new Insets(0, 0, 0, 150);
		gbc_btnReset.gridx = 6;
		gbc_btnReset.gridy = 5;
		panel.add(btnReset, gbc_btnReset);
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtLogin.setText("");
				txtPassword.setText("");
			}
		});
	}

}

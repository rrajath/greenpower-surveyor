package com.uic.gpsurveyor.gui.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.uic.gpsurveyor.gui.inventory.SearchInventoryUI;
import com.uic.gpsurveyor.gui.solar.SearchSolarDataUI;
import com.uic.gpsurveyor.gui.tidal.SearchTidalDataUI;
import com.uic.gpsurveyor.gui.user.SearchUsersUI;
import com.uic.gpsurveyor.gui.wind.SearchWindDataUI;
import com.uic.gpsurveyor.gui.wind.WindReportListUI;
import com.uic.gpsurveyor.service.common.GenerateInitialListService;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

public class ManagerUI extends JFrame {

	private JPanel contentPane;
	public ManagerUI frame;

	/**
	 * Launch the application.
	 */
	public void showManagerPage(){
		setTitle("Manager Home Page");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(GPConstants.WINDOW_X, GPConstants.WINDOW_Y, GPConstants.WINDOW_WIDTH, GPConstants.WINDOW_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 0, 520, 400);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(true);


		JLabel lblGenerateReport = new JLabel("Generate Report");
		lblGenerateReport.setFont((new Font("Calibri", Font.BOLD, 14)));
		lblGenerateReport.setForeground(Color.BLUE);
		lblGenerateReport.setBounds(20, 11, 400, 14);
		panel_1.add(lblGenerateReport);

		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setBounds(385, 11, 100, 20);
		panel_1.add(btnLogout);

		JButton btnWindReport = new JButton("WIND");
		btnWindReport.setBounds(30, 40, 100, 45);
		panel_1.add(btnWindReport);

		JButton btnSolarReport = new JButton("SOLAR");
		btnSolarReport.setBounds(150, 40, 100, 45);
		panel_1.add(btnSolarReport);

		JButton btnTidalReport = new JButton("TIDAL");
		btnTidalReport.setBounds(270, 40, 100, 45);
		panel_1.add(btnTidalReport);


		JLabel lblManageSurvey = new JLabel("Manage Survey");
		lblManageSurvey.setFont((new Font("Calibri", Font.BOLD, 14)));
		lblManageSurvey.setForeground(Color.blue);
		lblManageSurvey.setBounds(20, 130, 130, 14);
		panel_1.add(lblManageSurvey);

		JButton btnWindSurvey = new JButton("WIND");
		btnWindSurvey.setBounds(30, 160, 100, 45);
		panel_1.add(btnWindSurvey);

		btnWindSurvey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				SearchWindDataUI searchWind = new SearchWindDataUI();
				searchWind.showSearchPage();
			}});

		JButton btnSolarSurvey = new JButton("SOLAR");
		btnSolarSurvey.setBounds(150, 160, 100, 45);
		panel_1.add(btnSolarSurvey);

		btnSolarSurvey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				SearchSolarDataUI searchSolar = new SearchSolarDataUI();
				searchSolar.showSearchPage();
			}});


		JButton btnTidalSurvey = new JButton("TIDAL");
		btnTidalSurvey.setBounds(270,160,100, 45);
		panel_1.add(btnTidalSurvey);

		btnTidalSurvey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				SearchTidalDataUI searchTidal = new SearchTidalDataUI();
				searchTidal.showSearchPage();
			}});

		JLabel lblOtherActions = new JLabel("Other Actions");
		lblOtherActions.setFont((new Font("Calibri", Font.BOLD, 14)));
		lblOtherActions.setForeground(Color.blue);
		lblOtherActions.setBounds(20, 230, 120, 14);
		panel_1.add(lblOtherActions);

		JButton btnSettings = new JButton("SETTINGS");
		btnSettings.setBounds(30, 260, 100, 45);
		panel_1.add(btnSettings);

		btnSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				SearchInventoryUI mui = new SearchInventoryUI();
				mui.showSearchPage();
			}});

		JButton btnInitialList = new JButton("INITIAL LIST");
		btnInitialList.setBounds(150, 260, 120, 45);
		panel_1.add(btnInitialList);

		JButton btnCreateUser = new JButton("MANAGE USERS");
		btnCreateUser.setBounds(280,260,150, 45);
		panel_1.add(btnCreateUser);

		JLabel lblNotif = new JLabel("Notification: ");
		lblNotif.setBounds(20, 340, 100, 45);
		panel_1.add(lblNotif);

		String sNotifText = "";
		if(Scratchpad.ht.get("notification")!=null)
			sNotifText = Scratchpad.ht.get("notification").toString();

		JLabel lblNotifText = new JLabel(sNotifText);
		lblNotifText.setBounds(120, 340, 200, 45);
		panel_1.add(lblNotifText);

		Scratchpad.ht.remove("notification");

		btnCreateUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				SearchUsersUI su=new SearchUsersUI();
				su.showSearchPage();
			}});

		btnWindReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				WindReportListUI wui = new WindReportListUI();
				try {
					wui.showWindReport();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}});

		btnInitialList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Hashtable htParams = new Hashtable();

				ArrayList alInitialList = null;
				try {
					alInitialList = GenerateInitialListService.execute(htParams);
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
				setVisible(false);
				Scratchpad.ht.put("LocationsList", alInitialList);
				InitialListUI iw=new InitialListUI();
				try {
					iw.showLocationsList(alInitialList);
				} catch (XPathExpressionException | IOException
						| ParserConfigurationException | SAXException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});

		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				try {
					Scratchpad.ht.clear();
					new LoginUI().setVisible(true);
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}});
	}

}

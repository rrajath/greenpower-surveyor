package com.uic.gpsurveyor.gui.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.uic.gpsurveyor.gui.solar.SearchSolarDataUI;
import com.uic.gpsurveyor.gui.tidal.SearchTidalDataUI;
import com.uic.gpsurveyor.gui.wind.SearchWindDataUI;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

public class SurveyorUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public void showSurveyorPage() {
		setTitle("Surveyor Home Page");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(GPConstants.WINDOW_X, GPConstants.WINDOW_Y, GPConstants.WINDOW_WIDTH, GPConstants.WINDOW_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 0));

		final JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblManageSurvey = new JLabel("Manage Survey");
		lblManageSurvey.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblManageSurvey.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblManageSurvey);

		final JPanel panel = new JPanel();
		contentPane.add(panel);

		JButton btnWind = new JButton("WIND");
		btnWind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(false);
				setVisible(false);
				SearchWindDataUI searchWind = new SearchWindDataUI();
				searchWind.showSearchPage();
			}
		});

		btnWind.setVerticalAlignment(SwingConstants.CENTER);
		btnWind.setPreferredSize(new Dimension(120,50));
		panel.add(btnWind);

		JButton btnSolar = new JButton("SOLAR");
		btnSolar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				panel.setVisible(false);
				panel_1.setVisible(false);
				setVisible(false);
				SearchSolarDataUI searchSolar = new SearchSolarDataUI();
				searchSolar.showSearchPage();
			}
		});
		btnSolar.setPreferredSize(new Dimension(120,50));
		panel.add(btnSolar);

		JButton btnTidal = new JButton("TIDAL");
		btnTidal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(false);
				setVisible(false);
				SearchTidalDataUI searchTidal = new SearchTidalDataUI();
				searchTidal.showSearchPage();
			}
		});
		btnTidal.setPreferredSize(new Dimension(120,50));
		panel.add(btnTidal);

		JButton btnLogout = new JButton("LOGOUT");
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
			}
		});
		btnLogout.setPreferredSize(new Dimension(120,50));
		panel.add(btnLogout);
	}
}

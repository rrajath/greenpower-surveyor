/**
 * 
 */
package com.uic.gpsurveyor.gui.main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.uic.gpsurveyor.VO.LocationsVO;
import com.uic.gpsurveyor.service.common.GetRealTimeDataService;
import com.uic.gpsurveyor.util.GPConstants;

/**
 * @author rajath
 *
 */
public class InitialListUI extends JFrame{

	private JPanel contentPane;
	private DefaultTableModel tableModel;

	private Vector vInitialList;

	/**
	 * Create the frame.
	 * @throws Exception
	 */
	public void showLocationsList(ArrayList alInitialList) throws Exception {
		setTitle("Initial List");
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
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		final JTable table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		//		table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
		panel.add(table);

		tableModel = new DefaultTableModel(new Object[]{"City","Wind Speed"},0);
		table.setModel(tableModel);
		table.setToolTipText("Click on the table header to go back");

		String header[] = {"City","Wind Speed"};
		tableModel.addRow(header);
		vInitialList = new Vector(20);
		Hashtable ht = new Hashtable();
		String cityCode = "";
		String windSpeed = "";

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hashtable ht = new Hashtable();
				if(table.getSelectedRow()==0)
				{
					setVisible(false);
					ManagerUI objManagerUI = new ManagerUI();
					objManagerUI.showManagerPage();
				}
				else;
			}
		});

		for(int i=0;i<alInitialList.size();i++)
		{
			LocationsVO objLocationsVO = new LocationsVO();
			objLocationsVO = (LocationsVO)alInitialList.get(i);
			cityCode = objLocationsVO.getCityCode();
			windSpeed = GetRealTimeDataService.execute(cityCode).get("windSpeed").toString();
			ht.put(cityCode, windSpeed);
			String arr[] = {objLocationsVO.getCityCode(),windSpeed};
			//			vInitialList.add(i, objLocationsVO);
			tableModel.addRow(arr);
		}
	}
}

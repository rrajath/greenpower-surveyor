/**
 * 
 */
package com.uic.gpsurveyor.gui.locations;

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
import com.uic.gpsurveyor.gui.inventory.SearchInventoryUI;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

/**
 * @author rajath
 *
 */
public class LocationListUI extends JFrame{

	private JPanel contentPane;
	private DefaultTableModel tableModel;

	private Vector vLocationList;

	/**
	 * Create the frame.
	 */
	public void showLocationsList(ArrayList alLocationList) {
		setTitle("Location List");
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
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		final JTable table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		//		table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
		panel.add(table);

		tableModel = new DefaultTableModel(new Object[]{"Location ID","City Code","City Name","County", "State"},0);
		table.setModel(tableModel);
		table.setToolTipText("Click on table header to go back");

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hashtable ht = new Hashtable();
				if(table.getSelectedRow()==0)
				{
					setVisible(false);
					SearchInventoryUI objSearchInventoryUI = new SearchInventoryUI();
					objSearchInventoryUI.showSearchPage();
				}
				else
				{
					Scratchpad.ht.put("LocationsVO", vLocationList.get(table.getSelectedRow()-1));
					setVisible(false);
					LocationsUI lui = new LocationsUI();
					lui.showCreateLocationsPage();
				}
			}
		});

		String header[] = {"Location ID","City Code","City Name","County", "State"};
		tableModel.addRow(header);
		vLocationList = new Vector(20);
		for(int i=0;i<alLocationList.size();i++)
		{
			LocationsVO objLocationsVO = new LocationsVO();
			objLocationsVO = (LocationsVO)alLocationList.get(i);
			String arr[] = {String.valueOf(objLocationsVO.getLoc_id()),objLocationsVO.getCityCode(),objLocationsVO.getCityName(),objLocationsVO.getCounty(),objLocationsVO.getState()};
			vLocationList.add(i, objLocationsVO);
			tableModel.addRow(arr);
		}
	}
}

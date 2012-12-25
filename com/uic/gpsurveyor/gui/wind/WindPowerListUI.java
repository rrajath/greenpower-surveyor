/**
 * 
 */
package com.uic.gpsurveyor.gui.wind;

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

import com.uic.gpsurveyor.VO.WindPowerVO;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

/**
 * @author rajath
 *
 */
public class WindPowerListUI extends JFrame{

	private JPanel contentPane;
	private DefaultTableModel tableModel;

	private Vector vWindPowerList;

	/**
	 * Create the frame.
	 */
	public void showWindPowerList(ArrayList alWindPowerList) {
		setTitle("Wind Power List");
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

		tableModel = new DefaultTableModel(new Object[]{"WP ID","Wind Speed","Air Density","Humidity", "Area", "City", "County", "State"},0);
		table.setModel(tableModel);
		table.setToolTipText("Click on the table header to go back");

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hashtable ht = new Hashtable();
				if(table.getSelectedRow()==0)
				{
					setVisible(false);
					SearchWindDataUI objSearchWindDataUI = new SearchWindDataUI();
					objSearchWindDataUI.showSearchPage();
				}
				else
				{
					Scratchpad.ht.put("WindPowerVO", vWindPowerList.get(table.getSelectedRow()-1));
					setVisible(false);
					InsertWindUI iui = new InsertWindUI();
					iui.showInsertWindPage();
				}
			}
		});

		String header[] = {"WP ID","Wind Speed","Air Density","Humidity", "Area", "City", "County", "State"};
		tableModel.addRow(header);
		vWindPowerList = new Vector(20);
		for(int i=0;i<alWindPowerList.size();i++)
		{
			WindPowerVO objWindPowerVO = new WindPowerVO();
			objWindPowerVO = (WindPowerVO)alWindPowerList.get(i);
			String arr[] = {String.valueOf(objWindPowerVO.getWpId()),String.valueOf(objWindPowerVO.getWindSpeed()),String.valueOf(objWindPowerVO.getAirDensity()),String.valueOf(objWindPowerVO.getHumidity()),String.valueOf(objWindPowerVO.getArea()),objWindPowerVO.getCounty(),objWindPowerVO.getCity(),objWindPowerVO.getCounty(),objWindPowerVO.getState()};
			vWindPowerList.add(i, objWindPowerVO);
			tableModel.addRow(arr);
		}
	}
}

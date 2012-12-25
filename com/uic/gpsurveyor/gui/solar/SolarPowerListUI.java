/**
 * 
 */
package com.uic.gpsurveyor.gui.solar;

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

import com.uic.gpsurveyor.VO.SolarPowerVO;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

/**
 * @author rajath
 *
 */
public class SolarPowerListUI extends JFrame{

	private JPanel contentPane;
	private DefaultTableModel tableModel;

	private Vector vSolarPowerList;

	/**
	 * Create the frame.
	 */
	public void showSolarPowerList(ArrayList alSolarPowerList) {
		setTitle("Solar Power List");
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

		tableModel = new DefaultTableModel(new Object[]{"SP ID","Highest Temp","Lowest Temp","Avg Temp", "Cell Count", "Area", "City", "County", "State"},0);
		table.setModel(tableModel);
		table.setToolTipText("Click on the table header to go back");

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hashtable ht = new Hashtable();
				if(table.getSelectedRow()==0)
				{
					setVisible(false);
					SearchSolarDataUI objSearchSolarDataUI = new SearchSolarDataUI();
					objSearchSolarDataUI.showSearchPage();
				}
				else
				{
					Scratchpad.ht.put("SolarPowerVO", vSolarPowerList.get(table.getSelectedRow()-1));
					setVisible(false);
					InsertSolarUI iui = new InsertSolarUI();
					iui.showInsertSolarPage();
				}
			}
		});

		String header[] = {"SP ID","Highest Temp","Lowest Temp","Avg Temp", "Cell Count", "Area", "City", "County", "State"};
		tableModel.addRow(header);
		vSolarPowerList = new Vector(20);
		for(int i=0;i<alSolarPowerList.size();i++)
		{
			SolarPowerVO objSolarPowerVO = new SolarPowerVO();
			objSolarPowerVO = (SolarPowerVO)alSolarPowerList.get(i);
			String arr[] = {String.valueOf(objSolarPowerVO.getSpId()),String.valueOf(objSolarPowerVO.getHighestTemp()),String.valueOf(objSolarPowerVO.getLowestTemp()),String.valueOf(objSolarPowerVO.getAvgTemp()),String.valueOf(objSolarPowerVO.getCellCount()),String.valueOf(objSolarPowerVO.getArea()),objSolarPowerVO.getCounty(),objSolarPowerVO.getCity(),objSolarPowerVO.getCounty(),objSolarPowerVO.getState()};
			vSolarPowerList.add(i, objSolarPowerVO);
			tableModel.addRow(arr);
		}
	}
}

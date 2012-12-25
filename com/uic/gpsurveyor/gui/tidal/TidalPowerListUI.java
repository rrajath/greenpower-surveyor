/**
 * 
 */
package com.uic.gpsurveyor.gui.tidal;

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

import com.uic.gpsurveyor.VO.TidalPowerVO;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

/**
 * @author rajath
 *
 */
public class TidalPowerListUI extends JFrame{

	private JPanel contentPane;
	private DefaultTableModel tableModel;

	private Vector vTidalPowerList;

	/**
	 * Create the frame.
	 */
	public void showTidalPowerList(ArrayList alTidalPowerList) {
		setTitle("Tidal Power List");
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

		tableModel = new DefaultTableModel(new Object[]{"TP ID","Length","Density","Height", "Flow Rate", "City", "County", "State"},0);
		table.setModel(tableModel);
		table.setToolTipText("Click on the table header to go back");

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hashtable ht = new Hashtable();
				if(table.getSelectedRow()==0)
				{
					setVisible(false);
					SearchTidalDataUI objSearchTidalDataUI = new SearchTidalDataUI();
					objSearchTidalDataUI.showSearchPage();
				}
				else
				{
					Scratchpad.ht.put("TidalPowerVO", vTidalPowerList.get(table.getSelectedRow()-1));
					setVisible(false);
					InsertTidalUI itui = new InsertTidalUI();
					itui.showInsertTidalPage();
				}
			}
		});

		String header[] = {"TP ID","Length","Density","Height", "Flow Rate", "City", "County", "State"};
		tableModel.addRow(header);
		vTidalPowerList = new Vector(20);
		for(int i=0;i<alTidalPowerList.size();i++)
		{
			TidalPowerVO objTidalPowerVO = new TidalPowerVO();
			objTidalPowerVO = (TidalPowerVO)alTidalPowerList.get(i);
			String arr[] = {String.valueOf(objTidalPowerVO.getTpId()),String.valueOf(objTidalPowerVO.getLength()),String.valueOf(objTidalPowerVO.getDensity()),String.valueOf(objTidalPowerVO.getHeight()),String.valueOf(objTidalPowerVO.getFlow_rate()),objTidalPowerVO.getCounty(),objTidalPowerVO.getCity(),objTidalPowerVO.getCounty(),objTidalPowerVO.getState()};
			vTidalPowerList.add(i, objTidalPowerVO);
			tableModel.addRow(arr);
		}
	}
}

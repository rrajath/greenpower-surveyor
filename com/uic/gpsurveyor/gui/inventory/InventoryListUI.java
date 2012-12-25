package com.uic.gpsurveyor.gui.inventory;

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

import com.uic.gpsurveyor.VO.CostMaintVO;
import com.uic.gpsurveyor.gui.main.MaintenanceUI;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

public class InventoryListUI extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;

	Vector vCostMaint;
	/**
	 * Create the frame.
	 */
	public void showInventoryList(ArrayList alCostMaint) {
		setTitle("Inventory List");
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

		tableModel = new DefaultTableModel(new Object[]{"Type","Manufacturer","Model Name","Model ID", "Cost", "Service", "Rotor Length"},0);
		table.setModel(tableModel);
		table.setToolTipText("Click on the table header to go back");

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
					Scratchpad.ht.put("CostMaint", vCostMaint.get(table.getSelectedRow()-1));
					setVisible(false);
					MaintenanceUI mui = new MaintenanceUI();
					mui.showSettingsPage();
				}
			}
		});

		String header[] = {"Type","Manufacturer","Model Name","Model ID", "Cost", "Service", "Rotor Length"};
		tableModel.addRow(header);
		for(int i=0;i<alCostMaint.size();i++)
		{
			CostMaintVO objCostMaint = new CostMaintVO();
			objCostMaint = (CostMaintVO)alCostMaint.get(i);
			String arr[] = {objCostMaint.getType(),objCostMaint.getManufacturer(),objCostMaint.getModelName(),objCostMaint.getModel(),Float.toString(objCostMaint.getCost()),Float.toString(objCostMaint.getService()),Float.toString(objCostMaint.getRotorLength())};
			vCostMaint = new Vector();
			vCostMaint.add(i, objCostMaint);
			tableModel.addRow(arr);
		}
	}

}

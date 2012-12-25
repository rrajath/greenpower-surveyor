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

import com.uic.gpsurveyor.DAO.CostMaintDAO;
import com.uic.gpsurveyor.DAO.WindPowerDAO;
import com.uic.gpsurveyor.VO.CostMaintVO;
import com.uic.gpsurveyor.VO.WindPowerVO;
import com.uic.gpsurveyor.connection.Connection;
import com.uic.gpsurveyor.gui.main.ManagerUI;
import com.uic.gpsurveyor.service.wind.WindPowerService;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

/**
 * @author rajath
 *
 */
public class WindReportListUI extends JFrame{

	private JPanel contentPane;
	private DefaultTableModel tableModel;

	Vector vWindList = new Vector(50);
	Vector vCostList = new Vector(50);

	/**
	 * Create the frame.
	 * @throws Exception
	 */
	public void showWindReport() throws Exception {
		setTitle("Wind Report");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(GPConstants.WINDOW_X, GPConstants.WINDOW_Y, GPConstants.WINDOW_WIDTH, GPConstants.WINDOW_HEIGHT);
		setResizable(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		java.sql.Connection conn = new Connection().getConnection();

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		final JTable table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
		panel.add(table);

		tableModel = new DefaultTableModel(new Object[]{"LOCATION","MODEL","TOTAL COST","TOTAL POWER"},0);
		table.setModel(tableModel);
		table.setToolTipText("Click on the table header to go back");

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
				else
				{
					int pos = table.getSelectedRow()-1;
					Scratchpad.ht.put("WindPowerVO", vWindList.get(pos));
					Scratchpad.ht.put("CostMaintVO", vCostList.get(pos));
					setVisible(false);
					DetailedWindReportUI mui = new DetailedWindReportUI();
					try {
						mui.showWindReportPage();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		String header[] = {"LOCATION","MODEL","TOTAL COST","TOTAL POWER"};
		tableModel.addRow(header);

		ArrayList alWindPowerList = new ArrayList();
		WindPowerVO objWindPowerVO = new WindPowerVO();
		WindPowerDAO objWindPowerDAO = new WindPowerDAO();
		alWindPowerList = objWindPowerDAO.search(objWindPowerVO, conn);

		ArrayList alCostMaintList = new ArrayList();
		CostMaintVO objCostMaintVO = new CostMaintVO();
		CostMaintDAO objCostMaintDAO = new CostMaintDAO();

		objCostMaintVO.setType("wind");
		alCostMaintList = objCostMaintDAO.search(objCostMaintVO, conn);

		WindPowerService objWindPowerService = new WindPowerService();
		Hashtable ht = new Hashtable();

		float fTotalPower = 0.0f;
		float fTotalCost = 0.0f;

		int count = 0;

		for(int i=0;i<alWindPowerList.size();i++)
		{
			objWindPowerVO = (WindPowerVO)alWindPowerList.get(i);

			for(int j=0;j<alCostMaintList.size();j++)
			{
				ht = new Hashtable();

				objCostMaintVO = (CostMaintVO)alCostMaintList.get(j);
				ht.put("WindPowerVO", objWindPowerVO);
				ht.put("CostMaintVO", objCostMaintVO);

				fTotalPower = objWindPowerService.computeWindPower(ht);
				fTotalCost = objWindPowerService.computeWindCost(ht);

				String arr[] = {objWindPowerVO.getCity(), objCostMaintVO.getModelName(), String.valueOf(fTotalPower), String.valueOf(fTotalCost)};
				vWindList.add(count,objWindPowerVO);
				vCostList.add(count, objCostMaintVO);
				count = count + 1;
				tableModel.addRow(arr);

				ht.remove("WindPowerVO");
				ht.remove("CostMaintVO");
			}
		}
	}
}

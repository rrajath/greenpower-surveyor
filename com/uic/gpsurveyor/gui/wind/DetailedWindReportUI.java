/**
 * 
 */
package com.uic.gpsurveyor.gui.wind;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.uic.gpsurveyor.VO.CostMaintVO;
import com.uic.gpsurveyor.VO.WindPowerVO;
import com.uic.gpsurveyor.gui.main.ManagerUI;
import com.uic.gpsurveyor.gui.main.SurveyorUI;
import com.uic.gpsurveyor.service.wind.InsertWindDataService;
import com.uic.gpsurveyor.service.wind.WindPowerService;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

/**
 * @author rajath
 *
 */
public class DetailedWindReportUI extends JFrame{

	private JPanel contentPane;
	private JTextField txtWindSpeed;
	private JTextField txtAirDensity;
	private JTextField txtHumidity;
	private JTextField txtArea;
	private JTextField txtCity;
	private JTextField txtCounty;
	private JTextField txtState;

	private final boolean bSubmitFlag = true;

	/**
	 * Create the frame.
	 * @throws Exception
	 */
	public void showWindReportPage() throws Exception {
		setTitle("Detailed Wind Report");
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
		panel.setLayout(new GridLayout(0, 2, 20, 6));

		JButton btnBack = new JButton("BACK");
		panel.add(btnBack);

		JLabel lblDummy = new JLabel("");
		panel.add(lblDummy);

		WindPowerVO objWindPowerVO = new WindPowerVO();
		objWindPowerVO = (WindPowerVO)Scratchpad.ht.get("WindPowerVO");

		CostMaintVO objCostMaintVO = new CostMaintVO();
		objCostMaintVO = (CostMaintVO)Scratchpad.ht.get("CostMaintVO");

		JLabel lblLocation = new JLabel("CITY");
		panel.add(lblLocation);

		JLabel lblLocationValue = new JLabel(objWindPowerVO.getCity());
		panel.add(lblLocationValue);

		JLabel lblState = new JLabel("STATE");
		panel.add(lblState);

		JLabel lblStateValue = new JLabel(objWindPowerVO.getState());
		panel.add(lblStateValue);

		JLabel lblWindSpeed = new JLabel("WIND SPEED");
		panel.add(lblWindSpeed);

		JLabel lblWindSpeedValue = new JLabel(String.valueOf(objWindPowerVO.getWindSpeed()));
		panel.add(lblWindSpeedValue);

		JLabel lblAirDensity = new JLabel("AIR DENSITY");
		panel.add(lblAirDensity);

		JLabel lblAirDensityValue = new JLabel(String.valueOf(objWindPowerVO.getAirDensity()));
		panel.add(lblAirDensityValue);

		JLabel lblHumidity = new JLabel("HUMIDITY");
		panel.add(lblHumidity);

		JLabel lblHumidityValue = new JLabel(String.valueOf(objWindPowerVO.getHumidity()));
		panel.add(lblHumidityValue);

		JLabel lblArea = new JLabel("AREA (in sq.miles)");
		panel.add(lblArea);

		JLabel lblAreaValue = new JLabel(String.valueOf(objWindPowerVO.getArea()));
		panel.add(lblAreaValue);

		JLabel lblModel = new JLabel("MODEL");
		panel.add(lblModel);

		JLabel lblModelValue = new JLabel(objCostMaintVO.getModelName());
		panel.add(lblModelValue);

		JLabel lblTotalPower = new JLabel("TOTAL POWER");
		panel.add(lblTotalPower);

		Hashtable ht = new Hashtable();
		ht.put("WindPowerVO", objWindPowerVO);
		ht.put("CostMaintVO", objCostMaintVO);

		float fTotalPower = new WindPowerService().computeWindPower(ht);
		float fTotalCost = new WindPowerService().computeWindCost(ht);
		JLabel lblTotalPowerValue = new JLabel(String.valueOf(fTotalPower));
		panel.add(lblTotalPowerValue);

		JLabel lblNoOfWindmills = new JLabel("No. of Windmills");
		panel.add(lblNoOfWindmills);

		JLabel lblNoOfWindmillsValue = new JLabel(Scratchpad.ht.get("NoOfWindmills").toString());
		panel.add(lblNoOfWindmillsValue);

		JLabel lblTotalCost = new JLabel("TOTAL COST");
		panel.add(lblTotalCost);

		JLabel lblTotalCostValue = new JLabel(String.valueOf(fTotalCost));
		panel.add(lblTotalCostValue);

		JButton btnSubmit = new JButton("SUBMIT");

		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Hashtable ht = new Hashtable();

				WindPowerVO objWindPowerVO = new WindPowerVO();

				objWindPowerVO.setWindSpeed(Float.parseFloat(txtWindSpeed.getText()));
				objWindPowerVO.setAirDensity(Float.parseFloat(txtAirDensity.getText()));
				objWindPowerVO.setHumidity(Float.parseFloat(txtHumidity.getText()));
				objWindPowerVO.setArea(Float.parseFloat(txtArea.getText()));
				objWindPowerVO.setCity(txtCity.getText());
				objWindPowerVO.setCounty(txtCounty.getText());
				objWindPowerVO.setState(txtState.getText());

				ht.put("WindPowerVO", objWindPowerVO);

				try {
					InsertWindDataService.execute(ht);
				} catch (ClassNotFoundException | SQLException e) {
					if(e.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					if(e.getMessage()==null)
						JOptionPane.showMessageDialog(null, "Unknown Error. Check log for details");
					else
						JOptionPane.showMessageDialog(null, e.getMessage());
				}
				setVisible(false);

				if(Scratchpad.ht.get("login_user_type").equals(GPConstants.USER_TYPE_MANAGER))
				{
					ManagerUI mui = new ManagerUI();
					mui.showManagerPage();
				}
				else
				{
					SurveyorUI sui = new SurveyorUI();
					sui.showSurveyorPage();
				}
			}
		});

		// Back button. Takes back to Surveyor home page.
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				setVisible(false);
				/*SearchWindDataUI sui = new SearchWindDataUI();
				sui.showSearchPage();*/
				WindReportListUI objWindReportListUI = new WindReportListUI();
				try {
					objWindReportListUI.showWindReport();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}

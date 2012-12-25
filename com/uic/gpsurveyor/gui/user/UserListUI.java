/**
 * 
 */
package com.uic.gpsurveyor.gui.user;

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

import com.uic.gpsurveyor.VO.UserInfoVO;
import com.uic.gpsurveyor.util.GPConstants;
import com.uic.gpsurveyor.util.Scratchpad;

/**
 * @author rajath
 *
 */
public class UserListUI extends JFrame{

	private JPanel contentPane;
	private DefaultTableModel tableModel;

	private Vector vUserList;

	/**
	 * Create the frame.
	 */
	public void showUserList(ArrayList alUserList) {
		setTitle("User List");
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

		tableModel = new DefaultTableModel(new Object[]{"Username","First Name","Last Name","Phone", "Email", "User Type", "Manager ID"},0);
		table.setModel(tableModel);
		table.setToolTipText("Click on the table header to go back");

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hashtable ht = new Hashtable();
				if(table.getSelectedRow()==0)
				{
					setVisible(false);
					SearchUsersUI objSearchUsersUI = new SearchUsersUI();
					objSearchUsersUI.showSearchPage();
				}
				else
				{
					Scratchpad.ht.put("UserInfoVO", vUserList.get(table.getSelectedRow()-1));
					setVisible(false);
					CreateUserUI cui = new CreateUserUI();
					cui.showCreateUser();
				}
			}
		});

		String header[] = {"Username","First Name","Last Name","Phone", "Email", "User Type", "Manager ID"};
		tableModel.addRow(header);
		vUserList = new Vector(20);
		for(int i=0;i<alUserList.size();i++)
		{
			UserInfoVO objUserInfoVO = new UserInfoVO();
			objUserInfoVO = (UserInfoVO)alUserList.get(i);
			String arr[] = {objUserInfoVO.getUsername(),objUserInfoVO.getFirstName(),objUserInfoVO.getLastName(),objUserInfoVO.getPhone(),objUserInfoVO.getEmail(),objUserInfoVO.getUserType(),objUserInfoVO.getManagerId()};
			vUserList.add(i, objUserInfoVO);
			tableModel.addRow(arr);
		}
	}
}

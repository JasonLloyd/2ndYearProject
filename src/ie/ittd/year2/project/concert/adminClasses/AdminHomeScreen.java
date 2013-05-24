package ie.ittd.year2.project.concert.adminClasses;

import ie.ittd.year2.project.concert.dbFiles.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class AdminHomeScreen 
{
	confirmationScreens x = new confirmationScreens();
	dbConnect2 db = new dbConnect2();
	theHandler handler = new theHandler();
	
	
	
	private JButton addAccount  = new JButton("Add Account");
	private JButton addConcert = new JButton("Create New Concert");
	private JButton deleteConcert  = new JButton("Delete Concert");
	private JButton updateConcert  = new JButton("Update Concert");
	private JButton viewUsers  = new JButton("View Users");
	private JButton viewAllPurchases  = new JButton("View All Purchases");
	private JButton systemInfo  = new JButton("System Information");
	private JButton logout  = new JButton("logout");
	private JButton exit  = new JButton("Exit");
	
	private GridBagLayout gbl = new GridBagLayout();

	public JFrame adminHomeScreenFrame = new JFrame();

	private JPanel panel1 = new JPanel(gbl);
	private JPanel panel2 = new JPanel();
	
	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,25);
	
	private GridBagConstraints c = new GridBagConstraints();
	
	public AdminHomeScreen(String title)
	{
		JLabel username = new JLabel("Welcome " + title);
		
		adminHomeScreenFrame.setTitle("Welcome Administrator what would you like to do?");
		adminHomeScreenFrame.setSize(550,550);
		adminHomeScreenFrame.setLocation(500, 250);
		adminHomeScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminHomeScreenFrame.getContentPane().setLayout(new BorderLayout());
		adminHomeScreenFrame.setVisible(true);

		adminHomeScreenFrame.add(panel1,BorderLayout.CENTER);
		adminHomeScreenFrame.add(panel2,BorderLayout.NORTH);

		panel1.setBorder(new LineBorder(Color.BLACK));
		panel2.setBorder(new LineBorder(Color.BLACK));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel1.setBackground(Color.white);
		panel2.setBackground(Color.white);
		
		username.setFont(f2);
		panel2.add(username);

		c.insets = new Insets(20,60,0,60);
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 40;
		c.ipadx = 20;
		c.fill = GridBagConstraints.HORIZONTAL;

		panel1.add(addAccount,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(updateConcert,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		panel1.add(addConcert,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(deleteConcert,c);

		c.insets = new Insets(20,60,0,60);
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(viewUsers,c);	

		c.insets = new Insets(20,0,0,0);
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(viewAllPurchases,c);

		c.insets = new Insets(20,60,0,60);
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(systemInfo,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(logout,c);

		c.insets = new Insets(20,60,0,60);
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(exit,c);
		
		addAccount.addActionListener(handler);
		addConcert.addActionListener(handler);
		deleteConcert.addActionListener(handler);
		updateConcert.addActionListener(handler);
		viewUsers.addActionListener(handler);
		viewAllPurchases.addActionListener(handler);
		systemInfo.addActionListener(handler);
		logout.addActionListener(handler);
		exit.addActionListener(handler);
	}
	
	private class theHandler implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{
			JButton pushedButton = (JButton) e.getSource();
			
			if(pushedButton.equals(addAccount))
			{
				addAdminAccount x = new addAdminAccount();
				adminHomeScreenFrame.setVisible(false);
			}
			
			if(pushedButton.equals(addConcert))
			{
				CreateNewConcert x = new CreateNewConcert();
				adminHomeScreenFrame.setVisible(false);
			}
			if(pushedButton.equals(deleteConcert))
			{
				DeleteConcert x = new DeleteConcert();
				adminHomeScreenFrame.setVisible(false);
			}
			
			if(pushedButton.equals(updateConcert))
			{
				UpdateConcert x = new UpdateConcert();
				adminHomeScreenFrame.setVisible(false);
			}
			
			if(pushedButton.equals(viewUsers))
			{
				DeleteUser x = new DeleteUser();
				adminHomeScreenFrame.setVisible(false);
			}
			
			if(pushedButton.equals(viewAllPurchases))
			{
				try {
					viewAllPurchases x = new viewAllPurchases();
				} catch (SQLException e1) {
					//only here in the case of a database problem
					System.out.println("Cannot Open Purchases");
				}
				adminHomeScreenFrame.setVisible(false);
			}
			
			if(pushedButton.equals(systemInfo))
			{
				try {
					SystemInformation x = new SystemInformation();
				} catch (SQLException e1) {
					//only here in the case of a database problem
					System.out.println("Cannot open system information");
				}
				adminHomeScreenFrame.setVisible(false);
			}
			
			if(pushedButton.equals(logout))
			{
				adminHomeScreenFrame.dispose();
				LoginPage x = new LoginPage();
			}
			
			if(pushedButton.equals(exit))
			{
				x.logoutConfirmation();
				System.exit(0);
			}
		}
		
	}
}

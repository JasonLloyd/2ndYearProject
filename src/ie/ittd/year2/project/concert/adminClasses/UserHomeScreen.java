package ie.ittd.year2.project.concert.adminClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ie.ittd.year2.project.concert.userClasses.*;

import ie.ittd.year2.project.concert.dbFiles.*;

public class UserHomeScreen 
{
	theHandler handler = new theHandler();
	ResultSet rset;
	dbConnect2 db = new dbConnect2();


	public JFrame userHomeScreenFrame = new JFrame();

	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,25);

	private JButton viewConcerts = new JButton("View Concerts");
	private JButton upcomingConcerts = new JButton("Upcoming Concerts");
	private JButton accountInfo = new JButton("Account information");
	private JButton recentPurchases = new JButton("Recent Purchases");
	private JButton feedBack = new JButton("Submit Feedback");
	private JButton contactUs = new JButton("Contact us");
	private JButton logout  = new JButton("Logout");
	private JButton exit  = new JButton("Exit");

	private GridBagConstraints c = new GridBagConstraints();

	private JPanel panel5 = new JPanel(new GridBagLayout());
	private JPanel panel6 = new JPanel();


	public UserHomeScreen(String title)
	{
		
		JLabel title2 = new JLabel("Welcome " + title);	
		userHomeScreenFrame.setTitle("Welcome to the Concert System:");
		userHomeScreenFrame.setSize(550,450);
		userHomeScreenFrame.setLocation(500, 250);
		userHomeScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userHomeScreenFrame.getContentPane().setLayout(new BorderLayout());
		userHomeScreenFrame.setVisible(true);

		userHomeScreenFrame.add(panel5,BorderLayout.CENTER);
		userHomeScreenFrame.add(panel6,BorderLayout.NORTH);

		panel5.setBackground(Color.WHITE);
		panel6.setBackground(Color.WHITE);

		panel5.setBorder(new LineBorder(Color.BLACK));
		panel6.setBorder(new LineBorder(Color.BLACK));
		panel6.setLayout(new FlowLayout(FlowLayout.CENTER));
		title2.setFont(f2);
		panel6.add(title2);

		c.insets = new Insets(20,60,0,60);
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 40;
		c.ipadx = 20;
		panel5.add(upcomingConcerts,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel5.add(viewConcerts,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		panel5.add(accountInfo,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel5.add(recentPurchases,c);

		c.insets = new Insets(20,60,0,60);
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel5.add(feedBack,c);	

		c.insets = new Insets(20,0,0,0);
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel5.add(contactUs,c);

		c.insets = new Insets(20,60,0,60);
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel5.add(logout,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel5.add(exit,c);


		upcomingConcerts.addActionListener(handler);
		viewConcerts.addActionListener(handler);
		accountInfo.addActionListener(handler);
		recentPurchases.addActionListener(handler);
		contactUs.addActionListener(handler);
		feedBack.addActionListener(handler);
		logout.addActionListener(handler);
		exit.addActionListener(handler);
	}


	private class theHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{

			JButton pushed = (JButton) e.getSource();
			if( pushed.equals(upcomingConcerts))
			{
				try {
					upcomingConcerts x = new upcomingConcerts();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				userHomeScreenFrame.setVisible(false);
			}

			if(pushed.equals(viewConcerts))
			{
				allConcerts x = new allConcerts();
				userHomeScreenFrame.setVisible(false);
			}

			if(pushed.equals(accountInfo))
			{
				try {
					accountInformation x = new accountInformation();
				} catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				userHomeScreenFrame.setVisible(false);
			}

			if(pushed.equals(recentPurchases))
			{
				try {
					recentPurchases x = new recentPurchases();
				} catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				userHomeScreenFrame.setVisible(false);
			}

			if(pushed.equals(feedBack))
			{
				submitFeedback x = new submitFeedback();
			}
			if(pushed.equals(contactUs))
			{
				contactUsScreen x = new contactUsScreen();
				userHomeScreenFrame.setVisible(false);
			}

			if(pushed.equals(logout))
			{
				userHomeScreenFrame.dispose();
				LoginPage x = new LoginPage();
			}
			if(pushed.equals(exit))
				System.exit(0);
		}

	}
}

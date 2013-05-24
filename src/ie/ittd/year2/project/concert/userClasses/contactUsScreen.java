package ie.ittd.year2.project.concert.userClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ie.ittd.year2.project.concert.adminClasses.*;

import ie.ittd.year2.project.concert.dbFiles.*;

public class contactUsScreen 
{
	private GridBagLayout gbl = new GridBagLayout();
	
	private JFrame contactUsFrame = new JFrame();
	
	private JPanel panel1 = new JPanel(gbl);
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	
	JLabel Admins = new JLabel("Administrators");
	JLabel admin1 = new JLabel("Jason");
	JLabel admin2 = new JLabel("Keith");
	JLabel admin3 = new JLabel("Ashish");
	JLabel admin4 = new JLabel("Karl ");

	JLabel Email1 = new JLabel("Jasons Email:");
	JLabel Email2 = new JLabel("Keith Email:");
	JLabel Email3 = new JLabel("Ashish Email:");
	JLabel Email4 = new JLabel("Karls Email:");

	JLabel admin1Email = new JLabel("admin1@test.ie");
	JLabel admin2Email = new JLabel("admin2@test.ie");
	JLabel admin3Email= new JLabel("admin3@test.ie");
	JLabel admin4Email = new JLabel("admin4@test.ie");

	JButton back = new JButton("Back");

	JLabel title = new JLabel("Contact Us");
	
	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,25);
	private Font f3 = new Font("Roman",Font.BOLD,20);
	private Font writingFont = new Font("Roman",Font.ITALIC,16);
	
	GridBagConstraints c = new GridBagConstraints();

	theHandler myHandler = new theHandler();
	
	public contactUsScreen()
	{
		JLabel Admins = new JLabel("Administrators");
		JLabel admin1 = new JLabel("Jason");
		JLabel admin2 = new JLabel("Keith");
		JLabel admin3 = new JLabel("Ashish");
		JLabel admin4 = new JLabel("Karl ");

		JLabel Email1 = new JLabel("Jasons Email:");
		JLabel Email2 = new JLabel("Keith Email:");
		JLabel Email3 = new JLabel("Ashish Email:");
		JLabel Email4 = new JLabel("Karls Email:");

		JLabel admin1Email = new JLabel("admin1@test.ie");
		JLabel admin2Email = new JLabel("admin2@test.ie");
		JLabel admin3Email= new JLabel("admin3@test.ie");
		JLabel admin4Email = new JLabel("admin4@test.ie");

		JLabel title = new JLabel("Contact Us");

		contactUsFrame.setTitle("Concert System: Contact Us");
		contactUsFrame.setSize(550,425);
		contactUsFrame.setLocation(500, 250);
		contactUsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contactUsFrame.getContentPane().setLayout(new BorderLayout());
		contactUsFrame.getContentPane().setBackground(Color.WHITE);
		contactUsFrame.setVisible(true);


		contactUsFrame.add(panel1,BorderLayout.WEST);
		contactUsFrame.add(panel2,BorderLayout.NORTH);
		contactUsFrame.add(panel3,BorderLayout.SOUTH);
		
		panel1.setBackground(Color.WHITE);
		panel2.setBackground(Color.WHITE);
		panel3.setBackground(Color.WHITE);

		title.setFont(f2);
		panel2.add(title);

		Admins.setFont(f3);
		c.insets = new Insets(20,20,0,0);
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(Admins,c);

		admin1.setFont(writingFont);
		c.insets = new Insets(20,60,0,0);
		c.gridx = 0;
		c.gridy = 1;
		panel1.add(admin1,c);

		Email1.setFont(writingFont);
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0;
		c.gridy = 2;
		panel1.add(Email1,c);

		admin1Email.setFont(writingFont);
		c.insets = new Insets(0,0,0,0);
		c.gridx = 1;
		c.gridy = 2;
		panel1.add(admin1Email,c);

		admin2.setFont(writingFont);
		c.insets = new Insets(20,60,0,0);
		c.gridx = 0;
		c.gridy = 3;
		panel1.add(admin2,c);

		Email2.setFont(writingFont);
		c.insets = new Insets(0,20,0,0);
		c.gridx = 0;
		c.gridy = 4;
		panel1.add(Email2,c);

		admin2Email.setFont(writingFont);
		c.insets = new Insets(0,0,0,0);
		c.gridx = 1;
		c.gridy = 4;
		panel1.add(admin2Email,c);

		admin3.setFont(writingFont);
		c.insets = new Insets(20,60,0,0);
		c.gridx = 0;
		c.gridy = 5;
		panel1.add(admin3,c);

		Email3.setFont(writingFont);
		c.insets = new Insets(0,20,0,0);
		c.gridx = 0;
		c.gridy = 6;
		panel1.add(Email3,c);

		admin3Email.setFont(writingFont);
		c.insets = new Insets(0,0,0,0);
		c.gridx = 1;
		c.gridy = 6;
		panel1.add(admin3Email,c);

		admin4.setFont(writingFont);
		c.insets = new Insets(20,60,0,0);
		c.gridx = 0;
		c.gridy = 7;
		panel1.add(admin4,c);

		Email4.setFont(writingFont);
		c.insets = new Insets(0,20,70,0);
		c.gridx = 0;
		c.gridy = 8;
		panel1.add(Email4,c);

		admin4Email.setFont(writingFont);
		c.insets = new Insets(0,0,70,0);
		c.gridx = 1;
		c.gridy = 8;
		panel1.add(admin4Email,c);

		panel3.add(back);
		
		back.addActionListener(myHandler);
	}
	
	private class theHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{	
			JButton pushed = (JButton) e.getSource();
			
			if(pushed.equals(back))
			{
				contactUsFrame.setVisible(false);
				UserHomeScreen x = new UserHomeScreen(LoginPage.usernameCopy);
			}
		}
		
	}
	
}

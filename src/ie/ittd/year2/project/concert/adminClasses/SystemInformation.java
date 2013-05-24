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

import ie.ittd.year2.project.concert.dbFiles.*;

public class SystemInformation 
{
	ResultSet rset;
	dbConnect2 db = new dbConnect2();

	private GridBagLayout gbl = new GridBagLayout();

	private JPanel panel1 = new JPanel(gbl);
	private JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));

	private JFrame systemInformationFrame = new JFrame();

	private JLabel noOfUsers = new JLabel("No# of Users:");
	private JLabel noOfConcerts = new JLabel("No# of Concerts:");
	private JLabel noOfBooking = new JLabel("No# of Bookings:");
	private JLabel amountOfMoney =  new JLabel("Money Made:");

	private JLabel usersValue = new JLabel("1");
	private JLabel concertsValue = new JLabel("2");
	private JLabel bookingValue = new JLabel("3");
	private JLabel moneyValue = new JLabel("4");

	private JLabel title = new JLabel("System Information");

	private JButton back = new JButton("Back");
	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,20);
	private Font f3 = new Font("Roman",Font.BOLD + Font.ITALIC,40);

	private GridBagConstraints c = new GridBagConstraints();

	public SystemInformation() throws SQLException
	{
		db.connect();
		systemInformationFrame.setTitle("System Information");
		systemInformationFrame.setSize(520,400);
		systemInformationFrame.setLocation(500, 250);
		systemInformationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		systemInformationFrame.getContentPane().setLayout(new BorderLayout());
		systemInformationFrame.getContentPane().setBackground(Color.white);
		systemInformationFrame.setVisible(true);

		systemInformationFrame.add(panel1,BorderLayout.WEST);
		systemInformationFrame.add(panel2,BorderLayout.SOUTH);
		systemInformationFrame.add(panel3,BorderLayout.NORTH);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		panel1.setBackground(Color.white);
		panel2.setBackground(Color.white);
		panel3.setBackground(Color.white);

		title.setFont(f3);
		panel3.add(title);

		noOfUsers.setFont(f2);
		c.insets = new Insets(20,70,0,0);
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(noOfUsers,c);

		usersValue.setFont(f2);
		
		rset = db.countUsers();

		rset.next();
		
		String amountOfUsers = Integer.toString(rset.getInt(1));
		usersValue.setText(amountOfUsers);
		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 0;
		panel1.add(usersValue,c);

		noOfConcerts.setFont(f2);
		c.insets = new Insets(20,35,0,0);
		c.gridx = 0;
		c.gridy = 1;
		panel1.add(noOfConcerts,c);

		concertsValue.setFont(f2);
		rset = db.countConcerts();

		rset.next();
		String amountOfConcerts = Integer.toString(rset.getInt(1));
		concertsValue.setText(amountOfConcerts);
		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 1;
		panel1.add(concertsValue,c);

		noOfBooking.setFont(f2);
		c.insets = new Insets(20,25,0,0);
		c.gridx = 0;
		c.gridy = 2;
		panel1.add(noOfBooking,c);
		
		bookingValue.setFont(f2);
		rset = db.countBooking();
		
		rset.next();

		String amountOfBookings = Integer.toString(rset.getInt(1));
		bookingValue.setText(amountOfBookings);
		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 2;
		panel1.add(bookingValue,c);

		amountOfMoney.setFont(f2);
		c.insets = new Insets(20,55,0,0);
		c.gridx = 0;
		c.gridy = 3;
		panel1.add(amountOfMoney,c);

		moneyValue.setFont(f2);
		String money = Double.toString(rset.getDouble(2));
		moneyValue.setText(money);
		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 3;
		panel1.add(moneyValue,c);

		theHandler myHandler = new theHandler();

		panel2.add(back);
		back.addActionListener(myHandler);
	}

	private class theHandler implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{
			JButton pushed = (JButton) e.getSource();

			if(pushed.equals(back))
			{
				db.closeDB();
				AdminHomeScreen x = new AdminHomeScreen(LoginPage.usernameCopy);
				systemInformationFrame.setVisible(false);
			}
		}

	}
}

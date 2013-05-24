package ie.ittd.year2.project.concert.userClasses;

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
import java.util.Date;

import javax.swing.*;

import ie.ittd.year2.project.concert.adminClasses.*;

import ie.ittd.year2.project.concert.dbFiles.*;


public class recentPurchases 
{
	theHandler myHandler = new theHandler();
	dbConnect2 db = new dbConnect2();
	ResultSet rset;

	private GridBagLayout gbl = new GridBagLayout();

	private JFrame recentPurchasesFrame = new JFrame();

	private JPanel panel1 = new JPanel(gbl);
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();

	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,25);
	private Font f3 = new Font("Roman",Font.BOLD,20);

	private JLabel title = new JLabel("Recent Purchases");
	private JLabel number = new JLabel("No#");
	private JLabel concertName = new JLabel("Concert Name");
	private JLabel concertDate = new JLabel("Concert Date");
	private JLabel noOfTickets = new JLabel("Ticket No#");
	private JLabel concertCost = new JLabel("Concert Cost");

	private JButton back = new JButton("Back");

	private GridBagConstraints c = new GridBagConstraints();

	public recentPurchases() throws SQLException
	{
		db.connect();
		recentPurchasesFrame.setTitle("Concert System: Recent Purchases");
		recentPurchasesFrame.setSize(700,450);
		recentPurchasesFrame.setLocation(500, 250);
		recentPurchasesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		recentPurchasesFrame.getContentPane().setLayout(new BorderLayout());
		recentPurchasesFrame.setVisible(true);

		recentPurchasesFrame.add(panel1,BorderLayout.CENTER);
		recentPurchasesFrame.add(panel2,BorderLayout.NORTH);
		recentPurchasesFrame.add(panel3,BorderLayout.SOUTH);

		panel1.setBackground(Color.white);
		panel2.setBackground(Color.white);
		panel3.setBackground(Color.white);

		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));

		title.setFont(f2);
		panel2.add(title);

		JLabel numberLabels = new JLabel();

		JLabel nameLabels = new JLabel();
		JLabel dateLabels = new JLabel();
		JLabel costLabels = new JLabel();
		JLabel ticketLabels = new JLabel();

		String username = LoginPage.usernameCopy;
		rset = db.getUserPurchases(username);

		int concertCode = 0;

		int i = 0;
		int z = 0;

		ResultSet rset2;
		ResultSet rset3;
		
		do
		{
			rset = db.getUserPurchases(username);
			while(rset.next())
			{
//				rset.next();

				numberLabels=new JLabel((z + 1) + ".");
				c.insets = new Insets(0,15,10,0);
				c.gridx = 0;
				c.gridy = i + 1;
				panel1.add(numberLabels,c);

				concertCode = rset.getInt(2);
				
				rset2 = db.getConcertsToChange(concertCode);

				nameLabels=new JLabel(rset2.getString(2));
				c.insets = new Insets(0,15,10,0);
				c.gridx = 1;
				c.gridy = z + 1;
				panel1.add(nameLabels,c);
				
				Date d1 = rset2.getDate(5);

				String date = d1.toString();

				dateLabels=new JLabel(date);
				c.insets = new Insets(0,15,10,0);
				c.gridx = 2;
				c.gridy = z + 1;
				panel1.add(dateLabels,c);
				
				rset2.next();

				rset3 = db.getUserPurchases2(username,concertCode);
				rset3.next();
				
				int ticketNo = rset3.getInt(4);
				
				String ticketNoLabel = Integer.toString(ticketNo);
				
				ticketLabels=new JLabel(ticketNoLabel);
				c.insets = new Insets(0,15,10,0);
				c.gridx = 3;
				c.gridy = z + 1;
				panel1.add(ticketLabels,c);	
				
				String cost = Double.toString(rset3.getDouble(6));

				costLabels=new JLabel(cost);
				c.insets = new Insets(0,15,10,0);
				c.gridx = 4;
				c.gridy = z + 1;
				panel1.add(costLabels,c);	
				
				z++;
				i++;
			}
			rset.next();
		}while(rset.next());

		number.setFont(f3);
		c.insets = new Insets(20,20,10,20);
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(number,c);

		concertName.setFont(f3);
		c.insets = new Insets(20,20,10,20);
		c.gridx = 1;
		c.gridy = 0;
		panel1.add(concertName,c);

		concertDate.setFont(f3);
		c.insets = new Insets(20,20,10,20);
		c.gridx = 2;
		c.gridy = 0;
		panel1.add(concertDate,c);
		
		noOfTickets.setFont(f3);
		c.insets = new Insets(20,20,10,20);
		c.gridx = 3;
		c.gridy = 0;
		panel1.add(noOfTickets,c);

		concertCost.setFont(f3);
		c.insets = new Insets(20,20,10,20);
		c.gridx = 4;
		c.gridy = 0;
		panel1.add(concertCost,c);

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
				db.closeDB();
				UserHomeScreen x = new UserHomeScreen(LoginPage.usernameCopy);
				recentPurchasesFrame.setVisible(false);
			}
		}

	}
}

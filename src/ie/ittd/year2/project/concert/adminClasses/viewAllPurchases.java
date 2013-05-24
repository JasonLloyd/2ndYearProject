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
import javax.swing.JScrollPane;

import ie.ittd.year2.project.concert.dbFiles.*;

public class viewAllPurchases 
{

	dbConnect2 db = new dbConnect2();
	ResultSet rset;

	private GridBagLayout gbl = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();

	private JLabel viewAllpurchasesTitle = new JLabel("View all purchases");
	private JLabel purchaseCode = new JLabel("Purchase Code");
	private JLabel purchaseUsername = new JLabel("Username");
	private JLabel purchaseConcertCode = new JLabel("Concert Code");
	private JLabel purchaseTotal = new JLabel("Purchase Total");

	private JButton back = new JButton("Back");
	private JFrame viewAllPurchasesFrame = new JFrame();

	private JPanel panel1 = new JPanel(gbl);
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();


	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,25);
	private Font f3 = new Font("Roman",Font.BOLD,20);
	
	


	public viewAllPurchases() throws SQLException
	{
		db.connect();
		JScrollPane scrollPane = new JScrollPane();
		
		viewAllPurchasesFrame.setTitle("All Purchases");
		viewAllPurchasesFrame.setSize(750,600);
		viewAllPurchasesFrame.setLocation(500, 250);
		viewAllPurchasesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewAllPurchasesFrame.getContentPane().setLayout(new BorderLayout());
		viewAllPurchasesFrame.setVisible(true);

		viewAllPurchasesFrame.add(panel1,BorderLayout.CENTER);
		viewAllPurchasesFrame.add(panel2,BorderLayout.NORTH);
		viewAllPurchasesFrame.add(panel3,BorderLayout.SOUTH);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		panel1.setBackground(Color.white);
		panel2.setBackground(Color.white);
		panel3.setBackground(Color.white);

		viewAllpurchasesTitle.setFont(f2);
		panel2.add(viewAllpurchasesTitle);

		purchaseCode.setFont(f3);
		c.insets = new Insets(0,40,0,0);
		c.gridx = 1;
		c.gridy = 0;
		panel1.add(purchaseCode,c);

		purchaseConcertCode.setFont(f3);
		c.gridx = 2;
		c.gridy = 0;
		panel1.add(purchaseConcertCode,c);

		purchaseUsername.setFont(f3);
		c.gridx = 3;
		c.gridy = 0;
		panel1.add(purchaseUsername,c);

		purchaseTotal.setFont(f3);
		c.gridx = 4;
		c.gridy = 0;
		panel1.add(purchaseTotal,c);

		JLabel purchaseCodeLabels = new JLabel();
		JLabel concertCodeLabels = new JLabel();
		JLabel usernameLabels = new JLabel();
		JLabel purchaseTotalLabels = new JLabel();


		JLabel numbers = new JLabel("");
		int i = 0;

		rset = db.getAllPurchases();
		
		
		while(rset.next())
		{
			numbers=new JLabel((i + 1) + ".");
			c.insets = new Insets(0,30,10,0);
			c.gridx = 0;
			c.gridy = i + 1;
			panel1.add(numbers,c);

			String purchaseCode = Integer.toString(rset.getInt(3));

			purchaseCodeLabels=new JLabel(purchaseCode);
			c.insets = new Insets(0,30,10,0);
			c.gridx = 1;
			c.gridy = i + 1;
			panel1.add(purchaseCodeLabels,c);

			String concertCode = Integer.toString(rset.getInt(2));

			concertCodeLabels=new JLabel(concertCode);
			c.insets = new Insets(0,30,10,0);
			c.gridx = 2;
			c.gridy = i + 1;
			panel1.add(concertCodeLabels,c);

			usernameLabels=new JLabel(rset.getString(1));
			c.insets = new Insets(0,30,10,0);
			c.gridx = 3;
			c.gridy = i + 1;
			panel1.add(usernameLabels,c);

			String purchaseTotal = Double.toString(rset.getDouble(6));
			purchaseTotalLabels=new JLabel(purchaseTotal);
			c.insets = new Insets(0,30,10,0);
			c.gridx = 4;
			c.gridy = i + 1;
			panel1.add(purchaseTotalLabels,c);
			i++;
		}

		panel3.add(back);
		panel1.add(scrollPane);
		

		theHandler handler = new theHandler();
		back.addActionListener(handler);
	}

	private class theHandler implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{	
			if(e.getSource().equals(back))
			{
				db.closeDB();
				AdminHomeScreen x = new AdminHomeScreen(LoginPage.usernameCopy);
				viewAllPurchasesFrame.setVisible(false);
			}
		}

	}
}

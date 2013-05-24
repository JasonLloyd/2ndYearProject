package UserClasses;

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
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oracle.sql.DATE;

import ie.ittd.year2.project.concert.adminClasses.*;

import ie.ittd.year2.project.concert.dbFiles.*;
public class BookConcertScreen 
{
	ResultSet rset;
	static dbConnect2 db = new dbConnect2();

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();

	JFrame bookingFrame = new JFrame();

	JLabel title = new JLabel("Book Concert");
	JLabel concertCode = new JLabel("Concert Code:");
	JLabel concertName = new JLabel("Concert Name:");
	JLabel concertType = new JLabel("Concert Type:");
	JLabel concertDate = new JLabel("Concert Date:");
	JLabel concertLocation = new JLabel("Concert Location:");
	JLabel concertPrice = new JLabel("Concert Price:");
	JLabel noOfTickets = new JLabel("How many tickets would you like?");

	JLabel concertCodeValue = new JLabel("*Test*");
	JLabel concertNameValue = new JLabel("*Test1*");
	JLabel concertTypeValue = new JLabel("Test2");
	JLabel concertDateValue = new JLabel("*Test3*");
	JLabel concertLocationValue = new JLabel("*Test4*");
	JLabel concertPriceValue = new JLabel("*Test5*");
	JTextField noOfTicketsValue = new JTextField(16);

	JLabel creditCardNo = new JLabel("Enter Credit Card No:");
	JTextField creditCardNoValue = new JTextField(16); 

	JButton book = new JButton("Book");
	JButton back = new JButton("Back");

	GridBagConstraints c = new GridBagConstraints();

	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,25);

	String concertTitle = "";
	int screenCurrentlyOn;

	public BookConcertScreen(String x,int screenNo) throws SQLException
	{
		db.connect();
		rset = db.getConcertsInformation(x);

		screenCurrentlyOn = screenNo;

		bookingFrame.setTitle("Concert System: Book Concert");
		bookingFrame.setSize(500,400);
		bookingFrame.setLocation(500, 250);
		bookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bookingFrame.getContentPane().setLayout(new BorderLayout());
		bookingFrame.setVisible(true);

		bookingFrame.add(p1,BorderLayout.NORTH);
		bookingFrame.add(p2,BorderLayout.CENTER);
		bookingFrame.add(p3,BorderLayout.SOUTH);

		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.setLayout(new GridBagLayout());
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));

		p1.setBackground(Color.white);
		p2.setBackground(Color.white);
		p3.setBackground(Color.white);

		p3.setBorder(BorderFactory.createRaisedBevelBorder());

		title.setFont(f2);
		p1.add(title);

		c.insets = new Insets(0,115,0,0);
		c.gridx = 0;
		c.gridy = 0;
		p2.add(concertCode,c);

		rset.next();
		String code = Integer.toString(rset.getInt(1)).trim();

		concertCodeValue.setText(code);
		c.insets = new Insets(0,20,0,0);
		c.gridx = 1;
		c.gridy = 0;
		p2.add(concertCodeValue,c);


		c.insets = new Insets(20,110,0,0);
		c.gridx = 0;
		c.gridy = 1;
		p2.add(concertName,c);

		concertNameValue.setText(rset.getString(2).trim());
		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 1;
		p2.add(concertNameValue,c);

		c.insets = new Insets(20,110,0,0);
		c.gridx = 0;
		c.gridy = 2;
		p2.add(concertType,c);

		concertTypeValue.setText(rset.getString(3).trim());
		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 2;
		p2.add(concertTypeValue,c);

		c.insets = new Insets(20,115,0,0);
		c.gridx = 0;
		c.gridy = 3;
		p2.add(concertDate,c);


		Date d1 = rset.getDate(5);
		String date = d1.toString();

		concertDateValue.setText(date);
		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 3;
		p2.add(concertDateValue,c);

		c.insets = new Insets(20,90,0,0);
		c.gridx = 0;
		c.gridy = 4;
		p2.add(concertLocation,c);

		concertLocationValue.setText(rset.getString(4).trim());
		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 4;
		p2.add(concertLocationValue,c);

		c.insets = new Insets(20,110,0,0);
		c.gridx = 0;
		c.gridy = 5;
		p2.add(concertPrice,c);

		String price = Double.toString(rset.getFloat(6));
		concertPriceValue.setText(price);
		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 5;
		p2.add(concertPriceValue,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 6;
		p2.add(noOfTickets,c);

		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 6;
		p2.add(noOfTicketsValue,c);

		c.insets = new Insets(20,70,0,0);
		c.gridx = 0;
		c.gridy = 7;
		p2.add(creditCardNo,c);

		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 7;
		p2.add(creditCardNoValue,c);

		p3.add(back);
		p3.add(book);

		theHandler handler = new theHandler();
		book.addActionListener(handler);
		back.addActionListener(handler);


	}

	private class theHandler implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{
			JButton pushed = (JButton) e.getSource();

			if(pushed.equals(back))
			{
				if(screenCurrentlyOn == 1)
				{
					db.closeDB();
					upcomingConcerts.upcomingConcertsFrame.setVisible(true);
					bookingFrame.setVisible(false);
				}
				else 
				{
					db.closeDB();
					allConcerts x = new allConcerts();
					bookingFrame.setVisible(false);
				}
			}
			if(pushed.equals(book))
			{
				String username = LoginPage.usernameCopy;
				String numberOfTickets = noOfTickets.getText();
				String creditCardNo = creditCardNoValue.getText();

				if(numberOfTickets.equals("") || creditCardNo.equals("") || numberOfTickets.equals(null) || creditCardNo.equals(null))
				{
					JOptionPane.showMessageDialog(null, "Fields Must be filled in","Fields Empty", JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
					int noOfTickets = 0;
					try
					{
						noOfTickets = Integer.parseInt(noOfTicketsValue.getText());
					}catch(NumberFormatException e1)
					{
						JOptionPane.showMessageDialog(null, "The tickets amount must be ONLY numbers","Ticket Amount Error",JOptionPane.WARNING_MESSAGE);
					}

					String CCheck = creditCardNoValue.getText();

					if(!(CCheck.length() == 12))
					{
						JOptionPane.showMessageDialog(null,"Credit card no MUST be 12 digits Long","Error",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						rset = db.getUsersDetails(username);

						int creditCardNo2 = 0;

						try
						{
							creditCardNo2 = Integer.parseInt(CCheck); 
						}catch(NumberFormatException e5)
						{
							JOptionPane.showMessageDialog(null,"Please enter only numbers in credit card field","Credit Card Error",JOptionPane.ERROR_MESSAGE);
						}

						int creditCardCompare = 0;

						try 
						{
							rset.next();
							creditCardCompare = rset.getInt(6);
						} catch (SQLException e1) 
						{
							System.out.println("could not get credit card number");
						}

						if(creditCardNo2 != creditCardCompare)
						{
							JOptionPane.showMessageDialog(null,"credit card doesnt match please renter","Credit Card Wrong",JOptionPane.ERROR_MESSAGE);
						}
						else{

							String concertCode = concertCodeValue.getText(); 
							String concertName = concertNameValue.getText(); 
							String concertType = concertTypeValue.getText();
							String concertLocation = concertLocationValue.getText();
							String dateOfConcert = concertDateValue.getText();
							String noOfTicketsWanted = noOfTicketsValue.getText(); 
							String ticketPriceCheck = concertPriceValue.getText();

							int noOfTicketsOrdered = 0;
							int concertCode2 = Integer.parseInt(concertCode);

							try
							{
								noOfTicketsOrdered = Integer.parseInt(noOfTicketsWanted); 
							}catch(NumberFormatException e1)
							{
								JOptionPane.showMessageDialog(null, "The tickets amount must be ONLY numbers","Ticket Amount Error",JOptionPane.WARNING_MESSAGE);
							}

							if(noOfTicketsOrdered > 5 || noOfTicketsOrdered < 1)
							{
								JOptionPane.showMessageDialog(null, "You must book between 1 and 5 tickets","Numbers of tickets error", JOptionPane.PLAIN_MESSAGE);
							}
							else
							{

								double ticketPrice = Double.parseDouble(ticketPriceCheck);

								try
								{

									double totalAmount = ticketPrice * noOfTickets; 

									Random r1 = new Random();

									int purchaseCode = r1.nextInt(1000);

									bookingFrame.setVisible(false);
									
									db.addSale(username, concertCode2, purchaseCode, noOfTicketsOrdered, creditCardNo2, totalAmount,concertName,concertType,concertLocation,dateOfConcert);

								}catch(NumberFormatException e1)
								{
									JOptionPane.showMessageDialog(null, "Please enter a valid amount for price","Price Error",JOptionPane.WARNING_MESSAGE);
								}

							}
						}
					}
				}
			}
		}

	}

	public static void confirmationScreen(int purchaseCode,int concertCode2,String concertName,String concertType,String concertLocation,String dateOfConcert,int noOfTicketsOrdered, double totalAmount) 
	{
		ImageIcon checkMark = new ImageIcon("images\\Check_mark.png");
		JLabel pictureLabel = new JLabel("",checkMark,JLabel.CENTER);

		Font f3 = new Font("Roman",Font.BOLD + Font.ITALIC,30);

		final JFrame confirmatonFrame = new JFrame();

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panel3 = new JPanel();

		JButton Finish = new JButton("Finish");

		String concertCode3 = Integer.toString(concertCode2);

		JLabel title = new JLabel("Confirmation Screen");

		String purchaseCodeConvert = Integer.toString(purchaseCode);
		JLabel purchaseCode2 = new JLabel(purchaseCodeConvert);
		JLabel purchaseCodeTitle = new JLabel("Purchase Code:");
		JLabel code = new JLabel(concertCode3);
		JLabel codeTitle = new JLabel("Concert Code:");
		JLabel name = new JLabel(concertName);
		JLabel nameTitle = new JLabel("Concert Title:");
		JLabel type = new JLabel(concertType);
		JLabel typeTitle = new JLabel("Concert Type:");
		JLabel location = new JLabel(concertLocation);
		JLabel locationTitle = new JLabel("Concert Location:");
		JLabel date = new JLabel(dateOfConcert);
		JLabel dateTitle = new JLabel("Concert Date:");

		String ticketsAmount = Integer.toString(noOfTicketsOrdered);
		JLabel ticketWanted = new JLabel(ticketsAmount);
		JLabel ticketsTitle = new JLabel("Tickets Booked");

		String amountValue = Double.toString(totalAmount);
		JLabel amount = new JLabel(amountValue);
		JLabel amountTitle = new JLabel("Money Paid:");

		confirmatonFrame.setTitle("Concert System: Confirmation Screen");
		confirmatonFrame.setSize(800,450);
		confirmatonFrame.setLocation(500, 250);
		confirmatonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		confirmatonFrame.getContentPane().setLayout(new BorderLayout());
		confirmatonFrame.setVisible(true);

		confirmatonFrame.add(panel2,BorderLayout.NORTH);
		confirmatonFrame.add(panel1,BorderLayout.CENTER);
		confirmatonFrame.add(panel3,BorderLayout.EAST);

		panel1.setBackground(Color.white);
		panel2.setBackground(Color.white);
		panel3.setBackground(Color.white);

		panel1.setLayout(new GridBagLayout());


		GridBagConstraints c = new GridBagConstraints();

		title.setFont(f3);

		panel2.add(title);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(purchaseCodeTitle,c);

		c.gridx = 1;
		c.gridy = 0;
		panel1.add(purchaseCode2,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		panel1.add(codeTitle,c);

		c.gridx = 1;
		c.gridy = 1;
		panel1.add(code,c);

		c.gridx = 0;
		c.gridy = 2;
		panel1.add(nameTitle,c);

		c.gridx = 1;
		c.gridy = 2;
		panel1.add(name,c);

		c.gridx = 0;
		c.gridy = 3;
		panel1.add(typeTitle,c);

		c.gridx = 1;
		c.gridy = 3;
		panel1.add(type,c);

		c.gridx = 0;
		c.gridy = 4;
		panel1.add(locationTitle,c);

		c.gridx = 1;
		c.gridy = 4;
		panel1.add(location,c);

		c.gridx = 0;
		c.gridy = 5;
		panel1.add(dateTitle,c);

		c.gridx = 1;
		c.gridy = 5;
		panel1.add(date,c);

		c.gridx = 0;
		c.gridy = 6;
		panel1.add(ticketsTitle,c);

		c.gridx = 1;
		c.gridy = 6;
		panel1.add(ticketWanted,c);

		c.gridx = 0;
		c.gridy = 7;
		panel1.add(amountTitle,c);

		c.gridx = 1;
		c.gridy = 7;
		panel1.add(amount,c);

		panel3.add(pictureLabel);

		c.insets = new Insets(0,300,0,0);
		c.gridx = 0;
		c.gridy = 8;
		panel1.add(Finish,c);

		Finish.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) 
			{ 
				db.closeDB();
				confirmatonFrame.setVisible(false);
				UserHomeScreen x = new UserHomeScreen(LoginPage.usernameCopy);
			}
		});
	}
}

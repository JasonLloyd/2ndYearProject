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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CreateNewConcert {

	theHandler handler = new theHandler();
	confirmationScreens x = new confirmationScreens();
	dbConnect2 db = new dbConnect2();
	ResultSet rset;

	private GridBagLayout gbl = new GridBagLayout();

	private JPanel panel1 = new JPanel(gbl);
	private JPanel panel2 = new JPanel();

	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,25);

	private JButton back = new JButton("Back");

	private JFrame createNewConcertFrame = new JFrame();

	private JTextField codeTf = new JTextField(16);
	private JTextField nameTf = new JTextField(16);
	private JTextField typeTf = new JTextField(16);
	private JTextField locationTf = new JTextField(16);
	private JTextField dateTf = new JTextField(16);
	private JTextField ticketsPriceTf = new JTextField(16);
	private JTextField concertAvailableTf = new JTextField(16);

	private JButton createConcert = new JButton("Create Concert");

	private GridBagConstraints c = new GridBagConstraints();

	public CreateNewConcert()
	{
		db.connect();
		JLabel createNewConcerttitle = new JLabel("Create New Concert:");
		JLabel concertCode = new JLabel("Concert Code:");
		JLabel concertName = new JLabel("Concert Name:");
		JLabel concertType = new JLabel("Concert Type:");
		JLabel concertLocation = new JLabel("Concert Location:");
		JLabel concertDate = new JLabel("Concert Date:");
		JLabel concertTicketPrice = new JLabel("Tickets Price:");
		JLabel concertAvailable = new JLabel("Is concert on: Yes/No");

		createNewConcertFrame.setTitle("Create New Concert");
		createNewConcertFrame.setSize(600,450);
		createNewConcertFrame.setLocation(500, 250);
		createNewConcertFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createNewConcertFrame.getContentPane().setLayout(new BorderLayout());
		createNewConcertFrame.setVisible(true);

		createNewConcertFrame.add(panel1,BorderLayout.CENTER);
		createNewConcertFrame.add(panel2,BorderLayout.NORTH);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		panel1.setBackground(Color.white);
		panel2.setBackground(Color.white);

		createNewConcerttitle.setFont(f2);
		panel2.add(createNewConcerttitle);

		c.insets = new Insets(20,50,0,0);
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(concertCode,c);

		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 0;
		panel1.add(codeTf,c);

		c.insets = new Insets(20,45,0,0);
		c.gridx = 0;
		c.gridy = 1;
		panel1.add(concertName,c);

		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 1;
		panel1.add(nameTf,c);

		c.insets = new Insets(20,45,0,0);
		c.gridx = 0;
		c.gridy = 2;
		panel1.add(concertType,c);

		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 2;
		panel1.add(typeTf,c);

		c.insets = new Insets(20,25,0,0);
		c.gridx = 0;
		c.gridy = 3;
		panel1.add(concertLocation,c);

		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 3;
		panel1.add(locationTf,c);

		c.insets = new Insets(20,45,0,0);
		c.gridx = 0;
		c.gridy = 4;
		panel1.add(concertDate,c);

		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 4;
		panel1.add(dateTf,c);

		c.insets = new Insets(20,20,0,0);
		c.gridx = 2;
		c.gridy = 4;
		panel1.add(new JLabel("YYYY-MM-DD"),c);

		c.insets = new Insets(20,45,0,0);
		c.gridx = 0;
		c.gridy = 5;
		panel1.add(concertTicketPrice,c);

		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 5;
		panel1.add(ticketsPriceTf,c);

		c.insets = new Insets(20,20,0,0);
		c.gridx = 0;
		c.gridy = 6;
		panel1.add(concertAvailable,c);

		c.insets = new Insets(20,20,0,0);
		c.gridx = 1;
		c.gridy = 6;
		panel1.add(concertAvailableTf,c);

		c.insets = new Insets(20,30,0,20);
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 2;
		panel1.add(back,c);

		c.insets = new Insets(20,80,0,0);
		c.gridx = 1;
		c.gridy = 7;
		panel1.add(createConcert,c);

		back.addActionListener(handler);
		createConcert.addActionListener(handler);
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
				createNewConcertFrame.setVisible(false);
			}

			if(pushed.equals(createConcert))
			{
				rset = db.getConcerts();
				try
				{
					rset.next();
					String concertCodeCheck = codeTf.getText();
					String concertName = nameTf.getText();
					String concertType = typeTf.getText();
					String concertLocation = locationTf.getText();
					String dateOfConcert = dateTf.getText();
					String ticketPriceCheck = ticketsPriceTf.getText();

					String concertAvailable = concertAvailableTf.getText().trim();

					if(concertCodeCheck.equals("")|| concertName.equals("") || concertType.equals("")|| concertLocation.equals("") || dateOfConcert.equals("") || ticketPriceCheck.equals("") || concertAvailable.equals(""))
					{
						x.fieldsNotFilledIn();
					}
					else if(concertCodeCheck.length() > 5 || concertCodeCheck.length() < 1)
					{
						JOptionPane.showMessageDialog(null, "Concert code but be at least 2 charactors long","Concert Code Error", JOptionPane.PLAIN_MESSAGE);
					}

					else
					{
						int concertCode = 0;
						double ticketPrice = 0.00; 
						Date d1 = null;
						
						try
						{
							concertCode = Integer.parseInt(codeTf.getText());
						}catch(NumberFormatException e1)	
						{
							JOptionPane.showMessageDialog(null, "The concert code must be only numbers","Concert Code error",JOptionPane.WARNING_MESSAGE);
						}

						try
						{
							ticketPrice = Double.parseDouble(ticketPriceCheck);
						}catch(NumberFormatException e2)	
						{
							JOptionPane.showMessageDialog(null, "Please enter a valid amount for price","Price Error",JOptionPane.WARNING_MESSAGE);
						}
						
						try
						{
							d1 = Date.valueOf(dateTf.getText());
						}catch(Exception e3)
						{
							JOptionPane.showMessageDialog(null, "Date Must be a correct date and in format of YYYY-MM-DD","Date error",JOptionPane.WARNING_MESSAGE);
						}

						if(ticketPrice < 0 || ticketPrice > 10000000)
						{
							JOptionPane.showMessageDialog(null, "Ticket Price must be less greater than 0 and less than 1 million","Ticket price error", JOptionPane.PLAIN_MESSAGE);
						}
						
						else if(concertCode == 0 || ticketPrice == 0 || d1 == null)
						{
							//this if statement only here so i can check the format of the above variables to 
							//make sure that information the user is enter is in the correct format
						}
						else
						{
							db.addConcert(concertCode, concertName, concertType, concertLocation, d1, ticketPrice, concertAvailable);

						}
					}
				}catch(SQLException er)
				{
					//only here in the case of a database problem
					System.out.println("Problem with database ");
				}
			}

		}
	}
}

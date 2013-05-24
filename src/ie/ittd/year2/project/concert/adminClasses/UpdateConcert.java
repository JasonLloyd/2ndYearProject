package ie.ittd.year2.project.concert.adminClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ie.ittd.year2.project.concert.dbFiles.*;


public class UpdateConcert 
{
	theHandler handler = new theHandler();
	dbConnect2 db = new dbConnect2();
	ResultSet rset;

	int i = 0;
	
	private GridBagLayout gbl = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();

	private JFrame updateConcertFrame = new JFrame();

	//panels
	private JPanel panel1 = new JPanel(gbl);
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();


	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,25);

	private JTextField updateNameTf = new JTextField(16);
	private JTextField updateTypeTf = new JTextField(16);
	private JTextField updateLocationTf = new JTextField(16);
	private JTextField updateDateTf = new JTextField(16);
	private JTextField updateTicketsPriceTf = new JTextField(16);
	private JTextField updateConcertTf = new JTextField(16);
	private JTextField updateConcertAvailablilityTf = new JTextField(16);
	
	private JButton updateConcert = new JButton("Update Concert");
	private JButton viewConcert = new JButton("View Concert");
	private JButton clear = new JButton("Clear");
	private JButton back = new JButton("Back");

	private JLabel updateConcerTitle = new JLabel("Update Concert:");
	private JLabel concertToUpdate = new JLabel("Enter concert code you would like to update:");
	private JLabel updateConcertName = new JLabel("Concert Name:");
	private JLabel updateConcertType = new JLabel("Concert Type:");
	private JLabel updateConcertLocation = new JLabel("Concert Location:");
	private JLabel updateConcertDate = new JLabel("Concert Date:");
	private JLabel updateConcertTicketPrice = new JLabel("Tickets Price:");
	private JLabel updateConcertAvailablility = new JLabel("Concert Available Yes/No");

	public UpdateConcert()
	{
		db.connect();

		JTable t1 = db.concertTable();
		JScrollPane scrollPane = new JScrollPane(t1);



		updateConcertFrame.setTitle("Update Concert");
		updateConcertFrame.setSize(670,560);
		updateConcertFrame.setLocation(100, 100);
		updateConcertFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateConcertFrame.getContentPane().setLayout(new BorderLayout());
		updateConcertFrame.setVisible(true);

		updateConcertFrame.add(panel1,BorderLayout.CENTER);
		updateConcertFrame.add(panel2,BorderLayout.NORTH);
		updateConcertFrame.add(panel3,BorderLayout.SOUTH);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setLayout(new BorderLayout());
		
		panel1.setBackground(Color.white);
		panel2.setBackground(Color.white);
		panel3.setBackground(Color.white);

		updateConcerTitle.setFont(f2);
		panel2.add(updateConcerTitle);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(concertToUpdate,c);

		c.insets = new Insets(20,10,0,0);
		c.gridx = 1;
		c.gridy = 0;
		panel1.add(updateConcertTf,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 2;
		panel1.add(updateConcertName,c);

		c.insets = new Insets(20,10,0,0);
		c.gridx = 1;
		c.gridy = 2;
		panel1.add(updateNameTf,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 3;
		panel1.add(updateConcertType,c);

		c.insets = new Insets(20,10,0,0);
		c.gridx = 1;
		c.gridy = 3;
		panel1.add(updateTypeTf,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 4;
		panel1.add(updateConcertLocation,c);

		c.insets = new Insets(20,10,0,0);
		c.gridx = 1;
		c.gridy = 4;
		panel1.add(updateLocationTf,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 5;
		panel1.add(updateConcertDate,c);

		c.insets = new Insets(20,10,0,0);
		c.gridx = 1;
		c.gridy = 5;
		panel1.add(updateDateTf,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 6;
		panel1.add(updateConcertTicketPrice,c);

		c.insets = new Insets(20,10,0,0);
		c.gridx = 1;
		c.gridy = 6;
		panel1.add(updateTicketsPriceTf,c);

		c.gridx = 0;
		c.gridy = 7;
		panel1.add(updateConcertAvailablility,c);

		c.gridx = 1;
		c.gridy = 7;
		panel1.add(updateConcertAvailablilityTf,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 8;
		panel1.add(back,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 1;
		c.gridy = 8;
		panel1.add(viewConcert,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 2;
		c.gridy = 8;
		panel1.add(updateConcert,c);
		
		c.insets = new Insets(20,20,0,0);
		c.gridx = 3;
		c.gridy = 8;
		panel1.add(clear,c);

		panel3.add(scrollPane);

		updateNameTf.setEditable(false);
		updateTypeTf.setEditable(false);
		updateLocationTf.setEditable(false);
		updateDateTf.setEditable(false);
		updateTicketsPriceTf.setEditable(false);
		updateConcertAvailablilityTf.setEditable(false);

		back.addActionListener(handler);
		clear.addActionListener(handler);
		viewConcert.addActionListener(handler);
		updateConcert.addActionListener(handler);
	}



	private class theHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton viewConcertButton = (JButton) (e.getSource());
			JButton updateConcertButton = (JButton) (e.getSource());
			JButton clearButton = (JButton) (e.getSource());
			JButton updateUpcomingConcerts = (JButton) (e.getSource());
			
			if(e.getSource().equals(back))
			{
				db.closeDB();
				updateConcertFrame.setVisible(false);
				AdminHomeScreen x = new AdminHomeScreen(LoginPage.usernameCopy);

			}

			if(viewConcertButton.equals(viewConcert))
			{
				if(updateConcertTf.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please enter concert code","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
				{

					int concertCode = 0;
					
					try{
					concertCode = Integer.parseInt(updateConcertTf.getText());
					}catch(NumberFormatException e5)
					{
						JOptionPane.showMessageDialog(null,"Only enter Numbers of concert code"," Concert code Error",JOptionPane.ERROR_MESSAGE);
					}
					rset = db.getConcertsToChange(concertCode);

					try{				
						updateConcertTf.setEditable(false);
						updateNameTf.setEditable(true);
						updateTypeTf.setEditable(true);
						updateLocationTf.setEditable(true);
						updateDateTf.setEditable(true);
						updateTicketsPriceTf.setEditable(true);
						updateConcertAvailablilityTf.setEditable(true);

						Date dateOfConcert = rset.getDate(5);
						
						String dateTf = dateOfConcert.toString();

						double price = rset.getDouble(6);
						String priceTf = Double.toString(price);

						updateNameTf.setText(rset.getString(2).trim());
						updateTypeTf.setText(rset.getString(3).trim());
						updateLocationTf.setText(rset.getString(4).trim());
						updateDateTf.setText(dateTf);
						updateTicketsPriceTf.setText(priceTf);
						updateConcertAvailablilityTf.setText(rset.getString(7).trim());
						rset.close();


					}catch(SQLException a){}
				}
			}
			if(updateConcertButton.equals(updateConcert))
			{
				String code = updateConcertTf.getText();
				String updateName = updateNameTf.getText();
				String updateType = updateTypeTf.getText();
				String updateLocation = updateLocationTf.getText();
				String updateDate = updateDateTf.getText();
				String updateTicketPrice = updateTicketsPriceTf.getText();
				String concertAvailable = updateConcertAvailablilityTf.getText();
				String cac = concertAvailable.trim();
				
				if(code.equals("")||updateName.equals("") || updateType.equals("") || updateLocation.equals("") || updateDate.equals("") || updateTicketPrice.equals("") || concertAvailable.equals(""))
				{
					JOptionPane.showMessageDialog(null,"All field must be filled in","Error",JOptionPane.ERROR_MESSAGE);
				}

				else
				{

					double ticketPrice = 0;
					int concertCode = 0;
					Date d1 = null;
					
					try{
						ticketPrice = Double.parseDouble(updateTicketPrice);
			
					}catch(NumberFormatException e1)
					{
						JOptionPane.showMessageDialog(null, "Please enter a valid amount for price","Price Error",JOptionPane.WARNING_MESSAGE);
					}
					
					try{

						concertCode = Integer.parseInt(updateConcertTf.getText());

					}catch(NumberFormatException e1)
					{
						JOptionPane.showMessageDialog(null, "The concert code must be only numbers","Concert Code error",JOptionPane.WARNING_MESSAGE);
					}
					
					try{

						d1 = Date.valueOf(updateDate);

					}catch(java.lang.IllegalArgumentException e1)
					{
						JOptionPane.showMessageDialog(null, "Date Must be a correct date and in format of YYYY-MM-DD","Date error",JOptionPane.WARNING_MESSAGE);
					}
					
					if(ticketPrice == 0.00 || concertCode == 0 || d1 == null)
					{
						
					}
					else
					{
						updateConcertTf.setEditable(true);
						db.updateConcert(concertCode, updateName, updateType, updateLocation, d1, ticketPrice , concertAvailable);
					}
	
				}
			}
			if(clearButton.equals(clear))
			{
				updateConcertTf.setEditable(true);
				updateNameTf.setEditable(false);
				updateTypeTf.setEditable(false);
				updateLocationTf.setEditable(false);
				updateDateTf.setEditable(false);
				updateTicketsPriceTf.setEditable(false);
				updateConcertAvailablilityTf.setEditable(false);

				updateConcertTf.setText("");
				updateNameTf.setText("");
				updateTypeTf.setText("");
				updateLocationTf.setText("");
				updateDateTf.setText("");
				updateTicketsPriceTf.setText("");
				updateConcertAvailablilityTf.setText("");

			}
		}
	}
}

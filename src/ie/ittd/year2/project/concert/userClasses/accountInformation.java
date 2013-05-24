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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ie.ittd.year2.project.concert.adminClasses.*;

import ie.ittd.year2.project.concert.dbFiles.*;


public class accountInformation 
{

	ResultSet rset;
	dbConnect2 db = new dbConnect2();
	confirmationScreens x = new confirmationScreens();

	private GridBagLayout gbl = new GridBagLayout();

	private JFrame accountInformationFrame = new JFrame();

	private JPanel panel1 = new JPanel(gbl);
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();

	JLabel title = new JLabel("Account Information");
	JLabel changeDetailsLabel = new JLabel("Change Details");

	JLabel personalDetails = new JLabel("Personal Details");
	JLabel username = new JLabel("Username");
	JLabel password = new JLabel("Password");
	JLabel creditCardNo = new JLabel("Credit Card number");
	JLabel usernameTf = new JLabel();
	JLabel passwordTf = new JLabel();
	JLabel creditCardNoTF = new JLabel();

	JLabel newUsername = new JLabel("Username");
	JLabel newPassword = new JLabel("New Password");
	JLabel newCreditCardNo = new JLabel("New Credit Card number");

	JTextField newUsernameTf = new JTextField(10);
	JTextField newPasswordTf = new JTextField(10);
	JTextField newCreditNoTf = new JTextField(10);

	JButton submit = new JButton("Submit");
	JButton changeDetails = new JButton("Change Details");
	JButton back = new JButton("Back");

	theHandler myHandler = new theHandler();

	GridBagConstraints c = new GridBagConstraints();

	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,25);

	public accountInformation() throws SQLException
	{
		db.connect();

		String usernameToEdit = LoginPage.usernameCopy;

		rset = db.getUsersDetails(usernameToEdit);

		rset.next();

		accountInformationFrame.setTitle("Concert System: Account Information");
		accountInformationFrame.setSize(550,425);
		accountInformationFrame.setLocation(300, 250);
		accountInformationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accountInformationFrame.getContentPane().setLayout(new BorderLayout());
		accountInformationFrame.getContentPane().setBackground(Color.WHITE);
		accountInformationFrame.setVisible(true);

		accountInformationFrame.add(panel1,BorderLayout.WEST);
		accountInformationFrame.add(panel2,BorderLayout.NORTH);
		accountInformationFrame.add(panel3,BorderLayout.SOUTH);
		accountInformationFrame.add(panel4,BorderLayout.EAST);

		panel4.setVisible(false);

		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));

		panel1.setBackground(Color.WHITE);
		panel2.setBackground(Color.WHITE);
		panel3.setBackground(Color.WHITE);
		panel4.setBackground(Color.WHITE);
		
		
		title.setFont(f2);
		changeDetailsLabel.setFont(f2);
		personalDetails.setFont(f2);

		panel2.add(title);

		c.insets = new Insets(0,20,10,0);
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(personalDetails,c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(username,c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(password,c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(creditCardNo,c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(usernameTf,c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(passwordTf,c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(creditCardNoTF,c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(new JLabel(),c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel1.add(new JLabel(),c);

		panel3.add(back);
		panel3.add(changeDetails);

		//panel 4
		panel4.setLayout(gbl);

		c.insets = new Insets(0,20,10,20);
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel4.add(changeDetailsLabel,c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel4.add(newUsername,c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel4.add(newPassword,c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel4.add(newCreditCardNo,c);

		newUsernameTf.setEditable(false);
		c.insets = new Insets(20,20,10,0);
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel4.add(newUsernameTf,c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel4.add(newPasswordTf,c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel4.add(newCreditNoTf,c);

		c.insets = new Insets(20,20,10,0);
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel4.add(submit,c);

		usernameTf.setText(rset.getString(2).trim());
		passwordTf.setText(rset.getString(3).trim());
		String creditCardNumber = Integer.toString(rset.getInt(6));
		creditCardNoTF.setText(creditCardNumber);

		newUsernameTf.setText(usernameTf.getText());

		submit.addActionListener(myHandler);
		changeDetails.addActionListener(myHandler);
		back.addActionListener(myHandler);
	}

	private class theHandler implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			JButton pushed = (JButton) e.getSource();

			if(pushed.equals(changeDetails))
			{
				panel4.setVisible(true);
				accountInformationFrame.setSize(850,425);
			}

			if(pushed.equals(submit))
			{

				String username = newUsernameTf.getText();
				String newPassword = newPasswordTf.getText();
				String newCreditCardNo = newCreditNoTf.getText().trim();

				if(newUsername.equals("") || newPassword.equals("") || newCreditCardNo.equals(""))
				{
					x.fieldsNotFilledIn();
				}
				else if(newPassword.length() < 5 || newPassword.length() > 10)
				{
					x.passwordTooShort();
				}
				else if((newCreditCardNo.length() != 12))
				{
					x.creditCardTooShort();
				}
				else
				{
					int creditCardNo = 0;
				
					try{
					creditCardNo = Integer.parseInt(newCreditCardNo);
					db.changeDetails(username,newPassword,creditCardNo);
					panel4.setVisible(false);
					accountInformationFrame.setSize(550,425);
					}catch(NumberFormatException e5)
					{
						JOptionPane.showMessageDialog(null, "Only Enter Numbers in Credit card field","Credit card error",JOptionPane.WARNING_MESSAGE);
					}				
				}
			}

			if(pushed.equals(back))
			{
				db.closeDB();
				UserHomeScreen x = new UserHomeScreen(LoginPage.usernameCopy);
				accountInformationFrame.setVisible(false);
				

			}

		}

	}
}

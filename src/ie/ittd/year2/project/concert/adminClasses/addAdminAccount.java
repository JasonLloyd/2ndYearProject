package ie.ittd.year2.project.concert.adminClasses;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ie.ittd.year2.project.concert.dbFiles.*;


public class addAdminAccount {

	private GridBagLayout gbl = new GridBagLayout();

	theHandler handler = new theHandler();
	confirmationScreens x = new confirmationScreens();
	dbConnect2 db = new dbConnect2();
	ResultSet rset;

	int i = 0;

	private JFrame addAdminAccountFrame = new JFrame();


	private JPanel panel1 = new JPanel(gbl);
	private JPanel panel2 = new JPanel();


	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,25);

	private JButton back = new JButton("Back");
	private String[] accountOptions = {"Administrator", "User"};

	private JButton createNewAccount = new JButton("Create new Account");

	private JComboBox accTypeBox = new JComboBox(accountOptions);

	private JLabel accountType = new JLabel("Account Type:");
	private JLabel title = new JLabel("Add Account");
	private JLabel username = new JLabel("New Username:");
	private JLabel password = new JLabel("New Password:");
	private JLabel firstName = new JLabel("First Name:");
	private JLabel lastName = new JLabel("Last Name:");
	private JLabel creditCardNo = new JLabel("Credit Card No:");
	private JLabel capsLocksMessage = new JLabel("");
	private JLabel space = new JLabel("");

	private JTextField usernameTf = new JTextField(16);
	private JPasswordField passwordTf = new JPasswordField(16);
	private JTextField firstNameTf = new JTextField(16);
	private JTextField lastNameTf = new JTextField(16);
	private JTextField creditCardNoTf = new JTextField(16);

	private GridBagConstraints c = new GridBagConstraints();

	public addAdminAccount()
	{
		db.connect();
		rset = db.getUsers();
		addAdminAccountFrame.setTitle("Create Account");
		addAdminAccountFrame.setSize(580,400);
		addAdminAccountFrame.setLocation(500, 250);
		addAdminAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addAdminAccountFrame.getContentPane().setLayout(new BorderLayout());
		addAdminAccountFrame.setVisible(true);

		addAdminAccountFrame.add(panel1,BorderLayout.CENTER);
		addAdminAccountFrame.add(panel2,BorderLayout.NORTH);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

		panel1.setBackground(Color.white);
		panel2.setBackground(Color.white);
		
		title.setFont(f2);
		panel2.add(title);

		c.insets = new Insets(20,20,0,0);
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(accountType,c);

		c.insets = new Insets(20,0,0,20);
		c.gridx = 1;
		c.gridy = 0;
		panel1.add(accTypeBox,c);

		c.insets = new Insets(20,10,0,0);
		c.gridx = 0;
		c.gridy = 1;
		panel1.add(username,c);

		c.insets = new Insets(20,0,0,20);
		c.gridx = 1;
		c.gridy = 1;
		panel1.add(usernameTf,c);

		c.insets = new Insets(20,30,0,20);
		c.gridx = 0;
		c.gridy = 2;
		panel1.add(password,c);

		c.insets = new Insets(20,0,0,20);
		c.gridx = 1;
		c.gridy = 2;
		panel1.add(passwordTf,c);


		c.insets = new Insets(20,50,0,20);
		c.gridx = 0;
		c.gridy = 3;
		panel1.add(firstName,c);

		c.insets = new Insets(20,0,0,20);
		c.gridx = 1;
		c.gridy = 3;
		panel1.add(firstNameTf,c);

		c.insets = new Insets(20,50,0,20);
		c.gridx = 0;
		c.gridy = 4;
		panel1.add(lastName,c);

		c.insets = new Insets(20,0,0,20);
		c.gridx = 1;
		c.gridy = 4;
		panel1.add(lastNameTf,c);

		c.insets = new Insets(20,10,0,0);
		c.gridx = 0;
		c.gridy = 5;
		panel1.add(creditCardNo,c);

		c.insets = new Insets(20,0,0,20);
		c.gridx = 1;
		c.gridy = 5;
		panel1.add(creditCardNoTf,c);

		c.insets = new Insets(20,100,0,20);
		c.gridx = 0;
		c.gridy = 6;
		panel1.add(back,c);

		c.insets = new Insets(20,10,0,0);
		c.gridx = 1;
		c.gridy = 6;
		panel1.add(createNewAccount,c);

		capsLocksMessage.setVisible(false);
		c.gridx = 2;
		c.gridy = 6;
		panel1.add(capsLocksMessage,c);

		passwordTf.addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent e) {

					boolean _capLockToggle = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
					
					if(_capLockToggle == true)
					{
						capsLocksMessage.setText("Caps lock is on");
						capsLocksMessage.setForeground(Color.red);
						capsLocksMessage.setVisible(true);
					}
					else
					{
						capsLocksMessage.setText("");
						space.setVisible(false);
						capsLocksMessage.setVisible(false);
					}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		createNewAccount.addActionListener(handler);
		accTypeBox.addActionListener(handler);
		back.addActionListener(handler);
	}
	private class theHandler implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource().equals(createNewAccount))
			{

				if(accTypeBox.getSelectedIndex() == 0)
				{
					i = 1;
				}
				if(accTypeBox.getSelectedIndex() == 1)
				{
					i = 2;
				}


				String username = usernameTf.getText();
				String password = new String(passwordTf.getText());
				String firstName = firstNameTf.getText();
				String lastName = lastNameTf.getText();
				String CCNcheck = creditCardNoTf.getText();

				try {
					rset.next();


					if(username.equals(null) || password.equals(null)|| firstName.equals(null) || lastName.equals(null) || CCNcheck.equals(null))
					{
						x.fieldsNotFilledIn();
					}

					if(username.equals("") || password.equals("")|| firstName.equals("") || lastName.equals("") || CCNcheck.equals(""))
					{
						x.fieldsNotFilledIn();
					}
					else if(username.length() < 5)
					{
						x.usernameTooShort();
					}

					else if(password.length() < 5 || password.length() > 10)
					{
						x.passwordTooShort();
					}

					else if(CCNcheck.length() != 12)
					{
						x.creditCardTooShort();
					}

					else
					{

						int creditCardNo2 = 0;
						try
						{
							creditCardNo2 = Integer.parseInt(CCNcheck);
							db.addAccount(i,username, password, firstName, lastName, creditCardNo2);
						
						}catch(NumberFormatException e3)
						{
							JOptionPane.showMessageDialog(null, "Only Enter Numbers in Credit card field","Credit card error",JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
			}
			if(e.getSource().equals(back))
			{
				db.closeDB();
				AdminHomeScreen x = new AdminHomeScreen(LoginPage.usernameCopy);
				addAdminAccountFrame.setVisible(false);
			}
		}
	}
}

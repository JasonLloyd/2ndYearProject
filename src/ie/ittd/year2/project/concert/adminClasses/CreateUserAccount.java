package ie.ittd.year2.project.concert.adminClasses;

import ie.ittd.year2.project.concert.dbFiles.*;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class CreateUserAccount 
{

	theHandler handler = new theHandler();
	dbConnect2 db = new dbConnect2();
	ResultSet rset;

	private JButton createNewAccount = new JButton("Create New Account");
	private JButton back = new JButton("Back");

	private JLabel firstName = new JLabel("First Name:");
	private JLabel lastName = new JLabel("Last Name:");

	private Font f1 = new Font("Roman",Font.BOLD,20);

	private JFrame createUserAccountFrame = new JFrame();

	private JLabel caUsername = new JLabel("New Username:");
	private JLabel caPassword = new JLabel("New Password:");
	private JLabel caCreditCardNo = new JLabel("Credit Card No:");
	private JLabel capsLocksMessage = new JLabel("");
	private JLabel space = new JLabel("");
	private JTextField caUsernameTf = new JTextField(16);
	private JPasswordField caPasswordTf = new JPasswordField(16);
	private JTextField caFirstNameTf = new JTextField(16);
	private JTextField caLastNameTf = new JTextField(16);
	private JTextField caCreditCardNoTf = new JTextField(16);

	private GridBagConstraints c = new GridBagConstraints();


	public CreateUserAccount()
	{

		db.connect();
		rset = db.getUsers();

		JPanel panel1 = new JPanel(new GridBagLayout());
		JPanel panel2 = new JPanel();

		JLabel caTitle = new JLabel("Create New Account");

		createUserAccountFrame.setTitle("Create Account");
		createUserAccountFrame.setSize(520,400);
		createUserAccountFrame.setLocation(500, 250);
		createUserAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createUserAccountFrame.getContentPane().setLayout(new BorderLayout());
		createUserAccountFrame.setVisible(true);

		createUserAccountFrame.add(panel1,BorderLayout.CENTER);
		createUserAccountFrame.add(panel2,BorderLayout.NORTH);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

		panel1.setBackground(Color.white);
		panel2.setBackground(Color.white);

		caTitle.setFont(f1);
		panel2.add(caTitle);

		c.insets = new Insets(0,0,0,20);
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(caUsername,c);

		c.insets = new Insets(0,0,0,20);
		c.gridx = 1;
		c.gridy = 0;
		panel1.add(caUsernameTf,c);

		c.insets = new Insets(20,0,0,20);
		c.gridx = 0;
		c.gridy = 1;
		panel1.add(caPassword,c);

		c.insets = new Insets(20,0,0,20);
		c.gridx = 1;
		c.gridy = 1;
		panel1.add(caPasswordTf,c);

		space.setVisible(false);
		c.insets = new Insets(20,0,0,20);
		c.gridx = 0;
		c.gridy = 2;
		panel1.add(space,c);

		capsLocksMessage.setVisible(false);
		c.insets = new Insets(20,0,0,20);
		c.gridx = 1;
		c.gridy = 2;
		panel1.add(capsLocksMessage,c);

		c.insets = new Insets(20,20,0,20);
		c.gridx = 0;
		c.gridy = 3;
		panel1.add(firstName,c);

		c.insets = new Insets(20,0,0,20);
		c.gridx = 1;
		c.gridy = 3;
		panel1.add(caFirstNameTf,c);

		c.insets = new Insets(20,20,0,20);
		c.gridx = 0;
		c.gridy = 4;
		panel1.add(lastName,c);

		c.insets = new Insets(20,0,0,20);
		c.gridx = 1;
		c.gridy = 4;
		panel1.add(caLastNameTf,c);

		c.insets = new Insets(20,0,0,20);
		c.gridx = 0;
		c.gridy = 5;
		panel1.add(caCreditCardNo,c);

		c.insets = new Insets(20,0,0,20);
		c.gridx = 1;
		c.gridy = 5;
		panel1.add(caCreditCardNoTf,c);

		c.insets = new Insets(20,60,0,0);
		c.gridx = 0;
		c.gridy = 6;
		panel1.add(back,c);

		c.insets = new Insets(20,40,0,0);
		c.gridx = 1;
		c.gridy = 6;
		panel1.add(createNewAccount,c);

		back.addActionListener(handler);
		createNewAccount.addActionListener(handler);

		caPasswordTf.addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent e) {

				boolean bla = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);

				if(bla == true)
				{
					capsLocksMessage.setText("Caps lock is on");
					capsLocksMessage.setForeground(Color.red);
					space.setVisible(true);
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
				// they do nothing but they must be inherited 

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// they do nothing but they must be inherited 

			}

		});
	}
	private class theHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{

			try
			{
				rset.next();
				if(e.getSource() == createNewAccount)
				{

					rset = db.getUsers();


					String newUsername = caUsernameTf.getText();
					String newPassword = new String(caPasswordTf.getText());
					String firstName = caFirstNameTf.getText();
					String lastName = caLastNameTf.getText();
					String CCheck = caCreditCardNoTf.getText();

					if(newUsername.equals("") || newPassword.equals("")  || firstName.equals("") || lastName.equals("") || CCheck.equals(""))
					{
						JOptionPane.showMessageDialog(null,"All field must be filled in","Error",JOptionPane.ERROR_MESSAGE);
					}

					else if(newUsername.length() < 5)
					{
						JOptionPane.showMessageDialog(null,"new Username is too short must be at least 5 charactors","Error",JOptionPane.ERROR_MESSAGE);
					}

					else if(newPassword.length() < 5 || newPassword.length() > 10)
					{
						JOptionPane.showMessageDialog(null,"password must be least 5 to 10 characters","Error",JOptionPane.ERROR_MESSAGE);
					}

					else if(!(CCheck.length() == 12))
					{
						JOptionPane.showMessageDialog(null,"Credit card no MUST be 12 digits Long","Error",JOptionPane.ERROR_MESSAGE);
					}
					else{

						int creditCardNo = 0;
						try
						{
							creditCardNo = Integer.parseInt(CCheck);
						}catch(NumberFormatException e5)
						{
							JOptionPane.showMessageDialog(null, "Only Enter Numbers in Credit card field","Credit card error",JOptionPane.WARNING_MESSAGE);
						}
		
						if(creditCardNo == 0)
						{
						
						}
						else
						{
							db.addAccount(2,newUsername, newPassword, firstName, lastName, creditCardNo);
						}
					}
				}
			}catch(SQLException e2){}
			if(e.getSource().equals(back))
			{
				db.closeDB();
				LoginPage x = new LoginPage();
				createUserAccountFrame.setVisible(false);

			}
		}

	}
}

package ie.ittd.year2.project.concert.adminClasses;

import ie.ittd.year2.project.concert.dbFiles.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
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


public class LoginPage
{

	dbConnect2 db = new dbConnect2();
	ResultSet rset;

	private JFrame loginPageFrame= new JFrame();

	private JLabel title = new JLabel("Welcome to Concert Booking System");
	private JLabel username = new JLabel("Username:");
	private JLabel password = new JLabel("Password:");
	private JLabel capsLocksMessage = new JLabel("");

	private Font f1 = new Font("Roman",Font.BOLD,20);

	private JButton createAccount = new JButton("Create Account");
	private JButton login = new JButton("Login");

	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();

	private JTextField usernameTf = new JTextField(16);
	private String titleUsername = usernameTf.getText();
	private JPasswordField passwordTf = new JPasswordField(16);

	private GridBagConstraints c = new GridBagConstraints();

	public static String usernameCopy;

	public LoginPage()
	{
		db.connect();
		loginPageFrame.setTitle("Concert System: Login Page");
		loginPageFrame.setSize(400,300);
		loginPageFrame.setLocation(500, 250);
		loginPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginPageFrame.getContentPane().setLayout(new BorderLayout());
		loginPageFrame.setVisible(true);


		panel1.setLayout(new GridBagLayout());
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));

		loginPageFrame.add(panel1,BorderLayout.CENTER);
		loginPageFrame.add(panel2,BorderLayout.NORTH);
		loginPageFrame.add(panel3,BorderLayout.SOUTH);
		
		panel1.setBackground(Color.white);
		panel2.setBackground(Color.white);
		panel3.setBackground(Color.white);

		title.setFont(f1);
		panel2.add(title);

		c.insets = new Insets(30,10,10,10);
		c.gridy = 0;
		panel1.add(username,c);	


		c.gridx = 0;
		c.gridy = 1;
		panel1.add(password,c);


		c.gridx = 1;
		c.gridy = 0;
		panel1.add(usernameTf,c);


		c.gridx = 1;
		c.gridy = 1;
		panel1.add(passwordTf,c);
		
		c.gridx = 1;
		c.gridy = 2;
		panel1.add(capsLocksMessage,c);

		panel3.add(createAccount);
		panel3.add(login);

		passwordTf.setFocusTraversalKeysEnabled(false);
		passwordTf.addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent e) {

					boolean _capLockToggle = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
					
					if(_capLockToggle == true)
					{
						capsLocksMessage.setText("Caps lock is on");
						capsLocksMessage.setForeground(Color.red);
					}
					else
					{
						capsLocksMessage.setText("");
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

		theHandler handler = new theHandler();
		login.addActionListener(handler);
		createAccount.addActionListener(handler);
	}

	private class theHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{

			if(e.getSource() == login)
			{

				String username = usernameTf.getText();
				String password = new String(passwordTf.getText());



				rset = db.getLoginDetails(username, password);

				if(username.equals("") || password.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please Enter a username AND password","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						rset.next();

						int accountType = rset.getInt(1);
						String i = rset.getString(2);
						String xy = rset.getString(3);
						usernameCopy = i.trim(); 
						String passwordCopy = xy.trim();


						if(!username.equals(usernameCopy) && !password.equals(passwordCopy)) 
						{
							rset.close();
						}
						else
						{
							confirmationScreens a = new confirmationScreens();

							if(accountType == 1)
							{
								a.loginConfirmation();
								AdminHomeScreen z = new AdminHomeScreen(usernameCopy);
								loginPageFrame.setVisible(false);
								loginPageFrame.dispose();
								rset.close();
								db.closeDB();
							}
							else if(accountType == 2)
							{
								a.loginConfirmation();
								loginPageFrame.dispose();
								UserHomeScreen x = new UserHomeScreen(usernameCopy);
								rset.close();
								db.closeDB();
							}

						}
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(null,"Incorrect login, wrong username or password","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			if(e.getSource() == createAccount)
			{
				db.closeDB();
				CreateUserAccount y = new CreateUserAccount();
				loginPageFrame.setVisible(false);
			}


		}

	}
	public static void main(String[] args)
	{
		new LoginPage();

	}
}

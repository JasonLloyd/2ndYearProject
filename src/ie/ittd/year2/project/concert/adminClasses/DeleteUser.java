package ie.ittd.year2.project.concert.adminClasses;

import ie.ittd.year2.project.concert.dbFiles.*;

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

public class DeleteUser 
{

	confirmationScreens x = new confirmationScreens();
	dbConnect2 db = new dbConnect2();
	theHandler handler = new theHandler();
	ResultSet rset;

	int i = 0;


	private JTextField deleteusersTf = new JTextField(16);

	private JButton deleteUser = new JButton("Delete user");
	private JButton back = new JButton("Back");

	private GridBagLayout gbl = new GridBagLayout();

	private GridBagConstraints c = new GridBagConstraints();

	private JFrame deleteUserFrame = new JFrame();

	//panels
	private JPanel panel1 = new JPanel(gbl);
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();

	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,25);
	
	JTable t1;
	JScrollPane scrollPane;
	
	public DeleteUser()
	{
		db.connect();
		t1 = db.usersTable();
		scrollPane = new JScrollPane(t1);
		
		JLabel viewUsersTitle = new JLabel("View Users");
		JLabel userToDelete = new JLabel("Enter username you want to delete");

		deleteUserFrame.setTitle("View Users");
		deleteUserFrame.setSize(630,440);
		deleteUserFrame.setLocation(500, 250);
		deleteUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deleteUserFrame.getContentPane().setLayout(new BorderLayout());
		deleteUserFrame.setVisible(true);

		deleteUserFrame.add(panel1,BorderLayout.CENTER);
		deleteUserFrame.add(panel2,BorderLayout.NORTH);
		deleteUserFrame.add(panel3,BorderLayout.SOUTH);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel1.setBackground(Color.white);
		panel2.setBackground(Color.white);
		panel3.setBackground(Color.white);

		viewUsersTitle.setFont(f2);
		panel2.add(viewUsersTitle);

		c.insets = new Insets(0,0,0,0);
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(userToDelete,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		panel1.add(deleteusersTf,c);

		c.insets = new Insets(50,0,0,0);
		c.gridx = 1;
		c.gridy = 2;
		panel1.add(back,c);

		c.insets = new Insets(50,350,0,0);
		c.gridx = 0;
		c.gridy = 2;
		panel1.add(deleteUser,c);

		panel3.add(scrollPane);

		deleteUser.addActionListener(handler);
		back.addActionListener(handler);
	}
	private class theHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton pushedButton = (JButton) (e.getSource());

			if(pushedButton.equals(deleteUser))
			{
				String deleteUser = deleteusersTf.getText();

				if(deleteUser.equals(""))
				{
					x.fieldsNotFilledIn();
				}

				else
				{
				
					try{
						rset = db.getUsers();
						rset.next();

						String usernames = "";

						usernames = rset.getString(2);
						usernames = usernames.trim();


						db.deleteAccount(deleteUser);
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
				if(pushedButton.equals(back))
				{
					db.closeDB();
					deleteUserFrame.setVisible(false);
					AdminHomeScreen x = new AdminHomeScreen(LoginPage.usernameCopy);

				}

			}
		}
	public static void main(String args[])
	{
		new DeleteUser();
	}
	}

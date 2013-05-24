package ie.ittd.year2.project.concert.adminClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
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

import ie.ittd.year2.project.concert.dbFiles.*;


public class DeleteConcert 
{
	dbConnect2 db = new dbConnect2();
	ResultSet rset;
	theHandler handler = new theHandler();
	confirmationScreens z = new confirmationScreens();

	private GridBagLayout gbl = new GridBagLayout();

	private JTextField codeField = new JTextField(16);

	private JButton deleteConcert = new JButton("Delete Concert");
	private JButton back = new JButton("Back");

	private GridBagConstraints c = new GridBagConstraints();

	private JFrame deleteConcertFrame = new JFrame();
	
	private JPanel panel1 = new JPanel(gbl);
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();

	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,25);

	int i = 0;

	public DeleteConcert()
	{
		db.connect();

		JTable t1 = db.concertTable();

		JScrollPane scrollPane = new JScrollPane(t1);

		JLabel title = new JLabel("Delete Concert");
		JLabel code = new JLabel("Enter concert code to delete concert from system:");

		deleteConcertFrame.setTitle("Delete Concert");
		deleteConcertFrame.setSize(540,400);
		deleteConcertFrame.setLocation(500, 250);
		deleteConcertFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deleteConcertFrame.getContentPane().setLayout(new BorderLayout());
		deleteConcertFrame.setVisible(true);

		deleteConcertFrame.add(panel1,BorderLayout.CENTER);
		deleteConcertFrame.add(panel2,BorderLayout.NORTH);
		deleteConcertFrame.add(panel3,BorderLayout.SOUTH);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setLayout(new BorderLayout());
		panel1.setBackground(Color.white);
		panel2.setBackground(Color.white);
		
		title.setFont(f2);
		panel2.add(title);

		c.insets = new Insets(0,0,0,0);
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(code,c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		panel1.add(codeField,c);

		c.insets = new Insets(50,350,0,0);
		c.gridx = 0;
		c.gridy = 2;
		panel1.add(deleteConcert,c);

		c.insets = new Insets(50,0,0,0);
		c.gridx = 1;
		c.gridy = 2;
		panel1.add(back,c);

		deleteConcert.addActionListener(handler);
		back.addActionListener(handler);

		panel3.add(scrollPane);
	}

	private class theHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton pushedButton = (JButton) (e.getSource());

			if(pushedButton.equals(deleteConcert))
			{	
				rset = db.getConcerts();

				String deleteConcert = codeField.getText();

				if(deleteConcert.equals("") || deleteConcert.equals(null))
				{
					z.fieldsNotFilledIn();
				}
				else
				{
					int deleteConcertCode = 0;
					try
					{
						deleteConcertCode = Integer.parseInt(deleteConcert);
						db.deleteConcert(deleteConcertCode);
					}catch(NumberFormatException e1)
					{
						JOptionPane.showMessageDialog(null, "The concert code must be only numbers","Delete concert error",JOptionPane.WARNING_MESSAGE);
					}

					
				}
			} 
			if(pushedButton.equals(back))
			{
				db.closeDB();
				deleteConcertFrame.setVisible(false);
				AdminHomeScreen x = new AdminHomeScreen(LoginPage.usernameCopy);

			}
		}
	}
}

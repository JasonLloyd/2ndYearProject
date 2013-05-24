package ie.ittd.year2.project.concert.userClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import ie.ittd.year2.project.concert.adminClasses.*;

import ie.ittd.year2.project.concert.dbFiles.*;

public class allConcerts 
{
	dbConnect2 db = new dbConnect2();
	theHandler handler = new theHandler();

	private GridBagLayout gbl = new GridBagLayout();

	public JFrame allConcertsframe = new JFrame();

	private JPanel panel1 = new JPanel(gbl);
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	
	private JButton back = new JButton("back");

	private String[] buttonlabels = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V"
			,"W","X","Y","Z"};

	private JButton[] buttons = new JButton[27];

	private JLabel[] nameResults = new JLabel[10];

	private String[] bookButtonLabels = {"Book","Book","Book","Book","Book","Book","Book","Book","Book"};
	private ArrayList<JButton> bookButtons = new ArrayList<JButton>();
	
	private Font f3 = new Font("Roman",Font.BOLD,20);
	private Font f4 = new Font("Roman",Font.BOLD,10);

	private GridBagConstraints c = new GridBagConstraints();

	public allConcerts()
	{
		db.connect();	
		allConcertsframe.setTitle("Concert System: All Concerts");
		allConcertsframe.setSize(800,500);
		allConcertsframe.setLocation(300, 250);
		allConcertsframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		allConcertsframe.getContentPane().setLayout(new BorderLayout());
		allConcertsframe.getContentPane().setBackground(Color.WHITE);
		allConcertsframe.setVisible(true);
		allConcertsframe.add(panel1,BorderLayout.WEST);
		allConcertsframe.add(panel2,BorderLayout.NORTH);
		allConcertsframe.add(panel3,BorderLayout.SOUTH);
		
		panel1.setBackground(Color.WHITE);
		panel2.setBackground(Color.WHITE);
		panel3.setBackground(Color.WHITE);
		
		panel1.setLayout(gbl);
		panel2.setLayout(new GridLayout(2,14,10,3));
		panel2.setBackground(Color.white);
		panel2.setBorder(BorderFactory.createRaisedBevelBorder());
		panel3.setLayout(new FlowLayout());

		for (int i=0;i<buttons.length - 1;i++)
		{
			buttons[i]=new JButton(buttonlabels[i]);
			buttons[i].setFont(f4);
			panel2.add(buttons[i]);
		}

		for (int i=0;i<nameResults.length;i++)
		{
			nameResults[i] = new JLabel("");
			nameResults[i].setFont(f3);
			c.insets = new Insets(20,20,0,0);
			c.gridx = 0;
			c.gridy = i + 1;
			panel1.add(nameResults[i],c);

		}

		panel3.add(back);

		for(int i =0;i<buttons.length-1;i++)
		{
			buttons[i].addActionListener(handler);
		}
		back.addActionListener(handler);
	}

	private class theHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton pushed = (JButton) e.getSource();

			ResultSet rset = null;

			for(int i = 0;i<buttons.length;i++)
			{
				if(pushed.equals(buttons[i]))
				{
					for (int j = 0; j < 10; j++)
					{
						nameResults[j].setText("");
					}
					
					for (int q = 0; q< bookButtons.size(); q++)
					{
						panel1.remove(bookButtons.get(q));
					}
					
					bookButtons.clear();
						
					rset = db.getAllConcerts(buttonlabels[i]);
					
					try {
						if(rset.next() == true)
						{
							int z = 0;
							do
							{

								nameResults[z].setText(rset.getString(1));

								bookButtons.add(new JButton(bookButtonLabels[z]));
								c.insets = new Insets(40,20,20,0);
								c.gridx = 1;
								c.gridy = z +1;
								panel1.add(bookButtons.get(z),c);
								
								z++;
								
							}while(rset.next());

							for(int iy = 0;iy<z;iy++)
							{
								bookButtons.get(iy).addActionListener(new ActionListener() { 
									public void actionPerformed(ActionEvent e) 
									{ 
										String concertName = "";
										String newconcertName = "";

										for(int i = 0;i<bookButtons.size();i++)
										{
											if(e.getSource().equals(bookButtons.get(i)))
											{
												concertName = nameResults[i].getText().trim();

											}
											newconcertName = concertName.trim();
										}
										try {
											db.closeDB();
											BookConcertScreen z = new BookConcertScreen(newconcertName,2);
											allConcertsframe.setVisible(false);
										} catch (SQLException e1) 
										{
											System.out.println("error2");
										}
									} 
								});
							}
						}
						else
						{
							nameResults[0].setText("No concerts Found");
						}
					}
					catch(SQLException e2)
					{
						e2.printStackTrace();
					}
				}
			}

			if(pushed.equals(back))
			{
				
				for (int j = 0; j < 10; j++)
				{
					nameResults[j].setText("");
				}
				
				for (int q = 0; q< bookButtons.size(); q++)
				{
					panel1.remove(bookButtons.get(q));
				}
				bookButtons.clear();
				
				UserHomeScreen x = new UserHomeScreen(LoginPage.usernameCopy);
				allConcertsframe.setVisible(false);
				db.closeDB();

			}
		}
	}
}

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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import ie.ittd.year2.project.concert.adminClasses.*;

import ie.ittd.year2.project.concert.dbFiles.*;

public class upcomingConcerts
{
	dbConnect2 db = new dbConnect2();
	theHandler handler = new theHandler();
	ResultSet rset;

	private GridBagLayout gbl = new GridBagLayout();

	static JFrame upcomingConcertsFrame = new JFrame();

	private JPanel panel1 = new JPanel(gbl);
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();

	private JButton back = new JButton("back");

	private JLabel title = new JLabel("Title");

	JLabel UpcomingCon = new JLabel("Upcoming Concerts");
	JLabel location = new JLabel("Location");
	JLabel date = new JLabel("Date");

	JLabel no = new JLabel("No#");

	Border ethed = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

	private Font f2 = new Font("Roman",Font.BOLD + Font.ITALIC,25);
	private Font f3 = new Font("Roman",Font.BOLD,20);


	GridBagConstraints c = new GridBagConstraints();

	JLabel[] numbers = new JLabel[10];
	JLabel[] titleLabels = new JLabel[9];
	JLabel[] locationLabels = new JLabel[9];
	JLabel[] dateLabels = new JLabel[9];

	String[] bookButtonLabels = {"Book","Book","Book","Book","Book","Book","Book","Book","Book"};
	JButton bookButtons[] = new JButton[10];

	public upcomingConcerts() throws SQLException
	{
		db.connect();
		rset = db.getUpcomingConcerts();

		upcomingConcertsFrame.setTitle("Concert System: Upcoming Concerts");
		upcomingConcertsFrame.setSize(750,600);
		upcomingConcertsFrame.setLocation(500, 250);
		upcomingConcertsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		upcomingConcertsFrame.getContentPane().setLayout(new BorderLayout());
		upcomingConcertsFrame.getContentPane().setBackground(Color.white);
		upcomingConcertsFrame.setVisible(true);

		upcomingConcertsFrame.add(panel1,BorderLayout.WEST);
		upcomingConcertsFrame.add(panel2,BorderLayout.NORTH);
		upcomingConcertsFrame.add(panel3,BorderLayout.SOUTH);
		
		panel1.setBackground(Color.white);
		panel2.setBackground(Color.WHITE);
		panel3.setBackground(Color.WHITE);
		
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		panel2.setBorder(ethed);

		UpcomingCon.setFont(f2);
		panel2.add(UpcomingCon);

		panel1.setLayout(gbl);

		no.setFont(f3);
		c.insets = new Insets(0,20,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panel1.add(no,c);

		title.setFont(f3);
		c.insets = new Insets(0,20,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		panel1.add(title,c);

		location.setFont(f3);
		c.insets = new Insets(0,150,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		panel1.add(location,c);

		date.setFont(f3);
		c.insets = new Insets(0,100,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		panel1.add(date,c);

		String[] spaces = {"1","2","3","4","5","6","7","8","9","10"};		

		rset.next();

		int i = 0;
		int z = 0;
		do
		{
			numbers[i]=new JLabel(spaces[i]);
			c.insets = new Insets(0,30,10,0);
			c.gridx = 0;
			c.gridy = i + 1;
			panel1.add(numbers[i],c);

			titleLabels[i]=new JLabel(rset.getString(2).trim());
			c.insets = new Insets(0,30,10,0);
			c.gridx = 1;
			c.gridy = i + 1;
			panel1.add(titleLabels[i],c);

			locationLabels[i]=new JLabel(rset.getString(3).trim());
			c.insets = new Insets(0,150,10,0);
			c.gridx = 2;
			c.gridy = i + 1;
			panel1.add(locationLabels[i],c);

			Date d1 = rset.getDate(5);

			String x = d1.toString();

			dateLabels[i]=new JLabel(x);
			c.insets = new Insets(0,100,10,0);
			c.gridx = 3;
			c.gridy = i + 1;
			panel1.add(dateLabels[i],c);

			bookButtons[i] = new JButton(bookButtonLabels[i]);
			c.insets = new Insets(0,40,10,0);
			c.gridx = 5;
			c.gridy = i + 1;
			panel1.add(bookButtons[i],c);
			
			bookButtons[z++].addActionListener(handler);
			i++;
			
		}while(rset.next());

		panel3.add(back);

		back.addActionListener(handler);
	}

	private class theHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton pushed = (JButton) e.getSource();

			for(int i =0;i<bookButtons.length;i++)
			{
				if(pushed.equals(bookButtons[i]))
				{
					String concertName = titleLabels[i].getText();
					String newconcertName = concertName.trim();

					try {
						db.closeDB();
						BookConcertScreen z = new BookConcertScreen(newconcertName,1);
						upcomingConcertsFrame.setVisible(false);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					i++;
				}
				
			}
			if(pushed.equals(back))
			{
				db.closeDB();
				UserHomeScreen x = new UserHomeScreen(LoginPage.usernameCopy);
				upcomingConcertsFrame.setVisible(false);
			}

		}
	}
	public static void main(String[] args) throws SQLException
	{
		new upcomingConcerts();

	}
}

package ie.ittd.year2.project.concert.dbFiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ie.ittd.year2.project.concert.userClasses.*;

import oracle.jdbc.driver.OracleDriver;

public class dbConnect2 {

	confirmationScreens x = new confirmationScreens();
	PreparedStatement pstmt;
	ResultSet rset;
	Connection conn;
	
	public String usernamesLoggedIn;
	public String passwordOfUser;
	int creditCardNoOfUser;
	
	//this method connects to the database
	public void connect()
	{

		try{
			DriverManager.registerDriver(new OracleDriver());
			String url = "jdbc:oracle:oci:@global1";
			String user = "X00074511";
			String pass = "db29Apr91";

			conn = DriverManager.getConnection(url, user, pass);

		}

		catch (SQLException e)
		{
			System.out.print("Could not connect " + e);
			System.exit(1);
		}

	}

	//this method gets all the users from the database
	public ResultSet getUsers()
	{
		try
		{
			String queryString = "SELECT * FROM users";

			pstmt = conn.prepareStatement(queryString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();
		}

		catch (SQLException e)
		{
			System.out.println("Could not get Users");
		}

		return rset;
	}
	
	//this method gets a particular users details
	public ResultSet getUsersDetails(String username)
	{
		try
		{
			String queryString = "SELECT * FROM users WHERE username = '"+username+"'";

			pstmt = conn.prepareStatement(queryString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();
		}

		catch (SQLException e)
		{
			System.out.println("Could not get Users");
		}

		return rset;
	}
	
	//this method gets all the concerts from the database
	public ResultSet getConcerts()
	{
		try
		{
			String getConcerts = "Select * FROM concert";

			pstmt = conn.prepareStatement(getConcerts, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();
		}

		catch (SQLException e)
		{
			System.out.println("Could not get Concerts");
		}

		return rset;

	}

	//this gets all the concerts from the database starting with a particular letter 
	public ResultSet getAllConcerts(String x)
	{
		try
		{
			String y = x.toLowerCase();
			String getConcerts = "Select concertName FROM concert WHERE (concertName like '"+x+"%' OR concertName like '"+y+"%') AND concertAvailable = 'Yes'";

			pstmt = conn.prepareStatement(getConcerts, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();
			
		}

		catch (SQLException e)
		{
			System.out.println("Could not get Concerts starting with " + x);
			e.printStackTrace();
		}

		return rset;

	}
	
	//this gets all the concerts from the database before a certain date
	public ResultSet getUpcomingConcerts() 
	{
		try
		{
			String getConcerts = "Select * FROM concert where dateofconcert < '22-MAY-11'  AND concertavailable = 'Yes'";

			pstmt = conn.prepareStatement(getConcerts, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();
		}

		catch (SQLException e)
		{
			System.out.println("Could not get Concerts");
		}

		return rset;

	}
	
	//this get all the details for a concert from the database starting with a certain concert name
	public ResultSet getConcertsInformation(String concertName)
	{
		try
		{
			String lowercaseConcertName = concertName.toLowerCase();
			String getConcerts = "Select * FROM concert WHERE concertName ='"+concertName+"' OR concertName ='"+lowercaseConcertName+"'";

			pstmt = conn.prepareStatement(getConcerts, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();
			
		}

		catch (SQLException e)
		{
			System.out.println("Could not get Concerts with name " + concertName);
		}

		return rset;

	}
	

	//this method gets the details of a user that has logged into the system.
	public ResultSet getLoginDetails(String username,String password)
	{
		try
		{
			String queryString = "SELECT accountType,username,passwords FROM users where username='" +username+"' and passwords='" +password+"'";

			pstmt = conn.prepareStatement(queryString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();
			
			usernamesLoggedIn = username;
			passwordOfUser = password;
			
		}

		catch (SQLException e)
		{
			System.out.println("Usernmae or password was incorrect");
		}
		return rset;
	}

	//this method add a new user to the database
	public void addAccount(int accountType,String username, String password, String firstName, String lastName,int creditCardNo )
	{
		try
		{
			
			String queryString= "Select accountType,username,passwords,firstName,lastName,creditCardNo from users";

			pstmt = conn.prepareStatement(queryString,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);            

			rset = pstmt.executeQuery();

			rset.moveToInsertRow();                      

			// update column values
			rset.updateInt(1, accountType);
			rset.updateString(2, username);
			rset.updateString(3, password);
			rset.updateString(4, firstName); 
			rset.updateString(5, lastName); 
			rset.updateInt(6, creditCardNo); 
			rset.insertRow();
			pstmt.executeUpdate();
			x.caConfirmation();
			conn.commit();
			
		}


		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null,"Sorry but that username already exists","Username already exists",JOptionPane.ERROR_MESSAGE);
		}

	}

	//this method deletes a user from the database
	public void deleteAccount(String username)
	{
		try{
			String deleteAccount = "Select username from users where username = '"+username+"'";

			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(deleteAccount,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);            

			rset = pstmt.executeQuery();

			if (rset.next())                      	
			{		
				rset.deleteRow();                 		    
				x.deleteUserConfirmation();
				conn.commit();
			}  
			else
			{
				JOptionPane.showMessageDialog(null, "User doesnt exist","Username error", JOptionPane.WARNING_MESSAGE);
			}
		}             

		catch (SQLException e)
		{
			System.out.print("User could not be deleted " + e);
		}            
	}

	//this method add a concert to the database
	public void addConcert(int concertCode,String concertName,String concertType,String concertLocation,Date dateOfConcert,double ticketPrice,String concertAvailable)
	{	
		try
		{
			String addConcert = "Select concertCode,concertName,concertType,concertLocation,dateOfConcert,ticketPrice,concertAvailable FROM concert";

			pstmt = conn.prepareStatement(addConcert,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);            

			rset = pstmt.executeQuery();

			rset.moveToInsertRow();    
			rset.updateInt(1, concertCode);
			rset.updateString(2, concertName);
			rset.updateString(3, concertType); 
			rset.updateString(4, concertLocation); 
			rset.updateDate(5, dateOfConcert); 
			rset.updateDouble(6, ticketPrice);
			rset.updateString(7, concertAvailable);
			rset.insertRow();
			pstmt.executeUpdate();
			x.addConcertConfirmation();
			conn.commit();
		}

		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Concert Code already exists","Concert code error", JOptionPane.WARNING_MESSAGE);
		}
	}

	//this method deletes a concert from the database
	public void deleteConcert(int concertCodeCheck)
	{
		try{
			conn.setAutoCommit(false); 

			String deleteString = "select concertCode from concert where concertCode = '"+concertCodeCheck+"'";

			pstmt = conn.prepareStatement(deleteString, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);        


			rset = pstmt.executeQuery();


			if (rset.next())                      
			{		
				rset.deleteRow();                 	
				x.deleteConcertConfirmation();
				conn.commit();
			}  
			else
			{
				x.deleteConcertFailure();
			}
		}             

		catch (SQLException e)
		{
			System.out.print("Concert could not be deleted " + e);
			System.exit(1);
		}

	}

	//this method updates an existing concert in the database
	public void updateConcert(int concertCode,String concertName,String concertType,String concertLocation,Date dateOfConcert,double ticketPrice,String concertAvailable)
	{
		try
		{
			conn.setAutoCommit(false);   
			String queryString= "select concertCode,concertName,concertType,concertLocation, dateOfConcert,ticketPrice,concertAvailable from concert where concertCode = "+concertCode+"";
			pstmt = conn.prepareStatement(queryString,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);            
			rset = pstmt.executeQuery();


			if (rset.next())                                  
			{
				rset.updateInt(1, concertCode);
				rset.updateString(2, concertName);
				rset.updateString(3, concertType);
				rset.updateString(4, concertLocation);
				rset.updateDate(5, dateOfConcert);
				rset.updateDouble(6, ticketPrice);
				rset.updateString(7, concertAvailable);
				rset.updateRow(); 
			}


			conn.commit();     
			x.updateConcertConfirmation();
		}


		catch (SQLException e)
		{
			System.out.println("could update concert" + e);
		}
	}

	//this method get the details of a particular user so they can change their details.
	public void changeDetails(String username, String password, int creditCardNo)
	{
		try
		{
			String queryString= "select username,passwords,creditCardNo from users where username = '"+username+"'";
			pstmt = conn.prepareStatement(queryString,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);            
			rset = pstmt.executeQuery();


			if (rset.next())                                   
			{
				rset.updateString(2, password);
				rset.updateInt(3, creditCardNo);
				rset.updateRow(); 
			}

			x.changeDetailsConfirmation();
			conn.commit();                                       
			
		}


		catch (SQLException e)
		{
			x.changeDetailsFailure();
		}
	}

	//check to see if method is used
	public ResultSet getConcertsToChange(int concertCodeToUpdate) 
	{
		try
		{
			String getConcertsToChange = "Select * from concert WHERE concertCode = '"+concertCodeToUpdate+"'";

			pstmt = conn.prepareStatement(getConcertsToChange, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				return rset;
				
			}
			else
			{
				System.out.println("concert doesnt exist");
			}
			
		}

		catch (SQLException e)
		{
			System.out.println("Could not get concerts with concertcode" + concertCodeToUpdate);
		}

		return rset;

	}

	//this method get all the information from the sales table in the database
	public ResultSet getAllPurchases() 
	{
		try{

			String getPurchases = "Select * FROM sales";

			pstmt = conn.prepareStatement(getPurchases, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();

		}
		catch (SQLException e)
		{
			System.out.println("Could not get Sales");
		}

		return rset;
	}
	
	//this method get the purchases for a partiular user.
	public ResultSet getUserPurchases(String username) 
	{
		try{

			String getPurchases = "Select * FROM sales WHERE username ='"+username+"'";

			pstmt = conn.prepareStatement(getPurchases, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();

		}
		catch (SQLException e)
		{
			System.out.println("Could not get Sales for " + username);
		}

		return rset;
	}
	
	public ResultSet getUserPurchases2(String username,int concertCode) 
	{
		try{

			String getPurchases = "Select * FROM sales WHERE username ='"+username+"' AND concertCode = '"+concertCode+"'";

			pstmt = conn.prepareStatement(getPurchases, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();

		}
		catch (SQLException e)
		{
			System.out.println("Could not get Sales for " + username);
		}

		return rset;
	}
	
	//this method adds a sale to the database.
	public void addSale(String username,int concertCode, int purchaseCode, int noOfTicketsOrdered , int creditCardNo, double moneyPaid, String concertName, String concertType, String concertLocation, String dateOfConcert) //need to enter all information into sale table
	{
		try
		{
			
			String queryString= "Select username,concertCode,purchaseCode,noOfTicketsOrdered,creditCardNo,moneyPaid from sales";

			pstmt = conn.prepareStatement(queryString,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);            

			rset = pstmt.executeQuery();

			rset.moveToInsertRow();                      

			// update column values
			rset.updateString(1, username);
			rset.updateInt(2, concertCode);
			rset.updateInt(3, purchaseCode);
			rset.updateInt(4, noOfTicketsOrdered); 
			rset.updateInt(5, creditCardNo); 
			rset.updateDouble(6, moneyPaid); 
			rset.insertRow();
			pstmt.executeUpdate();
			conn.commit();
			
			BookConcertScreen.confirmationScreen(purchaseCode, concertCode, concertName, concertType, concertLocation, dateOfConcert, noOfTicketsOrdered, moneyPaid);
			
		}


		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null,"sale error","sale error",JOptionPane.ERROR_MESSAGE);
		}

	}
	
	//this method counts the amount of users in the system.
	public ResultSet countUsers() 
	{
		try{

			String getPurchases = "Select COUNT(username) FROM users";

			pstmt = conn.prepareStatement(getPurchases, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();

		}
		catch (SQLException e)
		{
			System.out.println("Connot count users");
		}

		return rset;
	}
	
	//this method counts the concerts that are in the system.
	public ResultSet countConcerts() 
	{
		try{

			String getPurchases = "Select COUNT(concertCode) FROM concert";

			pstmt = conn.prepareStatement(getPurchases, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();

		}
		catch (SQLException e)
		{
			System.out.println("Connot count concerts");
		}

		return rset;
	}
	
	//this method counts the amount of purchases made in the system and totals up the amount of money each booking has made.
	public ResultSet countBooking() 
	{
		try{

			String getPurchases = "select COUNT(purchaseCode),sum(moneyPaid) from sales";
			
			pstmt = conn.prepareStatement(getPurchases, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    

			rset = pstmt.executeQuery();

		}
		catch (SQLException e)
		{
			System.out.println("Connot count purchaseCode and moneyMade");
		}

		return rset;
	}
	
	// this method gets all the information from the users table and returns this information in a JTable
	public JTable usersTable()
	{
		
		// most of this code has been taken from this website: http://tips4java.wordpress.com/2009/03/12/table-from-database/  
		//it has almost been modified and adapted so it can work with our program
		Vector columnNames = new Vector();
		Vector data = new Vector();

		try
		{
			//  Read data from a table

			String sql = "Select accountType,username,firstname,lastname from users ORDER BY accountType ASC";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery( sql );
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();

			//  Get column names

			for (int i = 1; i <= columns; i++)
			{
				columnNames.addElement( md.getColumnName(i) );
			}

			//  Get row data

			while (rs.next())
			{
				Vector row = new Vector(columns);

				for (int i = 1; i <= columns; i++)
				{
					row.addElement( rs.getObject(i) );
				}

				data.addElement( row );
			}

			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println( e );
		}

		//  Create table with database data

		JTable table = new JTable(data, columnNames)
		{
			public Class getColumnClass(int column)
			{
				for (int row = 0; row < getRowCount(); row++)
				{
					Object o = getValueAt(row, column);

					if (o != null)
					{
						return o.getClass();
					}
				}

				return Object.class;
			}
		};

		DefaultTableModel model = new DefaultTableModel(data,columnNames);
		table = new JTable(model)
		{
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;   //Disallow the editing of any cell
			}

		};
		//////////////////////////////////////////////////////////////////////
		JTableHeader header = table.getTableHeader();

		header.setBackground(Color.yellow);
		table.setPreferredScrollableViewportSize(new Dimension(600,90));
		table.setFillsViewportHeight(true);
		return table;
	}

	// this method gets all the information from the concert table and returns this information in a JTable
	public JTable concertTable()
	{
		// most of this code has been taken from this website: http://tips4java.wordpress.com/2009/03/12/table-from-database/  
		//it has almost been modified and adapted so it can work with our program
		
		Vector columnNames = new Vector();
		Vector data = new Vector();

		try
		{
			//  Connect to an Access Database
			//  Read data from a table

			String sql = "Select * from concert ORDER BY concertName";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery( sql );
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();

			//  Get column names

			for (int i = 1; i <= columns; i++)
			{
				columnNames.addElement( md.getColumnName(i) );
			}

			//  Get row data

			while (rs.next())
			{
				Vector row = new Vector(columns);

				for (int i = 1; i <= columns; i++)
				{
					row.addElement( rs.getObject(i) );
				}

				data.addElement( row );
			}

			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println( e );
		}

		//  Create table with database data

		JTable table = new JTable(data, columnNames)
		{
			public Class getColumnClass(int column)
			{
				for (int row = 0; row < getRowCount(); row++)
				{
					Object o = getValueAt(row, column);

					if (o != null)
					{
						return o.getClass();
					}
				}

				return Object.class;
			}
		};

		DefaultTableModel model = new DefaultTableModel(data,columnNames);
		table = new JTable(model)
		{
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;   //Disallow the editing of any cell
			}

		};

		JTableHeader header = table.getTableHeader();

		header.setBackground(Color.yellow);
		table.setPreferredScrollableViewportSize(new Dimension(600,90));
		table.setFillsViewportHeight(true);
		return table;
	}
	
	// this method closes the database connection 
	public void closeDB()
	{   
		try{

			conn.close();
		}
		catch(SQLException e)
		{
			System.out.println("Problem closing the database");
		}
	}
}





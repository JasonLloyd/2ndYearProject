package ie.ittd.year2.project.concert.dbFiles;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class confirmationScreens 
{
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	
	JButton north = new JButton("North");
	JButton center = new JButton("Center");
	JButton south = new JButton("South");
	
	ImageIcon confirmationIcon = new ImageIcon("images/Green_Tick.jpg");
	ImageIcon failureIcon = new ImageIcon("images/cross.png");
	JFrame frame6 = new JFrame();
	
	public confirmationScreens()
	{
//		loginConfirmation();
//		loginFailure();
//		caConfirmation();
//		caFailure();
//		changeDetailsConfirmation();
//		changeDetailsFailure();
//		bookConcertConfirmation();
//		bookConcertFailure();
//		logoutConfirmation();
//		addConcertConfirmation();
//		addConcertFailure();
//		updateConcertConfirmation();
//		upadteConcertFailure();
//		deleteConcertConfirmation();
//		deleteConcertFailure();
//		deleteUserConfirmation();
//		deleteUserFailure();
		//basicLayout();
	}
	
	public void loginConfirmation()
	{	
		JOptionPane.showMessageDialog(frame6, "Thank you for login","Login Successful", JOptionPane.PLAIN_MESSAGE,confirmationIcon);
	}
	
	public void loginFailure()
	{	
		JOptionPane.showMessageDialog(frame6, "Login Failed","Login Failed", JOptionPane.PLAIN_MESSAGE,failureIcon);
	}
	
	public void caConfirmation()
	{	
		JOptionPane.showMessageDialog(frame6, "Thank you for creating an account","Create an account successful", JOptionPane.PLAIN_MESSAGE,confirmationIcon);
	}
	
	public void caFailure()
	{	
		JOptionPane.showMessageDialog(frame6, "Create account failed","Create account failed", JOptionPane.PLAIN_MESSAGE,failureIcon);
	}
	
	public void changeDetailsConfirmation()
	{	
		JOptionPane.showMessageDialog(frame6, "Your details are now changed","Change account details sucessful", JOptionPane.PLAIN_MESSAGE,confirmationIcon);
	}
	
	public void changeDetailsFailure()
	{	
		JOptionPane.showMessageDialog(frame6, "Change details failed","Change details failed", JOptionPane.PLAIN_MESSAGE,failureIcon);
	}
	
	public void bookConcertConfirmation()
	{	
		JOptionPane.showMessageDialog(frame6, "Thank you for booking concert","Booking concert sucessful", JOptionPane.PLAIN_MESSAGE,confirmationIcon);
	}
	
	public void  bookConcertFailure()
	{	
		JOptionPane.showMessageDialog(frame6, "Couldnt book concert","Booking concert failed", JOptionPane.PLAIN_MESSAGE,failureIcon);
	}
	
	public void logoutConfirmation()
	{	
		JOptionPane.showMessageDialog(frame6, "Thank you for using our concert booking system","Logout sucessful", JOptionPane.PLAIN_MESSAGE,confirmationIcon);
	}
	
	public void addConcertConfirmation()
	{	
		JOptionPane.showMessageDialog(frame6, "Added concert to the database","Adding concert sucessful", JOptionPane.PLAIN_MESSAGE,confirmationIcon);
	}
	
	public void addConcertFailure()
	{	
		JOptionPane.showMessageDialog(frame6, "Could not add concert to database","Adding concert failure", JOptionPane.PLAIN_MESSAGE,failureIcon);
	}
	
	public void updateConcertConfirmation()
	{	
		JOptionPane.showMessageDialog(frame6, "Updated concert","Concert Updated", JOptionPane.PLAIN_MESSAGE,confirmationIcon);
	}
	
	public void upadteConcertFailure()
	{	
		JOptionPane.showMessageDialog(frame6, "Could not update concert","Concert update failed", JOptionPane.PLAIN_MESSAGE,failureIcon);
	}
	
	public void deleteConcertConfirmation()
	{	
		JOptionPane.showMessageDialog(frame6, "Deleted concert","delete concert sucessful", JOptionPane.PLAIN_MESSAGE,confirmationIcon);
	}
	
	public void deleteConcertFailure()
	{	
		JOptionPane.showMessageDialog(frame6, "Could not delete concert","Delete concert failed", JOptionPane.PLAIN_MESSAGE,failureIcon);
	}
	
	public void deleteUserConfirmation()
	{	
		JOptionPane.showMessageDialog(frame6, "Deleted User","Deleted user sucessful", JOptionPane.PLAIN_MESSAGE,confirmationIcon);
	}
	
	public void deleteUserFailure()
	{	
		JOptionPane.showMessageDialog(frame6, "Could not delete user","Deleted user failed", JOptionPane.PLAIN_MESSAGE,failureIcon);
	}
	
	public void usernameTooShort()
	{
		JOptionPane.showMessageDialog(frame6,"new Username is too short must be at least 5 charactors","Error",JOptionPane.PLAIN_MESSAGE,failureIcon);
	}
	
	public void passwordTooShort()
	{
		JOptionPane.showMessageDialog(frame6,"password must be least 5 to 10 characters","Error",JOptionPane.PLAIN_MESSAGE,failureIcon);
	}
	
	public void fieldsNotFilledIn()
	{
		JOptionPane.showMessageDialog(frame6,"All field must be filled in","Error",JOptionPane.PLAIN_MESSAGE,failureIcon);
	}
	
	public void creditCardTooShort()
	{
		JOptionPane.showMessageDialog(frame6,"Your credit card number must be 12 numbers","Error",JOptionPane.PLAIN_MESSAGE,failureIcon);
	}
	
	public void creditCardError()
	{
		JOptionPane.showMessageDialog(frame6,"Your credit card number must be 12 numbers it cannot contain letters or special charactors","Error",JOptionPane.PLAIN_MESSAGE,failureIcon);
	}
	
	public static void main(String[] args)
	{
		confirmationScreens x = new confirmationScreens();
		
	}
	
	
}

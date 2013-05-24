package ie.ittd.year2.project.concert.userClasses;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class submitFeedback 
{
	public submitFeedback()
	{
		// http://www.kodejava.org/examples/619.html

		Desktop desktop = Desktop.getDesktop();
		try 
		{
			String message = "mailto:admin1@test.com?subject=Feedback%20Email%20From%20*Username*";
			URI uri = URI.create(message);
			desktop.mail(uri);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}

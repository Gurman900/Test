package Com.cetpa;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator 
{
 
	public PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication("gurmanjaura@gmail.com", "gunnujaura@900");
	}
	
}

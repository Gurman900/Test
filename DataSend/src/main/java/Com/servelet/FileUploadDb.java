package Com.servelet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;
import javax.swing.text.html.MinimalHTMLWriter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import Com.cetpa.MailAuth;
import Com.cetpa.UploadMainFile;
import Com.cetpa.Dao.ImpMyDao;

/**
 * Servlet implementation class FileUploadDb
 */
@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 1024*1024*200)  // upload filesize upto 200mb
public class FileUploadDb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadDb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	     String name=request.getParameter("name");
	     String email=request.getParameter("email");
	     InputStream inputstream=null;
		int random = 0;
	     Part filePart=request.getPart("myfile");
	     if (filePart != null) {
	            //get inputStream of filepart
	            inputstream=filePart.getInputStream();
	     }
	     else 
	     {
	    	System.exit(1);
	    	 
	     }
	     
	     String message=null;   //message which will sent to client back 
	     try
	     {
	    	 ClassPathXmlApplicationContext ctx= new ClassPathXmlApplicationContext("99kart.xml");
	    	 ImpMyDao mydao=ctx.getBean(ImpMyDao.class);
	    	 random=new Random().nextInt(1000000);
	    	int done= mydao.storeDataInDb(new UploadMainFile(name, email, inputstream, random));
	    	if(done>0)
	    	{
	    		Properties mailproperty =new Properties();
	    		mailproperty.put("mail.smtp.host", "smtp.gmail.com");
	    		mailproperty.put("mail.smtp.port", "587");
	    		mailproperty.put("mail.smtp.starttls.enable", "true");
	    		mailproperty.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    		mailproperty.put("mail.smtp.auth", "true");
	    		
	    		MailAuth Authorised=new MailAuth();
	    		
	    		Session session=Session.getDefaultInstance(mailproperty, Authorised);
	    		MimeMessage mime=new MimeMessage(session);
	    		mime.setHeader("99kart", "Password");
	    		mime.setSubject("Password for the file that uploaded on 99kart.");
	    		 InternetAddress internet=new InternetAddress(email);
	    		 mime.setRecipient(RecipientType.TO,internet);
	    		 mime.setContent("your password for the file name "+name+" is "+random+" ", "text/plain");
	    		Transport.send(mime);
	    		JOptionPane java=new JOptionPane();
	    	    java.createDialog("file stored");
	    	    
	    	}
	     }
	     catch(Exception e)
	     {
	    	 message = "ERROR: " + e.getMessage();
	    	 e.printStackTrace();
	     }
	     
	     //sets the message in request scope
	     request.setAttribute("Message", message);
	     request.setAttribute("AccessKey", random);
	     
	     getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
	}

}

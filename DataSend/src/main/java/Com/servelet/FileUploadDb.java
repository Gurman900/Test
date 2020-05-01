package Com.servelet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.context.support.ClassPathXmlApplicationContext;

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
	            // prints out some information for debugging
	            System.out.println(filePart.getName());
	            System.out.println(filePart.getSize());
	            System.out.println(filePart.getContentType());
	            
	            //get inputStream of filepart
	            inputstream=filePart.getInputStream();
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
	    		message="File uploaded and saved into the database ";
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

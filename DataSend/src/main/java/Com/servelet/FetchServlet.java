package Com.servelet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import Com.cetpa.UploadMainFile;
import Com.cetpa.Dao.ImpMyDao;

/**
 * Servlet implementation class FetchServlet
 */
@WebServlet("/DownloadFileServlet")
public class FetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 4096; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchServlet() 
    {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String name=request.getParameter("name");
		int password=Integer.parseInt(request.getParameter("pass"));
		System.out.println(password);
		String message=null;
		try
		{
			ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("99kart.xml");
			ImpMyDao mydao=ctx.getBean(ImpMyDao.class);
			UploadMainFile umf=mydao.fetchDataFromDb(password);
			if(umf!=null)
			{
				InputStream input=umf.getInput();
				int fileLength=input.available();
				
				ServletContext context=getServletContext();
				String mime=context.getMimeType(umf.getName());
				 if (mime == null) {        
	                 mime = "application/octet-stream";
	             }    
				response.setContentType(mime);
				response.setContentLength(fileLength);
				 String headerKey = "Content-Disposition";
	             String headerValue =  "attachment; filename=\"" + umf.getName() + ".zip";
	             response.setHeader(headerKey, headerValue);
	            
	             
	             //write the file to the client
	             OutputStream output=response.getOutputStream();
	             
	             byte[] buffer = new byte[BUFFER_SIZE];
	             int bytesRead = -1;
	              
	             while ((bytesRead = input.read(buffer)) != -1) {
	                 output.write(buffer, 0, bytesRead);
	             }
	          //   output.write(input.toString().getBytes());
	             input.close();
	             output.close();
				}
			else
			{
				response.getWriter().print("file not found ");
			}
			}
			
		catch(Exception e)
		{
			message="error "+e.getMessage();
			e.printStackTrace();
		}
		
		
	}

}

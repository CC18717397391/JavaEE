package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DoGetPost extends HttpServlet {

	String computer = "";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("处理get()请求");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("<strong>GET Hello Servlet完美 "+ computer +"</strong><br>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("处理post()请求");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();	
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("<p><strong>POST完美Hello Servlet "+ computer +"</strong></p><br>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
				
	}

	@Override
	public void init() throws ServletException {
		ServletConfig config=getServletConfig();
		computer = config.getInitParameter("computer");
		System.out.println(computer);
		
	}

}
 
package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class HelloServlet_url_pattern extends HttpServlet{
	
	public void service(HttpServletRequest request,
								HttpServletResponse response) 
										throws ServletException,IOException{
		
		//读取请求参数request.getParameter()
		//http://localhost:8888/4.Servlet_Base/HUP?name=zhangsan&salary=500
		String name=request.getParameter("name");
		String salary=request.getParameter("salary");
		int salary2=Integer.parseInt(salary)*2;
		//处理
		String greeting="Hello "+name+",your salary is "+salary2;
		//设置服务器返回给浏览器的数据类型
		response.setContentType("text/html");
		//通过response对象获得输出流
		PrintWriter out=response.getWriter();
		//输出 <div style='font-size:30px;color:red;'>Hello Kitty</div>
		out.println("<div style='font-size:30px;color:red;'>"+greeting+"</div>");
		out.close();
		
	}
}

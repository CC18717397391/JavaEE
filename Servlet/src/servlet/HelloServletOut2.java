package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * get方式提交中文的乱码解决方案;
 * 1.String str = new String(request.getParameter("param").getBytes("iso-8859-1"),"utf-8");
 * 2.配置Tomcat服务器的server， 同修改端口号标签内添加URIEncoding="utf-8" ,重启服务器
 * post方法只需：
 * request.setCharacterEncoding("utf-8");
 * response.setContentType("text/html;charset=utf-8");
 */
@SuppressWarnings("serial")
public class HelloServletOut2 extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 读取请求参数request.getParameter()
		// http://localhost:8080/Servlet/Forward_ServletJsp?name=Clear&id=Cheng
		String name = request.getParameter("name");
		// 处理
		String greeting = "Hello " + name;
		// 设置服务器返回给浏览器的数据类型
		response.setContentType("text/html");
		// 通过response对象获得输出流
		PrintWriter out = response.getWriter();
		out.println("<div style='font-size:30px;color:red;'>" + greeting + "</div>");
		out.close();
	}
}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * get方式提交中文的乱码解决方案;
 * 1.String str = new String(request.getParameter("param").getBytes("iso-8859-1"),"utf-8");
 * 2.配置Tomcat服务器的server， 同修改端口号标签内添加URIEncoding="utf-8" ,重启服务器
 * post方法只需：
 * request.setCharacterEncoding("utf-8");
 * response.setContentType("text/html;charset=utf-8");
 */
@SuppressWarnings("serial")
public class Service extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setContentType("text/html");
		
		//分析.do请求资源路径
		/*String uri=request.getRequestURI(); 
		String action=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		//处理cookies,延期或使失效
		Cookie[] cookies = request.getCookies();
      	if(cookies!=null&&cookies.length>0){
         	for(Cookie c:cookies){
            	if(c.getName().equals("username")){
                	c.setMaxAge(0); //设置Cookie失效
                	response.addCookie(c); //重新保存。
            	}
         	}
      	}
		
		//返回cookies
		response.addCookie("");
		Cookie cookie = new Cookie("key","value");
      	cookie.setMaxAge(864000);//设置最大生存期限为10天
      	response.addCookie(cookie);
		
		//跳转
		request.setAttribute("key", "value");
		request.getRequestDispatcher("Target.jsp").forward(request, response);
		
		//重定向
		response.sendRedirect("Target.do");*/
		
		// http://localhost:8080/Servlet/Forward_ServletJsp?name=Clear&id=Cheng
		// 处理参数
		String name = request.getParameter("name");
		String greeting = "Hello " + name;
		
		// 输出内容
		PrintWriter out = response.getWriter();
		out.println("<div style='font-size:30px;color:red;'>" + greeting + "</div>");
		out.close();
		
	}
	
	
	
}

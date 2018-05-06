package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * get��ʽ�ύ���ĵ�����������;
 * 1.String str = new String(request.getParameter("param").getBytes("iso-8859-1"),"utf-8");
 * 2.����Tomcat��������server�� ͬ�޸Ķ˿ںű�ǩ�����URIEncoding="utf-8" ,����������
 * post����ֻ�裺
 * request.setCharacterEncoding("utf-8");
 * response.setContentType("text/html;charset=utf-8");
 */
@SuppressWarnings("serial")
public class Service extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setContentType("text/html");
		
		//����.do������Դ·��
		/*String uri=request.getRequestURI(); 
		String action=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		//����cookies,���ڻ�ʹʧЧ
		Cookie[] cookies = request.getCookies();
      	if(cookies!=null&&cookies.length>0){
         	for(Cookie c:cookies){
            	if(c.getName().equals("username")){
                	c.setMaxAge(0); //����CookieʧЧ
                	response.addCookie(c); //���±��档
            	}
         	}
      	}
		
		//����cookies
		response.addCookie("");
		Cookie cookie = new Cookie("key","value");
      	cookie.setMaxAge(864000);//���������������Ϊ10��
      	response.addCookie(cookie);
		
		//��ת
		request.setAttribute("key", "value");
		request.getRequestDispatcher("Target.jsp").forward(request, response);
		
		//�ض���
		response.sendRedirect("Target.do");*/
		
		// http://localhost:8080/Servlet/Forward_ServletJsp?name=Clear&id=Cheng
		// �������
		String name = request.getParameter("name");
		String greeting = "Hello " + name;
		
		// �������
		PrintWriter out = response.getWriter();
		out.println("<div style='font-size:30px;color:red;'>" + greeting + "</div>");
		out.close();
		
	}
	
	
	
}

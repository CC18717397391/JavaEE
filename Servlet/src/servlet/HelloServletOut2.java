package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * get��ʽ�ύ���ĵ�����������;
 * 1.String str = new String(request.getParameter("param").getBytes("iso-8859-1"),"utf-8");
 * 2.����Tomcat��������server�� ͬ�޸Ķ˿ںű�ǩ�����URIEncoding="utf-8" ,����������
 * post����ֻ�裺
 * request.setCharacterEncoding("utf-8");
 * response.setContentType("text/html;charset=utf-8");
 */
@SuppressWarnings("serial")
public class HelloServletOut2 extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�������request.getParameter()
		// http://localhost:8080/Servlet/Forward_ServletJsp?name=Clear&id=Cheng
		String name = request.getParameter("name");
		// ����
		String greeting = "Hello " + name;
		// ���÷��������ظ����������������
		response.setContentType("text/html");
		// ͨ��response�����������
		PrintWriter out = response.getWriter();
		out.println("<div style='font-size:30px;color:red;'>" + greeting + "</div>");
		out.close();
	}
}

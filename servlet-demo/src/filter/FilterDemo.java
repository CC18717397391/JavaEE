package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FilterDemo implements Filter {
	
	private FilterConfig config;
	
	/**
	 * 1.ʵ����
	 * ��������֮�󣬻���������������ʵ��(ֻ�ᴴ��һ��)
	 */
	public FilterDemo() {
		super();
	}


	/**
	 * 4.����
	 */
	public void destroy() {
	}

	
	/**
	 * 3.����
	 * ���������doFilter()��������������
	 * �����Ὣrequest,response��Ϊ���������ø÷��� 
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		
		//�ض���ͨ��requestӳ����web.xml��
		//response2.sendRedirect(req.getContextPath()+"/main.jsp");
		//ת����ͨ��forward����includeӳ����web.xml��
		//req.getRequestDispatcher("main.jsp").forward(request, response);
		//req.getRequestDispatcher("main.jsp").include(request, response);
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		
		String permit = config.getInitParameter("permit");
		
		String charset = config.getInitParameter("charset");		
		if(charset==null){
			charset = "UTF-8";
		}
		request.setCharacterEncoding(charset);
		
		if(permit!=null){
			String[] strArray = permit.split(";");
			for (int i = 0; i < strArray.length; i++) {
				//��������URI�������õĲ����˲���������
				if(request.getRequestURI().indexOf(strArray[i])!=-1 ){
					arg2.doFilter(arg0, arg1);
					return;
				}
			}
		}
		
		//ͨ��session�ж��Ƿ��ѵ�¼
		Object obj=session.getAttribute("user");
		if(obj==null){
			response.sendRedirect("Base.jsp");
			return;
		}
		arg2.doFilter(arg0, arg1);
		//����������,������ڶ�������������ù�������û����ִ��servlet


	}
	
	/**
	 * 2.��ʼ��
	 * ʵ����֮�����������init�������÷���ֻ��ִ��һ��
	 */
	public void init(FilterConfig arg0) throws ServletException {
		config = arg0;
	}

}

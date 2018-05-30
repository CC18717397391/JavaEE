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
	 * 1.实例化
	 * 容器启动之后，会立即创建过滤器实例(只会创建一个)
	 */
	public FilterDemo() {
		super();
	}


	/**
	 * 4.销毁
	 */
	public void destroy() {
	}

	
	/**
	 * 3.就绪
	 * 容器会调用doFilter()方法来处理请求，
	 * 容器会将request,response作为参数来调用该方法 
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		
		//重定向（通过request映射至web.xml）
		//response2.sendRedirect(req.getContextPath()+"/main.jsp");
		//转发（通过forward或者include映射至web.xml）
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
				//如果请求的URI包含配置的不过滤参数，放行
				if(request.getRequestURI().indexOf(strArray[i])!=-1 ){
					arg2.doFilter(arg0, arg1);
					return;
				}
			}
		}
		
		//通过session判断是否已登录
		Object obj=session.getAttribute("user");
		if(obj==null){
			response.sendRedirect("Base.jsp");
			return;
		}
		arg2.doFilter(arg0, arg1);
		//继续向后调用,如果存在多个过滤器，调用过滤器，没有则执行servlet


	}
	
	/**
	 * 2.初始化
	 * 实例化之后，容器会调用init方法，该方法只会执行一次
	 */
	public void init(FilterConfig arg0) throws ServletException {
		config = arg0;
	}

}

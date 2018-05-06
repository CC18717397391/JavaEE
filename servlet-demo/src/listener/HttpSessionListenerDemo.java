package listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * 统计在线人数
 */
public class HttpSessionListenerDemo implements HttpSessionListener{
	
	
	private int count = 0;
	/**
	 * session创建之后，容器会调用sessionCreated方法
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		
		System.out.println("创建了一个session");
		++count;
		arg0.getSession().getServletContext().setAttribute("count", count);
		
	}
	/**
	 * session销毁之后，容器会调用sessionDestroyed方法。
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {		
		--count;
		arg0.getSession().getServletContext().setAttribute("count", count);
		System.out.println("销毁了一个session");
		
	}
}

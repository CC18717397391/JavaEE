package listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * ͳ����������
 */
public class HttpSessionListenerDemo implements HttpSessionListener{
	
	
	private int count = 0;
	/**
	 * session����֮�����������sessionCreated����
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		
		System.out.println("������һ��session");
		++count;
		arg0.getSession().getServletContext().setAttribute("count", count);
		
	}
	/**
	 * session����֮�����������sessionDestroyed������
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {		
		--count;
		arg0.getSession().getServletContext().setAttribute("count", count);
		System.out.println("������һ��session");
		
	}
}

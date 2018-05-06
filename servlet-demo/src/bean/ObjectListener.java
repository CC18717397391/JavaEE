package bean;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;


public class ObjectListener implements HttpSessionBindingListener,HttpSessionActivationListener,Serializable {

	private String username;
	private String password;
	
	//绑定，解除绑定，  指的是session中的对象， 所以方法写在bean中， 无需xml注册
	public void valueBound(HttpSessionBindingEvent httpsessionbindingevent) {
		System.out.println("valueBound Name:"+httpsessionbindingevent.getName());
	}

	public void valueUnbound(HttpSessionBindingEvent httpsessionbindingevent) {
		System.out.println("valueUnbound Name:"+httpsessionbindingevent.getName());
	}
	
	//钝化
	public void sessionWillPassivate(HttpSessionEvent httpsessionevent) {
		System.out.println("sessionWillPassivate "+httpsessionevent.getSource());
	}
	//活化
	public void sessionDidActivate(HttpSessionEvent httpsessionevent) {
		System.out.println("sessionDidActivate "+httpsessionevent.getSource());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}

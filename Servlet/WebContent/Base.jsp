<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
errorPage="Error.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <title>My JSP starting page</title>
  </head>
  
  <body>
    <h1>MyEcilipse New Servlet</h1>
    
    <!--get使用相对路径访问 -->
    <a href="HJH">Get方式相对路径请求HelloServlet</a><br><br>
    
    <!-- 使用绝对路径 访问HelloServlet_jsp_html,可以使用path变量:path变量表示项目的根目录   -->
    <a href="<%=path%>/HJH">Get绝对路径访问HelloServlet!</a><br><br>
     
    <!--post使用相对路径访问 -->
    <form  action="HJH"  method="post">
    	<input type="submit" value="post方式相对路径请求HelloServlet"/>
    </form><br>
    	
 	<a href="<%=path%>/JSPF">点击通过web.xml的jsp-file标签跳转至Xml_jspfile页面，并获取xml的init初始化参数</a><br><br>
 	
 	<input type="button" value="点击此处跳转至未定义jsp页面，404错误通过xml的<error-page>替换" onclick="location='xxx.jsp'"/><br><br>
 	
 	
 	<%-- <%Integer.parseInt("100a");%> --%>
 	<!-- 本行错误的jsp表达式，报错会根据上面的page指令的errorPage="Error.jsp"跳转至指定页面，
 	该配置无法解决 “点击跳转页面路径缺失的404”原始报错界面，需要配合xml的  <error-page>使用 -->
 	 
 	
 	<%!int i=100; %>
	<%=i+100%></br>
	
	<%! 
	int sum(int a1,int a2){
	return a1+a2;
	}
	%>
	<%=sum(1,1)%>
	
	
 	
  </body>
</html>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
errorPage="Error.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html>
  <head>
    <title>My JSP starting page</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
    <h1>Servlet Demo</h1>
    
    <!--get使用相对路径访问 -->
    <a href="DoGetPost">Get方式相对路径请求HelloServlet</a><br><br>
    
    <!-- 使用绝对路径 访问,可以使用path变量:path变量表示项目的根目录   -->
    <a href="<%=path%>/DoGetPost">Get绝对路径访问HelloServlet!</a><br><br>
     
    <!--post使用相对路径访问 -->
    <form  action="Service"  method="post">
    	<input type="submit" value="post方式相对路径请求HelloServlet"/>
    </form><br>
    	
 	<a href="<%=path%>/Forward_Jsp">点击通过web.xml跳转至jsp页面，并获取xml的init初始化参数</a><br><br>
 	
 	<input type="button" value="点击此处跳转至未定义jsp页面，404错误通过xml的<error-page>替换" onclick="location='xxx.jsp'"/><br><br>
 	
 	 
 	<%-- <%Integer.parseInt("100a");%> --%>
 	<!-- 本行错误的jsp表达式，报错会根据上面的page指令的errorPage="Error.jsp"跳转至指定页面，
 	该配置无法解决 “点击跳转页面路径缺失的404”原始报错界面，需要配合xml的  <error-page>使用 -->
	

		
 	
  </body>
</html>

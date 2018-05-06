<%@ page language="java" import="java.util.*,java.text.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    <h1>session内置对象</h1>
    <hr>
    <% 
      //session表示客户端与服务器的一次会话，从浏览器打开到关闭，或者超时默认30分钟
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
      Date d = new Date(session.getCreationTime());
      session.setAttribute("username", "admin"); 
      session.setAttribute("password", "123456");
      session.setAttribute("age", 20);      
      //设置当前session最大生成期限单位是秒
      //session.setMaxInactiveInterval(10);//10秒钟     
    %>
    Session创建时间：<%=sdf.format(d)%><br>    
    Session的ID编号：<%=session.getId()%><BR>
    Session中获取用户名：<%=session.getAttribute("username") %><br>
    
    <a href="A5_InnerObj_Session2.jsp" target="_blank">跳转到A5_Session2.jsp</a>     
        
  </body>
</html>

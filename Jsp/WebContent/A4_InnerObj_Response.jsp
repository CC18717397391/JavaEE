<%@ page language="java" import="java.util.*,java.io.*" contentType="text/html; charset=utf-8"%>
<%
	//response对象用来向客户端输出HTML页面
    response.setContentType("text/html;charset=utf-8"); //设置响应的MIMI类型
    
    out.println("<h1>response内置对象</h1>");//内置out对象，优先级低，在没有out.flush时，输出在PrintWriter之后
    out.println("<hr>");
    //out.flush();
    
    PrintWriter outer = response.getWriter(); //获得输出流对象，优先级高
    outer.println("大家好，我是response对象生成的输出流outer对象");	//PrintWriter对象输出的内容在内置out对象之前
    //response.sendRedirect("A3_InnerObj_RegReady.jsp");//请求重定向
    //请求重定向
    //response.sendRedirect("A3_InnerObj_Request.jsp");
    //请求转发
    request.getRequestDispatcher("A3_InnerObj_Request.jsp").forward(request, response);
%>


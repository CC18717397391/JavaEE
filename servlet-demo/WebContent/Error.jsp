<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" isErrorPage="true"
%>
<html>
	<head></head>
	<body style="font-size:30px">
	<%  response.setStatus(200);  %> 
	<%--IE,360兼容性问题：200表示服务器成功返回网页，这样IE就不会把其当做错误而显示IE自定义的错误页面 --%>

	页面处理异常,管理员正在处理中~~~
	<%--<%=exception.getMessage()%> --%>
	</body>
</html>
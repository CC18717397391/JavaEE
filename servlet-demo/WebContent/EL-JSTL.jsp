<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head></head>
<body style="font-size: 30px">
	${u.name}
	${paramValues.city[1]}<br/>
	性别:
	<c:if test="${user.gender=='f'}">	女	</c:if>
	<c:if test="${user.gender!='f'}">	男	</c:if>

	性别:
	<c:if test="${user.gender=='f'}" var="flag" scope="page">女</c:if>
	<c:if test="${!flag}">男</c:if>
	
	性别:
	<c:choose>
		<c:when test="${user.gender=='f'}">女</c:when>
		<c:when test="${user.gender=='m'}">男</c:when>
		<c:otherwise>保密</c:otherwise>
	</c:choose><br/>
	
	${empty list}<br/>
	${empty str}<br/>
	
	
	<c:forEach items="${list}" var="a"  varStatus="s">
		<tr class="row${s.index%2+1}">
		 	<td>${a.name}</td>
	 	</tr>
	</c:forEach>
	
</body>
</html>
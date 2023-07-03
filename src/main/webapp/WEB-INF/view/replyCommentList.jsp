<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${list != null}">
<c:forEach var="node" items="${list}">
    <c:set var="node" value="${node}" scope="request"/>
    <jsp:include page="replyComment.jsp" />
</c:forEach>
</c:if>
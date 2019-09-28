<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" %>
	
<%@ page isELIgnored="false" %>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table align='center' border='1' cellspacing='0'>
	<tr>
		<td>id</td>
		<td>名称</td>
		<td>价格</td>
		<td>购买</td>
	</tr>
	
	<c:forEach items="${products}" var="product" varStatus="st">
        <tr>
            <td>${product.getId()}</td>
            <td>${product.getName()}</td>
            <td>${product.getPrice()}</td>
            <td>
            	<form action="addOrderItem" method="post">
            		数量<input type="text" value="1" name="num">
            		<input type="hidden" value="${product.getId()}" name="pid">
            		<input type="submit" value="购买">
            	</form>
            </td>
		</tr>
	</c:forEach>
</table>
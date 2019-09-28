<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1 align="center">购物车</h1>
<table align="center" border="1" cellspacing="0">
	<tr>
		<td>商品名</td>
		<td>单价</td>
		<td>数量</td>
		<td>小计</td>
		<td>删除</td>
	</tr>
	
	<c:forEach items="${orderItemList}" var="orderItem" varStatus="st">
	<tr>
		<td>${orderItem.getProduct().getName()}</td>
		<td>${orderItem.getProduct().getPrice()}</td>
		<td>${orderItem.getNum()}</td>
		<td>${orderItem.getNum()*orderItem.getProduct().getPrice()}</td>
		<td><a href="deleteOrderItem?id=${orderItem.getId()}">删除</a></td>
	</tr>
	</c:forEach>
	
	<c:if test="${!empty orderItemList}">
		<tr>
			<td	colspan="4" align="right">
				<a href="createOrder">生成订单</a>
			</td>
		</tr>
	</c:if>
</table>
<%@page import="model.payment" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
//Delete Payment----------------------------------
if (request.getParameter("PayID") != null)
{
payment paymentObj = new payment();
String stsMsg = paymentObj.deletePayment(request.getParameter("PayID"));
session.setAttribute("statusMsg", stsMsg);
}   

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>PAYMENT DETAILS</h1>
<%

 payment paymentObj = new payment();
 out.print(paymentObj.readPayment());
%>

</body>
</html>
<%@page import="model.payment" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%    
    //update part
 if (request.getParameter("PayID") != null)
 {
 payment paymentObj = new payment();
 String stsMsg = paymentObj.updatePayment(request.getParameter("PayID"),

 request.getParameter("payDate"),
 request.getParameter("amount"),
 request.getParameter("cid"),
 request.getParameter("cHolderName"),
 request.getParameter("c_Num"), 
 request.getParameter("cvv"),
 request.getParameter("cexdate"));
 session.setAttribute("statusMsg", stsMsg);
 }
%>
<!DOCTYPE html>
<html>
<style>
input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}


div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Payment Management</h1>
<h3><u>Update Payment </u></h3>

<div>
<form method="post" action="updatePayment.jsp">
 PayID:<input name="PayID" type="text"><br><br> 
 PAYMENT DATE: <input name="payDate" type="text"><br><br> 
 AMOUNT:   <input name="amount" type="text"><br><br>
 CARD ID:   <input name="cid" type="text"><br><br> 
 NAME ON CARD:   <input name="cHolderName" type="text"><br><br> 
 CARD NUMBER:   <input name="c_Num" type="text"><br><br>  
 CVV:     <input name="cvv" type="text"><br><br> 
 CARD EXPIRE DATE:     <input name="cexdate" type="text"><br><br> 
 
 <input name="btnSubmit" type="submit" value="Update"><br><br>
</form>
<a href="viewPayment.jsp" class="button">View Details</a>
</div>

<%
 out.print(session.getAttribute("statusMsg"));
%>


<br>
<%
 payment paymentObj = new payment();
 out.print(paymentObj.readPayment());
%>
</body>
</html>
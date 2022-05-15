package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.payment;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Servlet implementation class paymentAPI
 */
@WebServlet("/paymentAPI")
public class paymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	payment paymentObj = new payment();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public paymentAPI() {
        super();
    	
        // TODO Auto-generated constructor stub
    }
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		try
		 {
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		 String queryString = scanner.hasNext() ?
		 scanner.useDelimiter("\\A").next() : "";
		 scanner.close();
		 String[] params = queryString.split("&");
		 for (String param : params)
		 {
		 String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 } 

		return map;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	/**
	 * @param paymentObj 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		// TODO Auto-generated method stub
		{
			 String output = paymentObj.insertPayment(request.getParameter("payDate"),
			 request.getParameter("amount"),
			request.getParameter("cid"),
			request.getParameter("cHolderName"),
			request.getParameter("c_Num"),
			request.getParameter("cvv"),
			request.getParameter("cexdate"));

			response.getWriter().write(output);
			}

		
	

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		// TODO Auto-generated method stub
		{
			 Map paras = getParasMap(request);
			 String output = paymentObj.updatePayment(paras.get("PayID").toString(),
			 paras.get("payDate").toString(),
			paras.get("amount").toString(),
			paras.get("cid").toString(),
			paras.get("cHolderName").toString(),
			paras.get("c_Num").toString(),
			paras.get("cvv").toString(),
			paras.get("cexdate").toString());
			 
			response.getWriter().write(output);
			} 

	

	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		// TODO Auto-generated method stub
		{
			 Map paras = getParasMap(request);
			 String output = paymentObj.deletePayment(paras.get("PayID").toString());
			response.getWriter().write(output);
		}

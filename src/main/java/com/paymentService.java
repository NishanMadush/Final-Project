package com;

import model.payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/payment")

public class paymentService {
	payment paymentObj = new payment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readpayment()
	 {
		return paymentObj.readPayment(); 
	 } 
	
	


	// insert user details
		@POST
		@Path("/insert")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPayment(
		
		@FormParam("payDate") String payDate,
		@FormParam("amount") String amount,
		@FormParam("cid") String cid,
		@FormParam("cHolderName") String cHolderName,
		@FormParam("c_Num") String c_Num,
		@FormParam("cvv") String cvv,
		@FormParam("cexdate") String cexdate)
		
				{
					 String output = paymentObj.insertPayment(payDate,amount,cid,cHolderName,c_Num,cvv,cexdate);
					 return output;
				}
						
						
						
	//update user details
		@PUT
		@Path("/update")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String paymentData)
		{
		   //Convert the input string to a JSON object
			JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
							 
		    //Read the values from the JSON object
							
				String PayID = paymentObject.get("PayID").getAsString();
				String payDate = paymentObject.get("payDate").getAsString();
				String amount = paymentObject.get("amount").getAsString();
				String cid = paymentObject.get("cid").getAsString();
				String cHolderName = paymentObject.get("cHolderName").getAsString();
				String c_Num = paymentObject.get("c_Num").getAsString();
			    String cvv = paymentObject.get("cvv").getAsString();
			    String cexdate = paymentObject.get("cexdate").getAsString();
				
				String output = paymentObj.updatePayment(PayID,payDate,amount,cid,cHolderName,c_Num, cvv, cexdate);
				return output;
		}
						
		
		
	//delete user details
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
		{
		 //Convert the input string to an XML document
		 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
					
		//Read the value from the element <idUnit>
		 String PayID = doc.select("PayID").text();
		 String output = paymentObj.deletePayment(PayID);
		 return output;
		 
		}

}

package model;

import java.sql.*; 

public class payment {
	
	//A common method to connect to the DB
	
	private Connection connect()
	 {
		 Connection con = null;
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");
		
			 //Provide the correct details: DBServer/DBName, username, password
			 
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/powerbill", "root", "root");
		 }
			 catch (Exception e)
			 {e.printStackTrace();}
			 return con;
	 }
	
	// insert method
			public String insertPayment(String payDate, String amount, String cid, String cHolderName, String c_Num, String cvv, String cexdate)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 
			 // create a prepared statement
			 
			 String query = " insert into payment (`PayID`,`payDate`,`amount`,`cid`,`cHolderName`,`c_Num`,`cvv`,`cexdate`)" + " values (?, ?, ?, ?, ?, ?, ?,?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, payDate);
			 preparedStmt.setString(3, amount);
			 preparedStmt.setString(4, cid);
			 preparedStmt.setString(5, cHolderName);
			 preparedStmt.setString(6, c_Num);
			 preparedStmt.setString(7, cvv);
			 preparedStmt.setString(8, cexdate);
			 
			 // execute the statement
			 
			 preparedStmt.execute();
			 con.close();
			 output = "Inserted successfully..";
		 }
		 catch (Exception e)
		 {
			 output = "Error while inserting..";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
			
			//view method
		public String readPayment()
		 {
			String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {return "Error while connecting to the database for reading."; }
				 
				 // Prepare the html table to be displayed
				 
				 output = "<table border='1'><tr><th>PAYMENT ID</th>" +"<th>PAYMENT DATE</th>" + "<th>AMOUNT</th>" +
				 "<th>CARD ID</th>" + "<th>NAME ON CARD</th>" + "<th>CARD NUMBER</th>" + "<th>CVV</th>" + "<th>CARD EXPIER DATE</th>" +
				 "<th>Update</th><th>Remove</th></tr>";
			
				 String query = "select * from payment";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 // iterate through the rows in the result set
				 
				 while (rs.next())
				 {
					 String PayID = Integer.toString(rs.getInt("PayID"));
					 String payDate = rs.getString("payDate");
					 String amount = rs.getString("amount");
					 String cid = rs.getString("cid");
					 String cHolderName = rs.getString("cHolderName");
					 String c_Num = rs.getString("c_Num");
					 String cvv = rs.getString("cvv");
					 String cexdate = rs.getString("cexdate");
					 
					 // Add into the html table
					 
					 output += "<td>" + PayID + "</td>";
					 output	+= "<td>" + payDate + "</td>";
					 output += "<td>" + amount + "</td>";
					 output += "<td>" + cid + "</td>";
					 output += "<td>" + cHolderName + "</td>";
					 output += "<td>" + c_Num + "</td>";
					 output += "<td>" + cvv + "</td>";
					 output += "<td>" + cexdate + "</td>";
					 
					 // buttons
					 
					 output += "<td><form method='post' action='updatePayment.jsp'>"
					 		+ "<input name='btnUpdate' type='submit' value='Update'class='btn btn-secondary'>"
					 		+ "<input name='ID' type='hidden' value='" + PayID + "'>" + "</form></td>"
							 + "<td><form method='post' action='viewPayment.jsp'>"
							 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							 + "<input name='PayID' type='hidden' value='" + PayID + "'>" + "</form></td></tr>";
				 }
				 con.close();
				 
				 // Complete the html table
				 
				 output += "</table>";
			 }
			 catch (Exception e)
			 {
				 output = "Error while reading the .";
				 System.err.println(e.getMessage());
			 }
			 return output;
		 }
		
		//update method
		 public String updatePayment(String PayID, String payDate, String amount, String cid, String cHolderName, String c_Num, String cvv, String cexdate)
		 {
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {return "Error while connecting to the database for updating."; }
				 
				 // create a prepared statement
				 
				 String query = "UPDATE payment SET payDate=?,amount=?,cid=?,cHolderName=?,c_Num=?,cvv=?,cexdate=? WHERE PayID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 
				 preparedStmt.setString(1, payDate);
				 preparedStmt.setString(2, amount);
				 preparedStmt.setString(3, cid);
				 preparedStmt.setString(4, cHolderName);
				 preparedStmt.setString(5, c_Num);
				 preparedStmt.setString(6, cvv);
				 preparedStmt.setString(7, cexdate);
				 preparedStmt.setInt(8, Integer.parseInt(PayID));
				 
				 // execute the statement
				 
				 preparedStmt.execute();
				 con.close();
				 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Error while updating the payment.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		}
		 
		 //delete method
		public String deletePayment(String PayID)
		{
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {return "Error while connecting to the database for deleting."; }
				 // create a prepared statement
				 String query = "delete from payment where PayID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(PayID));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Error while deleting the item.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		 }
}



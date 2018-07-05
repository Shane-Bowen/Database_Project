package Application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Connection {

   private static final String URL = "jdbc:mysql://localhost:3306/My_Garage_Database?autoReconnect=true&useSSL=false&relaxAutoCommit=true";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "root";
   
   private Connection connection; // manages connection
   
   private PreparedStatement statement = null; 
     
   // constructor
   public DB_Connection()
   {
      try 
      {
         connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         System.exit( 1 );
      } // end catch
   } // end constructor
 
   // add an Staff entry
   public int addStaff( 
      String staffNo, String fName, String lName, String address, String gender, String DOB, Integer salary )
   {
       try {
		statement = connection.prepareStatement( 
		           "INSERT INTO STAFF " + 
		           "( staffNo, fName, lName, address, gender, DOB, salary ) " + 
		           "VALUES ( ?, ?, ?, ?, ?, ?, ? )" );
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   
      int result = 0;
      
      // set parameters, then execute insert new staff
      try 
      {
         statement.setString( 1, staffNo );
         statement.setString( 2, fName );
         statement.setString( 3, lName );
         statement.setString( 4, address );
         statement.setString( 5, gender );
         statement.setString( 6, DOB );
         statement.setInt( 7, salary );

         // insert the new entry; returns # of rows updated
         result = statement.executeUpdate(); 
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch
      
      return result;
   } // end method addStaff
   
   public int deleteStaff(String staffNo)
   {
	      int result = 0;

	   try {
           //delete
           Statement deleteStmt = connection.createStatement();
           String deleteSQL = "DELETE FROM STAFF WHERE staffNo = '"+staffNo+"' ";
           result = deleteStmt.executeUpdate(deleteSQL);
           connection.commit();   
           deleteStmt.close();
      }catch (Exception io) {
               System.out.println("error"+io);
      };
	return result;   
 
    }//delete	
   
   public  int updateStaff (String staffNo, String fName, String lName, String address, String gender, String DOB, Integer salary) {
       
	   int res = 0;
	   
	        try {
	            //update
	            Statement updateStmt = connection.createStatement();
	            String updateSQL = " Update STAFF set fName = '"+fName+"', lName = '"+lName+"', address = '"+address+"'"
	            		+ ", gender = '"+gender+"', DOB = '"+DOB+"', salary = "+salary+" where staffNo = '"+staffNo+"'";
	            
	            res = updateStmt.executeUpdate(updateSQL);
	            updateStmt.close();
	            connection.commit();
	       }catch (Exception io) {
	                System.out.println("error"+io);
	       };
		return res;   
		
    }//update

   public int addClient( 
		      String clientNo, String fName, String lName, String address, String telNo )
		   {
       try {
		statement = connection.prepareStatement( 
		           "INSERT INTO CLIENT " + 
		           "( clientNo, fName, lName, address, telNo ) " + 
		           "VALUES ( ?, ?, ?, ?, ? )" );
	} catch (SQLException e) {
		e.printStackTrace();
	}
       
		      int result = 0;
		      
		      // set parameters, then execute insert new Client
		      try 
		      {
		         statement.setString( 1, clientNo );
		         statement.setString( 2, fName );
		         statement.setString( 3, lName );
		         statement.setString( 4, address );
		         statement.setString( 5, telNo );

		         // insert the new entry; returns # of rows updated
		         result = statement.executeUpdate(); 
		      } // end try
		      catch ( SQLException sqlException )
		      {
		         sqlException.printStackTrace();
		         close();
		      } // end catch
		      
		      return result;
		   } // end method addClient
   
   public int deleteClient(String clientNo)
   {
	   int result = 0;
	   try {
           //delete
           Statement deleteStmt = connection.createStatement();
           String deleteSQL = "DELETE FROM CLIENT WHERE clientNo = '"+clientNo+"' ";
           result = deleteStmt.executeUpdate(deleteSQL);
           connection.commit();   
           deleteStmt.close();
      }catch (Exception io) {
               System.out.println("error"+io);
      };   
      
      return result;
 
    }//delete
   
public  int updateClient (String clientNo, String firstName, String lastName, String address, String telNo) {
       
	   int res = 0;
	   
	        try {
	            //update
	            Statement updateStmt = connection.createStatement();
	            String updateSQL = " Update CLIENT set fName = '"+firstName+"', lName = '"+lastName+"', address = '"+address+"', "
	            		+ "telNo = '"+telNo+"' where clientNo = '"+clientNo+"'";
	            
	            res = updateStmt.executeUpdate(updateSQL);
	            updateStmt.close();
	            connection.commit();
	       }catch (Exception io) {
	                System.out.println("error"+io);
	       };
		return res;   
		
    }//update
   
   public int addJob( 
		      String jobNo, String Description, String startDate, String endDate, Integer cost, String clientNo, String staffNo, String partNo )
		   {
    try {
		statement = connection.prepareStatement( 
		           "INSERT INTO Job " + 
		           "( jobNo, Description, startDate, endDate, cost, clientNo, staffNo, partNo ) " + 
		           "VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )" );
	} catch (SQLException e) {
		e.printStackTrace();
	}
    
		      int result = 0;
		      
		      // set parameters, then execute insert new Job
		      try 
		      {
		         statement.setString( 1, jobNo );
		         statement.setString( 2, Description );
		         statement.setString( 3, startDate );
		         statement.setString( 4, endDate );
		         statement.setInt( 5, cost );
		         statement.setString( 6, clientNo );
		         statement.setString( 7, staffNo );
		         statement.setString( 8, partNo );

		         // insert the new entry; returns # of rows updated
		         result = statement.executeUpdate(); 
		      } // end try
		      catch ( SQLException sqlException )
		      {
		         sqlException.printStackTrace();
		         close();
		      } // end catch
		      
		      return result;
		   } // end method addJob
   
public  int updateJob (
		String jobNo, String Description, String startDate, String endDate, Integer cost, String clientNo, String staffNo, String partNo) {
       
	   int res = 0;
	   
	        try {
	            //update
	            Statement updateStmt = connection.createStatement();
	            String updateSQL = " Update JOB set Description = '"+Description+"', startDate = '"+startDate+"', endDate = '"+endDate+"', "
	            		+ "cost = "+cost+" , clientNo = '"+clientNo+"' , staffNo = '"+staffNo+"' , partNo = '"+partNo+"'"
	            				+ " where jobNo = '"+jobNo+"'";
	            
	            res = updateStmt.executeUpdate(updateSQL);
	            updateStmt.close();
	            connection.commit();
	       }catch (Exception io) {
	                System.out.println("error"+io);
	       };
		return res;   
}
   public int deleteJob(String jobNo)
   {
	   int result = 0;
	   
	   try {
           //delete
           Statement deleteStmt = connection.createStatement();
           String deleteSQL = "DELETE FROM JOB WHERE jobNo = '"+jobNo+"' ";
           result = deleteStmt.executeUpdate(deleteSQL);
           connection.commit();   
           deleteStmt.close();
      }catch (Exception io) {
               System.out.println("error"+io);
      };   
      
      return result;
 
    }//delete
   
   public int addParts( 
		      String partNo, String Description, Integer price, String orderNo)
		   {
 try {
		statement = connection.prepareStatement( 
		           "INSERT INTO Parts " + 
		           "( partNo, Description, price, orderNo ) " + 
		           "VALUES ( ?, ?, ?, ? )" );
	} catch (SQLException e) {
		e.printStackTrace();
	}
 
		      int result = 0;
		      
		      // set parameters, then execute insert New Part
		      try 
		      {
		         statement.setString( 1, partNo );
		         statement.setString( 2, Description );
		         statement.setInt( 3, price );
		         statement.setString( 4, orderNo );

		         // insert the new entry; returns # of rows updated
		         result = statement.executeUpdate(); 
		      } // end try
		      catch ( SQLException sqlException )
		      {
		         sqlException.printStackTrace();
		         close();
		      } // end catch
		      
		      return result;
		   } // end method addParts
   
   public  int updateParts (
		   String partNo, String Description, Integer price, String orderNo) {
	       
		   int res = 0;
		   
		        try {
		            //update
		            Statement updateStmt = connection.createStatement();
		            String updateSQL = " Update PARTS set Description = '"+Description+"', price = "+price+", orderNo = '"+orderNo+"'"
            		  			+ " where partNo = '"+partNo+"'";
		            
		            res = updateStmt.executeUpdate(updateSQL);
		            updateStmt.close();
		            connection.commit();
		       }catch (Exception io) {
		                System.out.println("error"+io);
		       };
			return res;   
	}
   
   public int deletePart(String partNo)
   {
	   int result = 0;
	   try {
           //delete
           Statement deleteStmt = connection.createStatement();
           String deleteSQL = "DELETE FROM PARTS WHERE partNo = '"+partNo+"' ";
           result = deleteStmt.executeUpdate(deleteSQL);
           connection.commit();   
           deleteStmt.close();
      }catch (Exception io) {
               System.out.println("error"+io);
      };   
      
      return result;
 
    }//delete
   
   public int addOrders( 
		      String orderNo, String orderedByDate, String arrivalDate, String courierNo)
		   {
try {
		statement = connection.prepareStatement( 
		           "INSERT INTO Orders " + 
		           "( orderNo, orderedByDate, arrivalDate, courierNo ) " + 
		           "VALUES ( ?, ?, ?, ? )" );
	} catch (SQLException e) {
		e.printStackTrace();
	}

		      int result = 0;
		      
		      // set parameters, then execute insert New Orders
		      try 
		      {
		         statement.setString( 1, orderNo );
		         statement.setString( 2, orderedByDate );
		         statement.setString( 3, arrivalDate );
		         statement.setString( 4, courierNo);

		         // insert the new entry; returns # of rows updated
		         result = statement.executeUpdate(); 
		      } // end try
		      catch ( SQLException sqlException )
		      {
		         sqlException.printStackTrace();
		         close();
		      } // end catch
		      
		      return result;
		   } // end method addOrders
   
   public  int updateOrders (
		   String orderNo, String orderedByDate, String arrivalDate, String courierNo) {
	       
		   int res = 0;
		   
		        try {
		            //update
		            Statement updateStmt = connection.createStatement();
		            String updateSQL = " Update ORDERS set orderedByDate = '"+orderedByDate+"', arrivalDate = '"+arrivalDate+"',"
		            		+ " courierNo = '"+courierNo+"' where orderNo = '"+orderNo+"'";
		            
		            res = updateStmt.executeUpdate(updateSQL);
		            updateStmt.close();
		            connection.commit();
		       }catch (Exception io) {
		                System.out.println("error"+io);
		       };
			return res;   
	}
   
   public int deleteOrder(String orderNo)
   {
	   int result = 0;
	   try {
           //delete
           Statement deleteStmt = connection.createStatement();
           String deleteSQL = "DELETE FROM ORDERS WHERE orderNo = '"+orderNo+"' ";
           result = deleteStmt.executeUpdate(deleteSQL);
           connection.commit();   
           deleteStmt.close();
      }catch (Exception io) {
               System.out.println("error"+io);
      };   
      
      return result;
 
    }//delete
   
   public int addCourier( 
		      String courierNo, String firstName, String lastName, String telNo)
		   {
try {
		statement = connection.prepareStatement( 
		           "INSERT INTO Courier " + 
		           "( courierNo, fName, lName, telNo ) " + 
		           "VALUES ( ?, ?, ?, ? )" );
	} catch (SQLException e) {
		e.printStackTrace();
	}

		      int result = 0;
		      
		      // set parameters, then execute insert New Courier
		      try 
		      {
		         statement.setString( 1, courierNo );
		         statement.setString( 2, firstName );
		         statement.setString( 3, lastName );
		         statement.setString( 4, telNo);

		         // insert the new entry; returns # of rows updated
		         result = statement.executeUpdate(); 
		      } // end try
		      catch ( SQLException sqlException )
		      {
		         sqlException.printStackTrace();
		         close();
		      } // end catch
		      
		      return result;
		   } // end method addCouriers
   
   public  int updateCourier (
		   String courierNo, String firstName, String lastName, String telNo) {
	       
		   int res = 0;
		   
		        try {
		            //update
		            Statement updateStmt = connection.createStatement();
		            String updateSQL = " Update COURIER set fName = '"+firstName+"', lName = '"+lastName+"',"
		            		+ " telNo = '"+telNo+"' where courierNo = '"+courierNo+"'";
		            
		            res = updateStmt.executeUpdate(updateSQL);
		            updateStmt.close();
		            connection.commit();
		       }catch (Exception io) {
		                System.out.println("error"+io);
		       };
			return res;   
	}

   public int deleteCourier(String courierNo)
   {
	   int result = 0;
	   try {
           //delete
           Statement deleteStmt = connection.createStatement();
           String deleteSQL = "DELETE FROM COURIER WHERE courierNo = '"+courierNo+"' ";
           result = deleteStmt.executeUpdate(deleteSQL);
           connection.commit();   
           deleteStmt.close();
      }catch (Exception io) {
               System.out.println("error"+io);
      };   
      
      return result;
 
    }//delete
   
   // close the database connection
   public void close()
   {
      try 
      {
         connection.close();
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch
   } // end method close   
   
} 
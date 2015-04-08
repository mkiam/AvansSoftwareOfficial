package controllers;
import java.sql.*;


public class CreateBase {
		
	  public static void main( String args[] )
	  {
	    Connection c = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Database.db");
			c.setAutoCommit(false);
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	      
	    }
	    System.out.println("Opened database successfully");
	 
	  }
	

}

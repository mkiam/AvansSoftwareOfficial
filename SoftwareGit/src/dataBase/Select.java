package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Select {
	public static void main(String[]args){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:database/Article.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql ="SELECT * FROM ARTICLE";
			stmt.execute(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}


}

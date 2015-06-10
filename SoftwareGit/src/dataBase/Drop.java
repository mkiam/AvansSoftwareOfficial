
package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Drop {
	public static void main(String[]args){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:database/Article.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql ="DELETE FROM ARTICLE";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}

}

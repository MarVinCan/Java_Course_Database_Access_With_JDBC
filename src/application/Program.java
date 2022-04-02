package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		

		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			
			conn.setAutoCommit(false);
			
			int row1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			//int x =1;
			
			//if(x < 2) {
			//	throw new SQLException("Fake Error");
			//}
			
			int row2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit();
			
			System.out.println("Row 1: "+row1);
			System.out.println("Row 2: "+ row2);
		} 
		catch (SQLException e) {
			try {
				conn.rollback();
				System.out.println("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				System.out.println("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}

package bookstore;

import java.sql.*;
import java.util.ArrayList;
import java.math.BigDecimal;

public class SQL {
	private Connection connection;
	
	public SQL(){
		try{
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookstore", "postgres", "postgres");
		}catch (Exception sqle) {
	        System.out.println("Exception: " + sqle);
	    }
	}
	
	public boolean insertUser(ArrayList<String> values){
		try {
			PreparedStatement ps = connection.prepareStatement(
				"insert into user_ values (?,?,?,?,?,?,?);"
			);
			for(int i=0; i<7; ++i) {
				if(i==3) {
					ps.setBoolean(4, values.get(i).equals("Owner"));
					continue;
				}
				ps.setString(i+1, values.get(i));
			}
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	// return: 0 if no match, 1 if customer, 2 if owner
	public int login(String username, String password) {
		try {
			PreparedStatement ps = connection.prepareStatement(
				"select * from user_ where username=? and password=?;"
			);

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if(!rs.isBeforeFirst()) return 0;
			rs.next();
			return rs.getBoolean(4) ? 1 : 2;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean insertBook(ArrayList<String> values){
		try {
			PreparedStatement ps = connection.prepareStatement(
				"insert into book values (?,?,?,?,?,?,?);"
			);
			for(int i=0; i<7; ++i) {
				if(i==2 || i==4) {
					ps.setInt(i+1, Integer.parseInt(values.get(i)));
					continue;
				}
				if(i==3 || i==6) {
					ps.setBigDecimal(i+1, new BigDecimal(values.get(i)));
					continue;
				}
				ps.setString(i+1, values.get(i));
			}
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public boolean insertPublisher(ArrayList<String> values){
		try {
			PreparedStatement ps = connection.prepareStatement(
				"insert into publisher values (?,?,?,?,?,?,?);"
			);
			for(int i=0; i<7; ++i) {
				if(i>=4) {
					ps.setInt(i+1, Integer.parseInt(values.get(i)));
					continue;
				}
				ps.setString(i+1, values.get(i));
			}
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}


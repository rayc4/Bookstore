package bookstore;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SQL {
	private static Connection connection;
	
	public SQL(){
		try{
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookstore", "postgres", "postgres");
		}catch (Exception sqle) {
	        System.out.println("Exception: " + sqle);
	    }
	}
	
	public static boolean insertUser(ArrayList<String> values){
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
	public static int login(String username, String password) {
		try {
			PreparedStatement ps = connection.prepareStatement(
				"select * from user_ where username=? and password=?;"
			);

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if(!rs.isBeforeFirst()) return 0;
			rs.next();
			return rs.getBoolean(4) ? 2 : 1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public static boolean insertBook(ArrayList<String> values){
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

	public static boolean insertPublisher(ArrayList<String> values){
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
	
	public static LinkedList<String> getBooks() {
		try {
			PreparedStatement ps = connection.prepareStatement(
				"select ISBN from book;"
			);

			ResultSet rs = ps.executeQuery();
			LinkedList<String> res = new LinkedList<String>();
			while(rs.next()) {
				res.add(rs.getString(1));
			}
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static LinkedList<String> getBook(String isbn) {
		try {
			PreparedStatement ps = connection.prepareStatement(
				"select * from book where ISBN=?;"
			);
			ps.setString(1, isbn);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			LinkedList<String> res = new LinkedList<String>();
			for(int i=1; i<=7; ++i) {
				res.add(rs.getString(i));
			}
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean deleteBook(String isbn) {
		try {
			PreparedStatement ps = connection.prepareStatement(
				"delete from book where isbn=?;"
			);
			ps.setString(1, isbn);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean insertOrder(ArrayList<String> al, float total) {
		try {
			PreparedStatement ps = connection.prepareStatement(
				"insert into order_ values (default,?,'pending','2021-01-01',?,?,?);"
			);
			ps.setString(1, WelcomeController.username);
			ps.setString(2, al.get(0));
			ps.setString(3, al.get(1));
			ps.setBigDecimal(4, BigDecimal.valueOf(total));
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static LinkedList<String> getOrders(String username){
		try {
			PreparedStatement ps = connection.prepareStatement(
				"select * from order_ where username=?;"
			);
			ps.setString(1, username);
			
			LinkedList<String> res = new LinkedList<String>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res.add(rs.getString(1));
			}
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static LinkedList<String> getOrder(String id){
		try {
			PreparedStatement ps = connection.prepareStatement(
				"select * from order_ where id=?;"
			);
			ps.setInt(1, Integer.parseInt(id));
			LinkedList<String> res = new LinkedList<String>();
			ResultSet rs = ps.executeQuery();
			rs.next();
			for(int i=1; i<=7; ++i) {
				res.add(rs.getString(i));
			}
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static LinkedList<String> getTitles(String orderId){
		try {
			PreparedStatement ps = connection.prepareStatement(
				"select title from book_order join book on (book_order.isbn = book.isbn) " +
				"where order_id = ?;"
			);
			ps.setInt(1, Integer.parseInt(orderId));
			LinkedList<String> res = new LinkedList<String>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res.add(rs.getString(1));
			}
				
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insertBookGenre(String isbn, String genre) {
		try {
			PreparedStatement ps = connection.prepareStatement(
				"insert into book_genre values (?,?);"
			);
			ps.setString(1, isbn);
			ps.setString(2, genre);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static LinkedList<String> getGenres(String isbn){
		try {
			PreparedStatement ps = connection.prepareStatement(
				"select genre from book_genre where isbn=?;"
			);
			ps.setString(1, isbn);
			
			LinkedList<String> res = new LinkedList<String>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res.add(rs.getString(1));
			}
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static boolean insertAuthor(String firstName, String lastName, String isbn) {
		try {
			PreparedStatement ps = connection.prepareStatement(
				"insert into author values (?,?,?);"
			);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, isbn);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static LinkedList<String> getAuthors(String isbn){
		try {
			PreparedStatement ps = connection.prepareStatement(
				"select first_name, last_name from author where isbn=?;"
			);
			ps.setString(1, isbn);
			
			LinkedList<String> res = new LinkedList<String>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res.add(rs.getString(1)+" "+rs.getString(2));
			}
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insertBookOrder(int orderId, String isbn, int quantity) {
		try {
			PreparedStatement ps = connection.prepareStatement(
				"insert into book_order values (?,?,?);"
			);
			ps.setInt(1, orderId);
			ps.setString(2, isbn);
			ps.setInt(3, quantity);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static int getMaxOrderId() {
		try {
			PreparedStatement ps = connection.prepareStatement(
				"select max(id) from order_ ;"
			);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public static double[] getRevenueAndExpenditures(String start, String end) throws ParseException{
		double[] res = new double[2];
		try {
			PreparedStatement ps = connection.prepareStatement("refresh materialized view date_revenue_expenditures;");
			ps.executeUpdate();
			ps = connection.prepareStatement(
					"select sum(revenue) as revenue, sum(expenditures) as expenditures from date_revenue_expenditures " +
					"where date between ? and ?; "
			);
			ps.setDate(1, Date.valueOf(start));
			ps.setDate(2, Date.valueOf(end));
			ResultSet rs = ps.executeQuery();
			rs.next();
			res[0] = rs.getDouble(1);
			res[1] = rs.getDouble(2);
			return res;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static HashMap<String, Double> getRevenuesByGenre(String start, String end) throws ParseException{
		HashMap<String, Double> res = new HashMap<String, Double>();
		try {
			PreparedStatement ps = connection.prepareStatement("refresh materialized view date_genre_revenue;");
			ps.executeUpdate();
			ps = connection.prepareStatement(
					"select genre, sum(revenue) as revenue from date_genre_revenue " +
					"where date between ? and ? " +
					"group by genre; "
			);
			ps.setDate(1, Date.valueOf(start));
			ps.setDate(2, Date.valueOf(end));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res.put(rs.getString(1), rs.getDouble(2));
			}
			return res;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static HashMap<String, Double> getRevenuesByAuthor(String start, String end) throws ParseException{
		HashMap<String, Double> res = new HashMap<String, Double>();
		try {
			PreparedStatement ps = connection.prepareStatement("refresh materialized view date_author_revenue;");
			ps.executeUpdate();
			ps = connection.prepareStatement(
					"select first_name, last_name, sum(revenue) as revenue from date_author_revenue " +
					"where date between ? and ? " +
					"group by first_name, last_name; "
			);
			ps.setDate(1, Date.valueOf(start));
			ps.setDate(2, Date.valueOf(end));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res.put(rs.getString(1) +" " +rs.getString(2), rs.getDouble(3));
			}
			return res;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}


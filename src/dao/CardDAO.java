package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.CardBean;
import bean.ProductBean;
import bean.ReviewBean;
import bean.UserPurchasesBean;

public class CardDAO {
	private DataSource dataSource;

	public CardDAO() throws ClassNotFoundException {
		try {
			dataSource = (DataSource) (new InitialContext()).lookup(DatabaseAccess.instance.ACTIVE_ACCESS);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public ProductBean retrieveByID(int id) throws SQLException {
		/*
		select * from cards where id=id_number
		*/
		
		String query = "select * from cards join cardmarket on cards.number = cardmarket.number join CardImages on cards.number = cardImages.cardNumber where cards.number = ?";      
		Connection con = this.dataSource.getConnection();   
		PreparedStatement p = con.prepareStatement(query);   
		p.setInt(1, id);
		
		ResultSet r = p.executeQuery();   
		if (r.next()) {
			String name = r.getString("name");
			String desc = r.getString("description");
			String rank = r.getString("rank");
			Integer limit = r.getInt("limit");
			String classType = r.getString("class");
			String spellType = r.getString("spellType");
			String img = r.getString("image");
			int stock = r.getInt("stock");
			double price = r.getDouble("sellingprice");

			r.close();   
			p.close();   
			con.close();   
			
			return new CardBean(id,name,desc,rank,limit,classType,spellType,img,stock,price);
		} else {
			r.close();   
			p.close();   
			con.close(); 
			
			return null;
		}
					
		
	}
	
	public List<ProductBean> search(String searchQuery) throws SQLException {
		/*
		select * from cards where name like query
		*/
		
		List<ProductBean> products = new ArrayList<>();
		
		String query = "select * from cards join cardmarket on cards.number = cardmarket.number join CardImages on cards.number = CardImages.cardNumber where cards.name like '%?%'";      
		Connection con = this.dataSource.getConnection();   
		PreparedStatement p = con.prepareStatement(query);   
		p.setString(1, searchQuery);
		ResultSet r = p.executeQuery();   
		while (r.next())
		{    
			int id = r.getInt("number");
			String name = r.getString("name");
			String desc = r.getString("description");
			String rank = r.getString("rank");
			Integer limit = r.getInt("limit");
			String classType = r.getString("class");
			String spellType = r.getString("spellType");
			String img = r.getString("image");
			int stock = r.getInt("stock");
			double price = r.getDouble("sellingprice");				
			products.add(new CardBean(id,name,desc,rank,limit,classType,spellType,img,stock,price));
		}   
		r.close();   
		p.close();   
		con.close();   
		return products; 
	}

	public List<ProductBean> retrieveAll() throws SQLException{
		List<ProductBean> products = new ArrayList<>();
		
		String query = "select * from cards join cardmarket on cards.number = cardmarket.number join CardImages on cards.number = CardImages.cardNumber";      
		Connection con = this.dataSource.getConnection();   
		PreparedStatement p = con.prepareStatement(query);   
		ResultSet r = p.executeQuery();   
		while (r.next())
		{    
			int id = r.getInt("number");
			String name = r.getString("name");
			String desc = r.getString("description");
			String rank = r.getString("rank");
			Integer limit = r.getInt("limit");
			String classType = r.getString("class");
			String spellType = r.getString("spellType");
			String img = r.getString("image");
			int stock = r.getInt("stock");
			double price = r.getDouble("sellingprice");				
			products.add(new CardBean(id,name,desc,rank,limit,classType,spellType,img,stock,price));
		}   
		r.close();   
		p.close();   
		con.close();   
		return products;     
		
		
		/*
		//Need to implement model for card shop; Currently testing with beans directly
		ProductBean testItem1 = new CardBean("testName1", "testDesc1", 420.69, 1, null);
		ProductBean testItem2 = new CardBean("testName2", "testDesc2", 420.69, 2, null);
		ProductBean testItem3 = new CardBean("testName3", "testDesc3", 420.69, 3, null);
		products.add(testItem1);
		products.add(testItem2);
		products.add(testItem3);
		*/
	}
	
	/**
	 * Get all reviews of this particular card ID
	 * @param cardID
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<ReviewBean> getReview(int cardID) throws SQLException
	{
		ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>();
		String query = "select * from CardReview where number = ?";
		
		Connection con = this.dataSource.getConnection();   
		PreparedStatement statement = con.prepareStatement(query); 
		statement.setInt(1, cardID);
		ResultSet results = statement.executeQuery();  	
		
		while (results.next())
		{
			reviews.add(new ReviewBean(cardID, results.getInt("rating"), results.getString("content")));
		}
		statement.close();
		results.close();
		con.close();
		return reviews; 
	}
	
	/**
	 * Add review to a card
	 * @param review
	 * @throws SQLException 
	 */
	public void addReview(ReviewBean review) throws SQLException
	{
		String query = "insert into CardReview (number,rating,content) values (?,?,?)";
		
		Connection con = this.dataSource.getConnection();   
		PreparedStatement statement = con.prepareStatement(query); 
		statement.setInt(1, review.getCardID());
		statement.setInt(2, review.getRating());
		statement.setString(3, review.getContent());
		statement.executeUpdate();  	
		
		statement.close();
		con.close();
	}
		
}
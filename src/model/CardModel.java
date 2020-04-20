package model;

import java.sql.SQLException;
import java.util.List;

import bean.ProductBean;
import dao.CardDAO;

public class CardModel {
	private CardDAO card;
	
    private static CardModel instance;
	
    public static CardModel getInstance() throws ClassNotFoundException{
        if (instance == null) {
            instance = new CardModel();
            instance.card = new CardDAO();
        }
        return instance;
    }

    private CardModel() {}
	
	public List<ProductBean> retrieveCards() throws SQLException{
		return card.retrieveAll();
	}

	/*public ProductBean retrieveCard(String name) {
		return card.retrieve();
	}*/
	
	public ProductBean retrieveCardByID(int id) throws SQLException {
		return card.retrieveByID(id);
	}
	

	public List<ProductBean> search(String searchQuery) throws SQLException{
		return card.search(searchQuery);
	}

}

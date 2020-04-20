package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProductBean;
import bean.ReviewBean;
import dao.CardDAO;

public class ReviewModel {
	private CardDAO card;
	
    private static ReviewModel instance;
	
    public static ReviewModel getInstance() throws ClassNotFoundException{
        if (instance == null) {
            instance = new ReviewModel();
            instance.card = new CardDAO();
        }
        return instance;
    }

	public List<ReviewBean> getReview(int id) throws SQLException{
		return card.getReview(id);
	}
	

	public void addReview(ReviewBean rb) throws SQLException{
		card.addReview(rb);
	}
}

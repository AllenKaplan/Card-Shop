package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.CardBean;

public class CardDAO {
	private DataSource ds;

	public CardDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Map<String, StudentBean> retrieve(String namePrefix, int credit_taken) throws SQLException {
		String query = "select * from students where surname like '%" + namePrefix + "%' and credit_taken >= "
				+ credit_taken;
		Map<String, StudentBean> map = new HashMap<String, StudentBean>();
		Connection connection = this.ds.getConnection();
		PreparedStatement dbQuery = connection.prepareStatement(query);
		ResultSet result = dbQuery.executeQuery();
		while (result.next()) {
			String name = result.getString("GIVENNAME") + ", " + result.getString("SURNAME");
			String sid = result.getString("SID");
			int creditsTaken = result.getInt("CREDIT_TAKEN");
			int creditsToGraduate = result.getInt("CREDIT_GRADUATE");
			StudentBean bean = new StudentBean(sid, name, creditsTaken, creditsToGraduate);
			map.put(sid, bean);
		}
		result.close();
		dbQuery.close();
		connection.close();
		return map;
	}
}

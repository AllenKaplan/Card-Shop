package model;

import dao.AnalyticsDAO;

public class AnalyticsModel {
	private AnalyticsDAO analyticsDAO;
	
    private static AnalyticsModel instance;
	
    public static AnalyticsModel getInstance() throws ClassNotFoundException{
        if (instance == null) {
            instance = new AnalyticsModel();
            instance.analyticsDAO = new AnalyticsDAO();
        }
        return instance;
    }
    
    private AnalyticsModel() {}
}

package dao;

/**
 * Scroll to the BOTTOM of this class and set the value of the instance 
 * constructor argument to the type of access you want to use
 */
public class DatabaseAccess {

	/**
	 * Use this as the named object for the datasource context lookup in DAOs 
	 * when you are developing using your local server. 
	 */
	public static final String DEVELOPMENT_ACCESS = "java:/comp/env/jdbc/EECS";

	/**
	 * Use this as the named object for the datasource context lookup in DAOs 
	 * when you are deploying the app to the cloud foundry. 
	 */
	public static final String DEPLOYMENT_ACCESS = "jdbc/Db2-CardShop";

	/**
	 * This is set to the respective access string required to connect to the 
	 * database to perform either development or deployment
	 */
	public String ACTIVE_ACCESS;

	/**
	 * constructor do not touch anything here
	 * @param access
	 */
	private DatabaseAccess(String access) {
		ACTIVE_ACCESS = access;
	}

	/**
	 * Set the value of access argument in constructor below
	 * to the type of access based off this classes two final strings.
	 * DEVELOPMENT_ACCESS or DEPLOYMENT_ACCESS
	 */
	public static DatabaseAccess instance = new DatabaseAccess(DEPLOYMENT_ACCESS);

}

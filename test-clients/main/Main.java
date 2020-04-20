package main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		test("https://eecs4413-cardshop.mybluemix.net/rest/product?productID=", args[0]);
		test("https://eecs4413-cardshop.mybluemix.net/rest/order?productID=", args[0]);
	}
	
	public static void test(String testUrl, String arg) throws IOException {
			
		System.out.println("Sending Request to " + testUrl + arg);
	
		URL url = new URL(testUrl + arg);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			 
			con.setDoOutput(true);
			
			int status = con.getResponseCode();
			System.out.println("Status " + status);
			
			BufferedReader in = new BufferedReader(
					  new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
					    content.append(inputLine);
			}
			in.close();
			con.disconnect();
			
			System.out.println(content);
	}
}

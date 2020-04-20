package listeners;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import bean.ProductBean;

/**
 * Application Lifecycle Listener implementation class PaymentListener
 *
 */
@WebListener
public class PaymentListener implements HttpSessionAttributeListener {

	private static final String CART = "cart";
	private static final String CARDS_SOLD = "cardsSold";
	private HashMap<Integer, Integer> cardsSold = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> currentCart = new HashMap<Integer, Integer>();
	
    /**
     * Default constructor. 
     */
    public PaymentListener() {
        // TODO Auto-generated constructor stub
    	System.out.println("ALL HAIL THE PAYMENT LISTENER");
    }
    
    private void printCurrentCart() {
    	System.out.println("THE CURRENT CART, ACCORDING TO THE LISTENER:");
    	for(Integer id: currentCart.keySet())
    		System.out.println(id + ": " + currentCart.get(id));
    	System.out.println("END OF THE CURRENT CART");
    }
    
    private void printCardsSold() {
    	System.out.println("THE CARDS SOLD, ACCORDING TO THE LISTENER:");
    	for(Integer id: cardsSold.keySet())
    		System.out.println(id + ": " + cardsSold.get(id));
    	System.out.println("END OF THE CARDS SOLD");
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
        
		boolean isCart = event.getName().equals(CART);
		
		    	
			if(isCart) {
				
				System.out.println("IT LOOKS LIKE SOMEONE HAS CREATED A CART!");
				System.out.println("Source: " + event.getSource());
				
				Map<ProductBean, Integer> cart = (HashMap<ProductBean, Integer>) event.getSession().getAttribute("cart");
		
				currentCart.clear();
    			
    			for(ProductBean product: cart.keySet()) {
    				
    				Integer id = product.getId();
    				Integer quantity = cart.get(product);
    				
    				currentCart.put(id, quantity);
    			}
    			
    			printCurrentCart();
			}
			
			
    	
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
         
    	boolean isCart = event.getName().equals(CART);
    	
    	if(isCart) {
    		
    		System.out.println("IT LOOKS LIKE SOMEONE HAS MESSED WITH THE CART!");
    		System.out.println("Source: " + event.getSource());
    		
    		Map<ProductBean, Integer> cart = (HashMap<ProductBean, Integer>) event.getSession().getAttribute("cart");
    		
    		if(cart.isEmpty()) {
    			
    			System.out.println("SOMEONE HAS EMPTIED THE CART! GOOD THING WE SAVED IT IN CURRENT CART");
    			printCurrentCart();
    			System.out.println("WE'RE ABOUT TO MAKE SOME CHANGES TO CARDS SOLD! HERE'S WHAT IT LOOKS LIKE:");
    			printCardsSold();
    			
    			for(Integer id: currentCart.keySet()) {
    				
    				Integer quantity = currentCart.get(id);
    				
    				System.out.println("ID: " + id);
    				System.out.println("Quantity: " + quantity);
    				
    				if(cardsSold.containsKey(id)) {
    					
    					
    					Integer oldQuantity = cardsSold.get(id);
    					Integer newQuantity = oldQuantity + quantity;
    					
    					System.out.println(id + " IS AN OLD CARD, SO WE'RE ADDING TO THE QUANTITY OF THE OLD CARD");
    					cardsSold.put(id, newQuantity);
    					
    				}
    				
    				else {
    					System.out.println(id + " IS A NEW CARD, SO WE'RE MAKING A NEW KEY");
    					cardsSold.put(id, quantity);
    				}
    					
    			}
    			
    			currentCart.clear();
    			
    			System.out.println("WE'VE EMPTIED THE CURRENT CART, YOU SHOULD SEE CHANGES TO CARDS SOLD");
    			printCardsSold();
    			
    			ServletContext context = event.getSession().getServletContext();
    			context.setAttribute(CARDS_SOLD, cardsSold);
    		}
    		
    		else {
    			
    			currentCart.clear();
    			
    			for(ProductBean product: cart.keySet()) {
    				
    				Integer id = product.getId();
    				Integer quantity = cart.get(product);
    				
    				currentCart.put(id, quantity);
    			}
    		}
    	}
    }
	
}

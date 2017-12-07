import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestEdgeConnector{
	String input = "9|1|7|";
  	
   EdgeConnector edgeConnector = new EdgeConnector(input);
   
   @Test  
	public void testPrintMessage(){
		System.out.println("Inside testPrintMessage()");
      
      System.out.println("Sending string to EdgeConnector");
      
		assertEquals(input, edgeConnector(input));
          
      System.out.println(edgeConnector.edgeConnector(input));
      
	}
   
   
   
}
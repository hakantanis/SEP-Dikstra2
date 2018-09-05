import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class EdgeTest {
	
	Edge e;


	@Test
	void testToString() {
		e = new Edge(10.0);
		e.setDestination(new Node("A",1,3));
		e.setSource(new Node("B",2,4));
		Double weight = 10.0;
		String expected = "Kante von " + e.getSource().toString() + " zu " + e.getDestination().toString()  + " mit dem Gewicht " + weight;
		
		assertEquals(expected, e.toString());
	}

}
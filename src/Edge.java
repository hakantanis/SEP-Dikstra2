
 	public class Edge
   	{
   	
   		private Node quelle = null;
   		private Node ziel = null;
   		private Double gewicht = null;
   		
   		Node getquelle()
   		{
   			return quelle;
   		}
   		
   		
   		Node setquelle(Node newQuelle) // Name vom Knoten?! oder X achse / y achse
   		{
   			return quelle = newQuelle;
   	
   	    }
   		
   		Node getziel()
   		{
   			return ziel;
   		}
   		
   		Node setziel(Node newZiel) // name vom Knoten?! oder X achse / Y achse
   		{
   			return ziel = newZiel;
   		}
   		
   		Double getGewicht()
   		{
   			return gewicht;
   		}
   		
   		Double setGewicht(Double newGewicht)
   		{
   			return gewicht = newGewicht;
   		}
   		
   		public String toString() 
   		{
			String ergebnis = "test";
			
			return ergebnis += quelle.setNameY("test");
   			
   		}
   		
}
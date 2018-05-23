/*
 *
 *
 * @ Author Kevin Adamczewski
 */
public class Edge
{

	private Node source = null;
	private Node destination = null;
	private Double weight = null;

	Edge(Double weight)
	{
		this.weight = weight;
	}

	Node getSource()
	{
		return source;
	}


	void setSource (Node source) // Name vom Knoten?! oder X achse / y achse
			{
		this.source = source;

			}

	Node getDestination()
	{
		return destination;
	}

	void setDestination(Node destination) // name vom Knoten?! oder X achse / Y achse
	{
		this.destination = destination;
	}
	/*
   		Double getWeight()
   		{
   			return weight;
   		}

   		Double setWeight(Double Weight)
   		{
   			return weight = weight;
   		}
	 */
	public String toString()
	{
		String edgeResult = "";

		edgeResult = "Kante von " + source + " zu " + destination  + " mit dem Gewicht " + weight;

		return edgeResult;
	}

}
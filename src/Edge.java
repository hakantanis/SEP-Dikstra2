
/**
 * Project Dijkstra Algorithm
 * This class is used to define edges within half order and algorithm
 *
 * @author Hakan Tanis
 * @author Kevin Adamczewski
 * @author Jonas Litmeyer
 * Date 30.05.2018
 * @version 3.0
 *
 * Last Change:
 * by: Hakan Tanis
 * date: 30.05.2018
 */

public class Edge
{
	/**
	 * @param: source
	 * @param: destination
	 * @param: weight
	 */
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

	/**
	 * @param source set name or x axis or y axis?
	 */
	void setSource (Node source)
			{
		this.source = source;

			}

	Node getDestination()
	{
		return destination;
	}

	/**
	 * @param destination set name or x axis or y axis?
	 */
	void setDestination(Node destination)
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



	/**
	 *
	 * @return
	 */
	public String toString()
	{
		String edgeResult = "";

		edgeResult = "Kante von " + source + " zu " + destination  + " mit dem Gewicht " + weight;

		return edgeResult;
	}

}
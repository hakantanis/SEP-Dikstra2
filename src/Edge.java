import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
 * by: Kevin Adamczewski
 * date: 04.06.2018
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
	private int weight;

	Edge(int rndNum)
	{
		this.weight = rndNum;
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
	
   		int getWeight()
   		{
   			return weight;
   		}
   		int setWeight(Double Weight)
   		{
   			return weight = weight;
   		}
	 



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
	
	 /*
     * @param doc creats XML document to write everthing in the XML
     * @param edges give the document the attribute
     * 
     */
	   public void generateXml (Document doc, Element edges)
	    {
	    	Element edge = doc.createElement("Edge");
	    	edges.appendChild(edge);
	    	Element source = doc.createElement("Source");
	    	source.setTextContent("" + this.source);
	    	edge.appendChild(source);
	    	Element destination = doc.createElement("Destination");
	    	destination.setTextContent("" + this.destination);
	    	edge.appendChild(destination);
	    	Element weight = doc.createElement("Weight");
	    	weight.setTextContent("" + this.weight);
	    	edge.appendChild(weight);
	    }

}
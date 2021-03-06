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

public class Edge extends NodeOrEdgeToColor
{
	/**
	 * @param: source
	 * @param: destination
	 * @param: weight
	 * @param: processed
	 */
	private Node source = null;
	private Node destination = null;
	private Integer weight = null; 
	private boolean processed = false;
	
	/**
	 * @param weight to assign a weight on the Edge
	 */

	Edge(int weight)
	{
		this.weight = weight;
	}

	Node getSource()
	{
		return source;
	}

	/**
	 * @param source set name or x axis or y axis
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
	
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
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
	
	 /**
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
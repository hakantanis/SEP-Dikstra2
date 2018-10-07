import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Project Dijkstra Algorithm
 * This class is used to define the nodes within half order and algorithm
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

public class Node extends NodeOrEdgeToColor
{
    /**
     * @param: x    getting x coordinate
     * @param: y    getting y coordinate
     * @param: name getting name of nodes
     */
    private Integer x = null;
    private Integer y = null;
    private String name = null;
    private Integer shortPath = null;


/**
 * @param name assign direct name of node
 */
    Node(String name)
    {
        this.name = name;
    }
    
    /**
     * @param shortPath set value
     * @return 
     */
    
    Integer setShortPath(Integer shortPath)
    {
    	return this.shortPath = shortPath;
    }
    
    Integer getShortPath()
    {
    	return shortPath;
    }
    String getName()
    {
    return name;
    }
    Integer getX()
    {
        return x;
    }

/**
 * @param x set value
 */
    void setX(Integer x)
    {
        this.x = x;
    }


    Integer getY()
    {
        return y;
    }


/**
 * @param y set value
 */
    void setY(Integer y)
    {
        this.y = y;
    }


    /**
     * @param toString creats how to output name of node and posotion of x and y coordinate
     * @return result of output
     */
    
    public String toString()
    {
    	String result = null;
    	
    	if (name == null)
    	{
    		result = "kein name vorhanden";
    	}
    	else
    	{
    		result = name;
    	}

    	if ( x != null && y != null)
    	{
    		result = result + "(x=" + x + ", " + "y=" + y + ")";
    	}
    	
        
        return result;
    }
    
    
    /*
     * @param doc creats XML document to write everthing in the XML
     * @param nodes give the document the attribute
     * 
     */
    public void generateXml (Document doc, Element nodes)
    {
    	Element node = doc.createElement("Node");
    	nodes.appendChild(node);
    	Element name = doc.createElement("Name");
    	name.setTextContent(this.name);
    	node.appendChild(name);
    	Element x = doc.createElement("X");
    	x.setTextContent("" + this.x);
    	node.appendChild(x);
    	Element y = doc.createElement("Y");
    	y.setTextContent("" + this.y);
    	node.appendChild(y);
    }
    

}
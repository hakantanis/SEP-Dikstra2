
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
 * by: Hakan Tanis
 * date: 30.05.2018
 */

public class Node
{
    /**
     * @param: x    getting x coordinate
     * @param: y    getting y coordinate
     * @param: name getting name of nodes
     */
    private Integer x = null;
    private Integer y = null;
    private String name = null;


/**
 * @param name assign direct name of node
 */
    Node(String name)
    {
        this.name = name;
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
 * @param toString creats how to output
 */
    public String toString()
    {
        String result = null;

        if (name == null)
        {
            result = "kein Name vorhanden";
        } else
        {
            result = name;
        }

        if (x != null && y != null)
        {
            result = result + "(x=" + x + ", " + "y=" + y + ")";
        }
        return result;
    }

}

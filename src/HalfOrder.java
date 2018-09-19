import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import javafx.scene.text.Text;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Project Dijkstra Algorithm This class is used to define the halforder
 *
 * @author Hakan Tanis
 * @author Kevin Adamczewski
 * @author Jonas Litmeyer Date 30.05.2018
 * @version 3.0
 * <p>
 * Last Change: by: Kevin Adamczewski date: 04.06.2018
 */

public class HalfOrder
{
    /**
     * initialisation of methods
     * <img src="halforder.jpg" alt="illustration" >
     */

    public ArrayList<Node> nodes;
    public ArrayList<Edge> edges;
    public ArrayList<ArrayList<Node>> halfOrder;



    /**
     * initialisation of methods
     */
/*	public void initExampleOne()
	{

		 nodes = createNodelistExampleOne();
		//	printNodes(nodes);

		//System.out.println();

		edges = createEdgelistRandom(nodes);
		//	printEdges(edges);

		halfOrder = computeHalfOrder(setFirstNode(nodes, edges), nodes, edges);

		computePositioningData(halfOrder);

	} */
    public void init()
    {

        nodes = createNodelistExampleTwo();

        edges = createEdgelistStatic(nodes);

        halfOrder = computeHalfOrder(setFirstNode(nodes, edges), nodes, edges);

        computePositioningData(halfOrder);

    }



    public void initTwo()
    {
        nodes = createNodelistExampleThree();

        edges = createEdgelistStatic(nodes);

        halfOrder = null;


    }



    /**
     * @param edgelist    prints edges of given edgelist
     * @param description of edge set to be printed as prefix
     */

    public Edge returnEdge(int i)
    {
        if (i < 0 || i >= edges.size())
        {
            return null;
        }
        return edges.get(i);
    }



    private Node setFirstNode(ArrayList<Node> nodelist, ArrayList<Edge> edgelist)
    {
        Node firstNode = new Node("START");

        firstNode.setX(30);
        firstNode.setY(400);

        return firstNode;
    }




    private Node setLastNode(ArrayList<Node> nodelist, ArrayList<Edge> edgelist)
    {
        Node lastNode = new Node("END");

        lastNode.setX(1370);
        lastNode.setY(400);

        return lastNode;
    }



    public Node returnNode(int i)
    {
        if (i < 0 || i >= nodes.size())
        {
            return null;
        }
        return nodes.get(i);
    }



    /**
     * @param nodelist create and add list of edges (example one)
     * @return edgelist with sources, destinations and weights
     */
/*	private ArrayList<Edge> createEdgelistRandom(ArrayList<Node> nodelist)
	{
		ArrayList<Edge> edgelist = new ArrayList<Edge>();

		Random randomWeight = new Random();
		
		int weightEdge = randomWeight.nextInt(15);
		

		edges = edgelist;
		for (int i = 0; i < 40; i++)
		{
			edgelist.add(new Edge(weightEdge));
		}
		
		Random r = new Random();
		
		for (int x= 0; x < edgelist.size();x++)
		{ 

			edgelist.get(x).setSource(nodelist.get(r.nextInt(nodelist.size())));
			edgelist.get(x).setDestination(nodelist.get(r.nextInt(nodelist.size())));
		}

			return edgelist;
	}*/
    private ArrayList<Edge> createEdgelistStatic(ArrayList<Node> nodelist)
    {
        ArrayList<Edge> edgelist = new ArrayList<Edge>();

        edges = edgelist;
        Edge e = new Edge(5);
        Edge e1 = new Edge(3);
        Edge e2 = new Edge(2);
        Edge e3 = new Edge(2);
        Edge e4 = new Edge(1);
        Edge e5 = new Edge(3);
        Edge e6 = new Edge(1);
        Edge e7 = new Edge(1);

        edgelist.add(e);
        edgelist.add(e1);
        edgelist.add(e2);
        edgelist.add(e3);
        edgelist.add(e4);
        edgelist.add(e5);
        edgelist.add(e6);
        edgelist.add(e7);

		/*
		edgelist.add(new Edge(5));
		edgelist.add(new Edge(3));
		edgelist.add(new Edge(2));
		edgelist.add(new Edge(2));
		edgelist.add(new Edge(1));
		edgelist.add(new Edge(3));
		edgelist.add(new Edge(1));
		edgelist.add(new Edge(1));
*/

        edgelist.get(0).setSource(nodelist.get(0));
        edgelist.get(0).setDestination(nodelist.get(1));

        edgelist.get(1).setSource(nodelist.get(0));
        edgelist.get(1).setDestination(nodelist.get(2));

        edgelist.get(2).setSource(nodelist.get(0));
        edgelist.get(2).setDestination(nodelist.get(3));

        edgelist.get(3).setSource(nodelist.get(1));
        edgelist.get(3).setDestination(nodelist.get(4));

        edgelist.get(4).setSource(nodelist.get(2));
        edgelist.get(4).setDestination(nodelist.get(5));

        edgelist.get(5).setSource(nodelist.get(3));
        edgelist.get(5).setDestination(nodelist.get(5));

        edgelist.get(6).setSource(nodelist.get(4));
        edgelist.get(6).setDestination(nodelist.get(6));

        edgelist.get(7).setSource(nodelist.get(5));
        edgelist.get(7).setDestination(nodelist.get(6));

        return edgelist;
    }



    /**
     * @param nodelist    prints nodes
     * @param description of node set to be printed as prefix
     */
    private void printNodes(ArrayList<Node> nodelist, String descr)
    {
        System.out.println("Nodelist : " + descr);
        System.out.println();

        for (int i = 0; i < nodelist.size(); i++)
        {
            System.out.println(nodelist.get(i));
        }

        System.out.println("--------");
    }



    /**
     * create list of nodes (example one)
     *
     * @return list of named nodes (example one)
     */
/*	public ArrayList<Node> createNodelistExampleOne()
	{
		ArrayList<Node> nodelist = new ArrayList<Node>();

		nodes = nodelist;
		
		for (int i = 0; i < 50; i++)
		{
			nodelist.add(new Node ("node " + i));

		}

		return nodelist;

	}*/
    public ArrayList<Node> createNodelistExampleTwo()
    {
        ArrayList<Node> nodelist = new ArrayList<Node>();

        nodes = nodelist;

        nodelist.add(new Node("a"));
        nodelist.add(new Node("b"));
        nodelist.add(new Node("c"));
        nodelist.add(new Node("d"));
        nodelist.add(new Node("e"));
        nodelist.add(new Node("f"));
        nodelist.add(new Node("g"));

        return nodelist;
    }



    public ArrayList<Node> createNodelistExampleThree()
    {
        ArrayList<Node> nodelist = new ArrayList<Node>();

        nodes = nodelist;
        Node node = new Node("a");
        node.setX(50);
        node.setY(200);
        nodelist.add(node);
        Node node1 = new Node("b");
        node1.setX(100);
        node1.setY(250);
        nodelist.add(node1);
        Node node2 = new Node("c");
        node2.setX(100);
        node2.setY(200);
        nodelist.add(node2);
        Node node3 = new Node("d");
        node3.setX(100);
        node3.setY(150);
        nodelist.add(node3);
        Node node4 = new Node("e");
        node4.setX(150);
        node4.setY(225);
        nodelist.add(node4);
        Node node5 = new Node("f");
        node5.setX(150);
        node5.setY(175);
        nodelist.add(node5);
        Node node6 = new Node("g");
        node6.setX(200);
        node6.setY(200);
        nodelist.add(node6);

        return nodelist;
    }



    /**
     * checks if double edges exists
     *
     * @param edgelist    checks if double edges exists
     * @param destination checks if edge e and e2 are the same
     * @return edge if edge e is not the same as edge e2
     * <img src="checkIfDoubleEdges.jpg" alt="illustration" >
     */
    public boolean checkIfDoubleEdges(ArrayList<Edge> edgelist, Node destination)
    {
        for (int i = 0; i < edgelist.size(); i++)
        {
            for (int j = 1; j < edgelist.size(); j++)
            {
                Edge e = edgelist.get(i);
                Edge e2 = edgelist.get(j);

                if (e == null || e2 == null)
                {
                    return false;
                }

                if (e != e2 && e.getDestination() == e2.getDestination() &&
                        e.getSource() == e2.getSource())
                {
                    return true;
                }

            }

        }
        return false;
    }



    /**
     * @param source   computes next node
     * @param edgelist checks source and destination
     * @return of next node
     */
    public ArrayList<Node> computeNextNodes(Node source, ArrayList<Edge> edgelist)
    {
        ArrayList<Node> nextNodes = new ArrayList<Node>();

        for (int i = 0; i < edgelist.size(); i++)
        {
            Edge e = edgelist.get(i);

            if (e.getSource() == source)
            {
                nextNodes.add(e.getDestination());
            }

        }

        return nextNodes;
    }



    /**
     * @param nodeToAdd add node if not redundant
     * @param nodelist  checks if node is included
     * @return node
     * <img src="addNodeIfNotRedundant.jpg" alt="illustration" >
     */
    public ArrayList<Node> addNodeIfNotRedundant(Node nodeToAdd, ArrayList<Node> nodelist)
    {
        for (int i = 0; i < nodelist.size(); i++)
        {
            if (nodelist.get(i) == nodeToAdd)
            {
                return nodelist;
            }
        }

        nodelist.add(nodeToAdd);
        return nodelist;
    }



    /**
     * compute HalfOrder
     *
     * @param firstNode get first node to lock first node
     * @param nodelist  to calculate the next nodelists in the halforder
     * @param edgelist  to put edges between the placement of nodes in the halforder
     * @return put nodes into the list of lists of the amount of nodes that are given
     */
    public ArrayList<ArrayList<Node>> computeHalfOrder(Node firstNode, ArrayList<Node> nodelist,
                                                       ArrayList<Edge> edgelist)
    {
        ArrayList<ArrayList<Node>> listOfLists = new ArrayList<ArrayList<Node>>();
        ArrayList<Node> allNodesProcessed = new ArrayList<Node>();

        ArrayList<Node> stepTarget = computeNextNodes(firstNode, edgelist);
        listOfLists.add(stepTarget);
        allNodesProcessed.addAll(stepTarget);

        int i = 1;

        printNodes(stepTarget, "" + i);
        printNodes(allNodesProcessed, i + " processed");

        while (stepTarget.size() > 0)
        {
            ArrayList<Node> stepSource = stepTarget;
            stepTarget = new ArrayList<Node>();

            for (int j = 0; j < stepSource.size(); j++)
            {
                ArrayList<Node> partStep = computeNextNodes(stepSource.get(j), edgelist);
                magicalAdd(partStep, stepTarget, allNodesProcessed);
            }

            if (stepTarget.size() > 0)
            {
                listOfLists.add(stepTarget);

                printNodes(stepTarget, "" + ++i);
                printNodes(allNodesProcessed, i + " processed");
            }

        }

        return listOfLists;
    }



    public void computePositioningData(ArrayList<ArrayList<Node>> halfOrder)
    {

        for (int x = 0; x < halfOrder.size(); x++)
        {
            ArrayList<Node> nodes = halfOrder.get(x);

            for (int y = 0; y < nodes.size(); y++)
            {
                Node node = nodes.get(x);
                node.setX(x * 30);
                node.setY(y * 5);
            }
        }
    }



    /**
     * @param source           of node
     * @param destination      of node to node
     * @param alreadyProcessed add already processed node to already processed list
     *                         <img src="halforder.jpg" alt="illustration" >
     */
    public void magicalAdd(ArrayList<Node> source, ArrayList<Node> destination,
                           ArrayList<Node> alreadyProcessed)
    {
        for (Node node : source)
        {
            if (!alreadyProcessed.contains(node))
            {
                destination.add(node);
                alreadyProcessed.add(node);
            }
        }
    }

}
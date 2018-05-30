import java.util.ArrayList;
import java.util.Set;

/**
 * Project Dijkstra Algorithm
 * This class is used to define the halforder
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

public class HalfOrder
{
    /**
     * initialisation of methods
     */
    public void init()
    {

        ArrayList<Node> nodelist = createNodelistExampleOne();
        //	printNodes(nodelist);

        System.out.println();

        ArrayList<Edge> edgelist = createEdgelistExampleOne(nodelist);
        //	printEdges(edgelist);

        ArrayList<ArrayList<Node>> listOfLists =
                computeHalfOrder(getFirstNode(nodelist), nodelist, edgelist);

    }

    /**
     * get the first node
     * @param nodelist
     * @return
     */
    public Node getFirstNode(ArrayList<Node> nodelist)
    {
        return nodelist.get(0);

    }

    /**
     * prints edges
     * @param edgelist
     * @param descr
     */
    private void printEdges(ArrayList<Edge> edgelist, String descr)
    {
        System.out.println("Edges: " + descr);

        for (int i = 0; i < edgelist.size(); i++)
        {
            System.out.println(edgelist.get(i));
        }

        System.out.println("--------");
    }

    /**
     * create list of edges (example one)
     * @param nodelist
     * @return
     */
    private ArrayList<Edge> createEdgelistExampleOne(ArrayList<Node> nodelist)
    {
        ArrayList<Edge> edgelist = new ArrayList<Edge>();
        edgelist.add(new Edge(8.0));
        edgelist.add(new Edge(10.0));
        edgelist.add(new Edge(5.0));
        edgelist.add(new Edge(3.0));
        edgelist.add(new Edge(2.2));
        edgelist.add(new Edge(9.0));
        edgelist.add(new Edge(20.5));
        // setting destination and source
        edgelist.get(0).setDestination(nodelist.get(1));
        edgelist.get(0).setSource(nodelist.get(0));

        edgelist.get(1).setDestination(nodelist.get(3));
        edgelist.get(1).setSource(nodelist.get(1));

        edgelist.get(2).setDestination(nodelist.get(4));
        edgelist.get(2).setSource(nodelist.get(2));

        edgelist.get(3).setDestination(nodelist.get(5));
        edgelist.get(3).setSource(nodelist.get(3));

        edgelist.get(4).setDestination(nodelist.get(5));
        edgelist.get(4).setSource(nodelist.get(4));

        edgelist.get(5).setDestination(nodelist.get(2));
        edgelist.get(5).setSource(nodelist.get(0));

        edgelist.get(6).setDestination(nodelist.get(5));
        edgelist.get(6).setSource(nodelist.get(0));

        return edgelist;
    }

    /**
     * prints nodes
     * @param nodelist
     * @param descr
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
     * @return
     */
    private ArrayList<Node> createNodelistExampleOne()
    {
        ArrayList<Node> nodelist = new ArrayList<Node>();

        nodelist.add(new Node("Knoten 1 "));
        nodelist.add(new Node("Knoten 2 "));
        nodelist.add(new Node("Knoten 3 "));
        nodelist.add(new Node("Knoten 4 "));
        nodelist.add(new Node("Knoten 5 "));
        nodelist.add(new Node("Knoten 6 "));
        return nodelist;
    }

    /**
     * checks if double edges
     * @param edgelist
     * @param destination
     * @return
     */
    public Boolean checkIfDoubleEdges(ArrayList<Edge> edgelist, Node destination)
    {
        for (int i = 0; i < edgelist.size(); i++)
        {
            for (int j = 1; j < edgelist.size(); j++)
            {
                Edge e = edgelist.get(i);
                Edge e2 = edgelist.get(j);

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
     * computes next node
     * @param source
     * @param edgelist
     * @return
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
     * add node if not redundant
     * @param nodeToAdd
     * @param nodelist
     * @return
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
     * @param firstNode
     * @param nodelist
     * @param edgelist
     * @return
     */
    public ArrayList<ArrayList<Node>> computeHalfOrder(Node firstNode, ArrayList<Node> nodelist, ArrayList<Edge> edgelist)
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

    /**
     * add destinations list
     * @param source
     * @param destination
     * @param alreadyProcessed
     */
    public void magicalAdd(ArrayList<Node> source, ArrayList<Node> destination, ArrayList<Node> alreadyProcessed)
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


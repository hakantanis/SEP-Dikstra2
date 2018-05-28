import java.util.ArrayList;
import java.util.Set;

public class HalfOrder


{

	public void init()
	{


		ArrayList<Node> nodelist = createNodelistExampleOne();
	//	printNodes(nodelist);

		System.out.println();

		ArrayList<Edge> edgelist = createEdgelistExampleOne(nodelist);
	//	printEdges(edgelist);


		ArrayList<ArrayList <Node>> listOfLists = computeHalfOrder(getFirstNode(nodelist), nodelist, edgelist);

	}



	public Node getFirstNode (ArrayList <Node> nodelist)
	{		
		return nodelist.get(0);

	}

	private void printEdges(ArrayList<Edge> edgelist, String descr) 
	{
		System.out.println("Edges: " + descr);
		
		for(int i = 0; i < edgelist.size(); i++)
		{
				System.out.println(edgelist.get(i));
		}
		
		System.out.println("--------");
	}

	private ArrayList<Edge> createEdgelistExampleOne(ArrayList <Node> nodelist) 
	{
		ArrayList<Edge> edgelist = new ArrayList<Edge>();
		edgelist.add(new Edge (8.0));
		edgelist.add(new Edge (10.0));
		edgelist.add(new Edge (5.0));
		edgelist.add(new Edge (3.0));
		edgelist.add(new Edge (2.2));
		edgelist.add(new Edge (9.0));
		edgelist.add(new Edge (20.5));

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

	private void printNodes(ArrayList<Node> nodelist, String descr) 
	{
		System.out.println("Nodes: " + descr);

		for(int i = 0; i < nodelist.size(); i++)
		{
			System.out.println(nodelist.get(i));
		}
		
		System.out.println("--------");
	}

	private ArrayList<Node> createNodelistExampleOne() {
		ArrayList<Node> nodelist = new ArrayList<Node>();
		// Erstellung Knoten
		nodelist.add(new Node("Knoten 1 "));
		nodelist.add(new Node("Knoten 2 "));
		nodelist.add(new Node("Knoten 3 "));
		nodelist.add(new Node("Knoten 4 "));
		nodelist.add(new Node("Knoten 5 "));
		nodelist.add(new Node("Knoten 6 "));
		return nodelist;
	}

	public Boolean checkIfDoubleEdges (ArrayList<Edge> edgelist, Node destination)
	{
		for (int i = 0; i < edgelist.size(); i++)
		{
			for(int j = 1; j < edgelist.size(); j++)
			{
				Edge e = edgelist.get(i);
				Edge e2 = edgelist.get(j);

				if (e != e2 
						&& e.getDestination() == e2.getDestination() 
						&& e.getSource() == e2.getSource())
				{
					return true;
				}

			}

		}
		return false;
	}

	public ArrayList<Node> computeNextNodes (Node source, ArrayList<Edge> edgelist)						// Berechnung von nächsten Knoten
	{
		ArrayList<Node> nextNodes = new ArrayList<Node>();

		for(int i = 0; i < edgelist.size(); i++)
		{
			Edge e = edgelist.get(i);

			if(e.getSource() == source)
			{
				nextNodes.add(e.getDestination());
			}

		}

		return nextNodes;
	}

	public ArrayList<Node> addNodeIfNotRedundant (Node nodeToAdd, ArrayList<Node> nodelist)
	{
		for(int i = 0; i < nodelist.size(); i++)	
		{
			if (nodelist.get(i) == nodeToAdd)	
			{
				return nodelist;
			}
		}

		nodelist.add(nodeToAdd);
		return nodelist;
	}

	public ArrayList<ArrayList<Node>> computeHalfOrder(Node firstNode, ArrayList <Node> nodelist, ArrayList <Edge> edgelist)
	{
		ArrayList<ArrayList <Node>> listOfLists = new ArrayList<ArrayList <Node>>();
		ArrayList<Node> allNodesProcessed = new ArrayList<Node>();

		ArrayList<Node> stepTarget = computeNextNodes(firstNode, edgelist);
		listOfLists.add(stepTarget);
		allNodesProcessed.addAll(stepTarget);
		
		int i = 1;
		
		printNodes(stepTarget,"" + i);
		printNodes(allNodesProcessed, i + " all");

		while (stepTarget.size() > 0)
		{
			ArrayList<Node> stepSource = stepTarget;
			stepTarget = new ArrayList<Node>();

			for(int j = 0; j < stepSource.size(); j++)
			{
				ArrayList <Node> partStep = computeNextNodes(stepSource.get(j), edgelist);
				magicalAdd(partStep, stepTarget, allNodesProcessed);
			}

			if (stepTarget.size() > 0)
			{
				listOfLists.add(stepTarget);
				
				printNodes(stepTarget, "" + ++i);
				printNodes(allNodesProcessed, i + " all");
			}
			
			
		}

		return listOfLists;
	}


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


	public void printHalfOrder (ArrayList <ArrayList <Node>> computeHalforder)
	{


	}


}

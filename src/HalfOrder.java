import java.util.ArrayList;

public class HalfOrder


{

	public void init()
	{

		ArrayList<Node> nodelist = new ArrayList<Node>();
		// Erstellung Knoten
		nodelist.add(new Node("Knoten 1 "));
		nodelist.add(new Node("Knoten 2 "));
		nodelist.add(new Node("Knoten 3 "));
		nodelist.add(new Node("Knoten 4 "));
		nodelist.add(new Node("Knoten 5 "));
		nodelist.add(new Node("Knoten 6 "));
		// X und Y Werte an Knoten vergeben " Noch " selbst
		//	k1.name("Knoten1");
		nodelist.get(0).setX(100);
		nodelist.get(0).setY(200);

		nodelist.get(1).setX(150);
		nodelist.get(1).setY(300);

		nodelist.get(2).setX(150);
		nodelist.get(2).setY(100);

		nodelist.get(3).setX(200);
		nodelist.get(3).setY(300);

		nodelist.get(4).setX(200);
		nodelist.get(4).setY(100);

		nodelist.get(5).setX(250);
		nodelist.get(5).setY(200);


		// Ausgabe  Knoten in Console
		for(int i = 0; i < nodelist.size();i++)
		{
			if (nodelist.size() >= i)
			{
				System.out.println(nodelist.get(i));
			}
			else{
				System.out.println("--------");
			}

		}

		System.out.println("");

		ArrayList<Edge> edgelist = new ArrayList<Edge>();


		// Erstellung Kanten
		edgelist.add(new Edge (8.0));
		edgelist.add(new Edge (10.0));
		edgelist.add(new Edge (5.0));
		edgelist.add(new Edge (3.0));
		edgelist.add(new Edge (2.2));
		edgelist.add(new Edge (9.0));
		edgelist.add(new Edge (20.5));


		edgelist.get(0);
		edgelist.get(1);
		edgelist.get(2);
		edgelist.get(3);
		edgelist.get(4);
		edgelist.get(5);
		edgelist.get(6);


		// Erstellung Kanten Quelle / Ziel
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

		// Ausgabe von Knoten x zu Knoten y
		for(int i = 0; i < edgelist.size();i++)
		{
			if (edgelist.size() >= i)
			{
				System.out.println(edgelist.get(i));
			}
			else
			{
				System.out.println("--------");
			}

		}

		System.out.println();
		System.out.println("---Start---");																// Ausgabe Anfangsknoten
		System.out.println(nodelist.get(0));

		// Ausgabe weiterer NextNodeListen in Console // von Schleife unten
		/*	
		ArrayList<Node> nextNode = ComputeNextNodes(nodelist.get(0), edgelist);					
		printNodeList(nextNode);

		ArrayList<Node> nextNode1 = ComputeNextNodes(nextNode.get(0), edgelist);
		printNodeList(nextNode1);

		ArrayList<Node> nextNode2 = ComputeNextNodes(nextNode.get(1), edgelist);
		printNodeList(nextNode2);
		System.out.println("");

		System.out.println("---End---");																// Ausgabe Endknoten
		System.out.println(nodelist.get(5));
		 */
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

	public ArrayList<Node> ComputeNextNodes (Node source, ArrayList<Edge> edgelist)						// Berechnung von nächsten Knoten
	{
		ArrayList<Node> nextNode = new ArrayList<Node>();

		for(int i = 0; i < edgelist.size(); i++)
		{
			Edge e = edgelist.get(i);

			if(e.getSource() == source)
			{
				nextNode.add(e.getDestination());
			}
		}
		
		return nextNode;
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

	public ArrayList<ArrayList <Node>> printList (ArrayList<Node> nodelist)
	{
		ArrayList<ArrayList<Node>> print = new ArrayList <ArrayList <Node>>();
		
		
		for (int i = 0; i < nodelist.size(); i++)
		{
			System.out.println(nodelist.get(i));
		}
		System.out.println(nodelist);
		return print;
		
	}

	public void printNodeList(ArrayList<Node> nextNode)											// Schleife zur Ausgabe von Knotenlistne
	{
		System.out.println();
		System.out.println("---NextNodeList---");
		for(int i = 0; i < nextNode.size(); i++)
		{
			System.out.println(nextNode.get(i));	
		}

	}
}

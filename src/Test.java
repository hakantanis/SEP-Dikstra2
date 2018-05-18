

import java.util.ArrayList;

public class Test


{

	public void init()
	{

		ArrayList<Node> nodelist = new ArrayList<Node>();

		nodelist.add(new Node("Knoten 1 "));
		nodelist.add(new Node("Knoten 2 "));
		nodelist.add(new Node("Knoten 3 "));
		nodelist.add(new Node("Knoten 4 "));
		nodelist.add(new Node("Knoten 5 "));
		nodelist.add(new Node("Knoten 6 "));

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

		ArrayList<Edge> edgelist = new ArrayList<Edge>();



		edgelist.add(new Edge(8.0));//
		edgelist.add(new Edge (10.0));//
		edgelist.add(new Edge (5.0));//
		edgelist.add(new Edge (3.0));//
		edgelist.add(new Edge (2.2));
		edgelist.add(new Edge (9.0));
		edgelist.add(new Edge (20.5));//

		edgelist.get(0);
		edgelist.get(1);
		edgelist.get(2);
		edgelist.get(3);
		edgelist.get(4);
		edgelist.get(5);
		edgelist.get(6);


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
	 
		ArrayList<Node> nextNode = computeNextNodes(nodelist.get(0), edgelist);
		printNodeList(nextNode);
		System.out.println("---");
		
		ArrayList<Node> nextNode1 = computeNextNodes(nextNode.get(0), edgelist);
		printNodeList(nextNode1);
		
		ArrayList<Node> nextNode2 = computeNextNodes(nextNode.get(1), edgelist);
		printNodeList(nextNode2);
		
		ArrayList<Node> nextNode3 = computeNextNodes(nextNode.get(2), edgelist);
		printNodeList(nextNode3);
	}

	public ArrayList<Node> computeNextNodes (Node source, ArrayList<Edge> edgelist)
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
	
	
	public void printNodeList(ArrayList<Node> nextNode)
	{
		System.out.println();
		System.out.println("---PRINTNODELIST---");
		for(int i = 0; i < nextNode.size(); i++)
		{
		System.out.println(nextNode.get(i));	
		}
		
	}

	
}



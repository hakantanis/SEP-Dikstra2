import java.util.ArrayList;

public class DijkstraAlgorithmus 
{
	HalfOrder hO = new HalfOrder();

	public Node firstNode = new Node("START");
	public Node lastNode = new Node("END");


	private ArrayList<Node> setFirstAndLastNodes(ArrayList<Edge> edgelist, ArrayList<Node> nodelist,Node source,Node destination)
	{
		firstNode.setX(30);
		firstNode.setY(400);

		lastNode.setX(1370);
		lastNode.setY(400);

		ArrayList<Node> setFirstNode = new ArrayList<Node>();

		for(int i = 0; i < nodelist.size(); i++)
		{
			// ?!?!?!?!?!
			edgelist.get(0).setSource(firstNode);
			edgelist.get(0).setDestination(nodelist.get(i));
			// ?!?!?!?!
		}
		return setFirstNode;
	}
	private ArrayList<Node> passedNodes (ArrayList<Edge> edgelist, ArrayList<Node> nodelist,Node source,Node destination, ArrayList<ArrayList <Node>> listOfLists)
	{
		ArrayList<Node> passedNode = new ArrayList<Node>();
		
		for(int i = 0; i < edgelist.size(); i++)
		{
				if (nodelist.size() < i)
				{
					int nextList = 1;
					nextList++;
					listOfLists.get(nextList);
				}

				
				
				edgelist.get(i).setSource(nodelist.get(i));
		}
		return passedNode;
	}
}

import java.util.ArrayList;

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
 * by: Kevin Adamczewski
 * date: 04.06.2018
 */


public class DijkstraAlgorithmus 
{
	HalfOrder hO = null;

	public Node startNode = null;
	public Node targetNode = null;

	public Node currentNode = null;
	public Node currentTarget = null;
	public Edge currentEdge = null;

	public Integer stepCounter = null;

	public ArrayList<Node> nextStepNodes = null;
	public ArrayList<Node> currentStepNodes = null;


	public DijkstraAlgorithmus (HalfOrder hO,Node startNode,Node targetNode)
	{
		this.hO = hO;
		this.startNode = startNode;
		this.targetNode = targetNode;

		nextStepNodes = new ArrayList<Node>(); 
		currentStepNodes = new ArrayList<Node>();
		currentStepNodes.add(startNode);
		currentNode = startNode;
	}

	public Edge getNextEdge(Node source)
	{
		ArrayList<Edge> outGoingEdges = new ArrayList<Edge>();

		for (Edge e: hO.edges)
		{
			if(e.getSource() == source)
			{
				outGoingEdges.add(e);
			}
		}

		if(outGoingEdges.size() == 0)
		{
			return null;
		}

		if(currentEdge == null)
		{
			currentEdge = outGoingEdges.get(0);
		}
		else
		{
			int i = 0;
			for (;i < outGoingEdges.size(); i++)
			{
				if(currentEdge == outGoingEdges.get(i))
				{
					break;
				}

			}

			if(i >= outGoingEdges.size())
			{
				return null;
			}
			i++;
			currentEdge = outGoingEdges.get(i);
		}

		return currentEdge;

	}

	public Node getNextNode (Node destination)
	{
		ArrayList<Node> nextNodes = new ArrayList<Node>();

		for(Node n : hO.nodes)
		{
			if(currentEdge.getDestination() == destination) // keine Ahnung ob richtig
			{
				nextNodes.add(currentNode);
			}
		}
		if(currentNode == null)
		{
			currentNode = nextNodes.get(0);
		}
		else
		{
			int i = 0;
			for (;i < nextNodes.size(); i++)
			{
				if(currentNode == nextNodes.get(i))
				{
					break;
				}
			}
			if(i >= nextNodes.size())
			{
				return null;
			}
			i++;
			currentNode = nextNodes.get(i);
		}

		return currentNode;
	}

	public NodeOrEdgeToColor getNextStep()
	{
		NodeOrEdgeToColor nOr = null;

		switch(stepCounter)
		{
		case 0:
			nOr = currentNode;
			currentNode.toColor = Color.investigateNodeOrEdge;
			System.out.println(stepCounter + " . " + nOr);
			stepCounter++;
			break;
		case 1:
			Edge e = getNextEdge(currentNode);
			if (e != null)
			{
				nOr = currentEdge;
			}
			Node n = getNextNode(currentNode);
			if ( n != null)
			{
				nOr = currentNode;
			}
			System.out.println(stepCounter + " . " + nOr);
			stepCounter++;
			break;
		case 2:
			currentTarget = currentEdge.getDestination();
			currentTarget.toColor = Color.investigateNodeOrEdge;
			nOr = currentTarget;
			nextStepNodes.add(currentTarget);
			System.out.println(stepCounter + " . " + nOr);
			stepCounter++;
			break;
		case 3:
			if (currentTarget.getShortPath() > currentNode.getShortPath() + currentEdge.getWeight())
			{
				currentTarget.setShortPath(currentNode.getShortPath() + currentEdge.getWeight());
				currentTarget.toColor = Color.updateWeight;
				nOr = currentTarget;
			}
			System.out.println(stepCounter + " . " + nOr);
			stepCounter++;
			break;
		case 4:
			currentTarget.toColor = Color.noColor;
			nOr = currentTarget;
			System.out.println(stepCounter + " . " + nOr);
			stepCounter++;
			break;
		case 5:
			currentEdge.toColor = Color.noColor;
			nOr = currentEdge;
			System.out.println(stepCounter + " . " + nOr);
			stepCounter = 1;
			break;
		}

		return nOr;
	}

	public ArrayList<Node> computeFromNodelist(ArrayList<Edge> edgelist, ArrayList<Node> nodelist,Node firstNode)
	{
		ArrayList<Node> nextNodesList = new ArrayList<Node>();

		for(Node node : nodelist)
		{
			nextNodesList.addAll(computeNextNodes(node, edgelist, nodelist));
		}
		return nextNodesList;
	}

	public ArrayList<Node> computeNextNodes(Node node, ArrayList<Edge> edgelist, ArrayList<Node> nodelist)
	{
		ArrayList<Node> nextNodes = new ArrayList<Node>();

		for (Node n : nextNodes) 
		{
			for (Edge e : edgelist)
			{
				if (e.getSource() == n)
				{
					Node destination = e.getDestination();

					destination.setShortPath(Math.min(destination.getShortPath(), n.getShortPath() + e.getWeight()));

					nextNodes.add(destination);
				}
			}
		}
		return nextNodes;
	}
}


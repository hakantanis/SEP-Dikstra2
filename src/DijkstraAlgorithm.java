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
 * date: 28.09.2018
 */

/**
 * @param: hO
 * 
 * @param: startNode
 * @param: targetNode
 * 
 * @param: currentNode
 * @param: currentTarget
 * @param: currentEdge
 * 
 * @param: stepCounter
 * 
 * @param: nextStepNodes
 * @param: currentStepNodes
 */
public class DijkstraAlgorithm 
{
	HalfOrder hO = null;

	public Node startNode = null;
	public Node targetNode = null; 
	public Integer shortPath = null;
	public Integer savePath = null;


	public Node currentNode = null;
	public Node currentTarget = null;
	public Edge currentEdge = null;
	public Node previousNode = null;

	public Integer stepCounter = null;

	public ArrayList<Node> nextStepNodes = null;
	public ArrayList<Node> currentStepNodes = null;

	/**
	 * 
	 * @param hO		 assign the HalfOrder into the DijkstraAlgorithm
	 * @param startNode  assign the startNode
	 * @param targetNode assign the targetNode
	 * 
	 */

	public DijkstraAlgorithm (HalfOrder hO,Node startNode,Node targetNode)
	{
		this.hO = hO;
		this.startNode = startNode;
		this.targetNode = targetNode;
		stepCounter = 0;

		nextStepNodes = new ArrayList<Node>(); 
		currentStepNodes = new ArrayList<Node>();
		currentStepNodes.add(startNode);
		currentNode = startNode;

	}
	/**
	 * 
	 * @param source is needed to complete the list of outGoingEdges
	 * @return
	 */

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

			if(i >= outGoingEdges.size() -1)
			{
				currentEdge = null;
				return null;
			}
			i++;
			currentEdge = outGoingEdges.get(i);
		}

		return currentEdge;

	}

	/**
	 * 
	 * @param destination is needed to complete nextNodes list
	 * @return
	 */


	public Node getNextNode (Node currentNode)
	{
		int i = 0;
		for (;i < currentStepNodes.size(); i++)
		{
			if(currentNode == currentStepNodes.get(i))
			{
				break;
			}
		}
		if(i >= currentStepNodes.size()-1)
		{
			if(nextStepNodes.size() == 0)
			{
				return null;
			}

			currentStepNodes.clear();
			currentStepNodes.addAll(nextStepNodes);
			nextStepNodes.clear();
			return currentStepNodes.get(0);		
		}
		i++;

		currentNode = currentStepNodes.get(i);

		return currentNode;
	}

	/**
	 * NodeOrEdgeToColor is made to set all passed nodes or edges into a Color
	 * @return nOr
	 */


	public NodeOrEdgeToColor getNextStep()
	{
		NodeOrEdgeToColor nOr = null;

		switch(stepCounter)
		{
		case 0:
			nOr = currentNode;
			currentNode.toColor = ColorXY.investigateNodeOrEdge;
			System.out.println(stepCounter + " . " + nOr);
			stepCounter++;
			break;
		case 1:
			Edge e = getNextEdge(currentNode);
			if (e != null)
			{
				currentEdge.toColor = ColorXY.investigateNodeOrEdge;
				nOr = currentEdge;
				stepCounter++;
			}
			else
			{
				currentNode = getNextNode(currentNode);
				if ( currentNode != null)
				{
					nOr = currentNode;
				}
			}
			System.out.println(stepCounter + " . " + nOr);
			break;
		case 2:
			currentTarget = currentEdge.getDestination();
			currentTarget.toColor = ColorXY.investigateNodeOrEdge;
			nOr = currentTarget;
			if (!nextStepNodes.contains(currentTarget))
				nextStepNodes.add(currentTarget);
			System.out.println(stepCounter + " . " + nOr);
			stepCounter++;
			break;
		case 3:
			if (currentTarget.getShortPath() == null )
			{
				currentTarget.setShortPath(currentEdge.getWeight());
				previousNode = currentTarget;
				currentTarget.toColor = ColorXY.updateWeight;
				nOr = currentTarget;
			}
			else if (currentTarget.getShortPath() > currentNode.getShortPath() + currentEdge.getWeight())
			{
				currentTarget.setShortPath(currentNode.getShortPath() + currentEdge.getWeight());
				if(previousNode.getShortPath() < currentTarget.getShortPath())
				{	
					previousNode.setShortPath(currentTarget.getShortPath());
				}
				currentTarget.toColor = ColorXY.updateWeight;
				nOr = currentTarget;
			}
			else 
			{
				nOr = currentNode;
			}
			System.out.println(stepCounter + " . " + nOr);
			stepCounter++;
			break;
		case 4:
			nOr = currentEdge;
			System.out.println(stepCounter + " . " + nOr);
			stepCounter++;
			break;
		case 5:
			currentTarget.toColor = ColorXY.noColor;
			nOr = currentTarget;
			System.out.println(stepCounter + " . " + nOr);
			stepCounter = 1;
			break;
		}

		return nOr;
	}

	/**
	 * 
	 * @param edgelist get the edgelist from HalfOrder to compute Algorithm
	 * @param nodelist get the nodelist from HalfOrder to compute Algorithm
	 * @param firstNode is needed to compute nextNodes on it
	 * @return
	 */

	public ArrayList<Node> computeFromNodelist(ArrayList<Edge> edgelist, ArrayList<Node> nodelist,Node firstNode)
	{
		ArrayList<Node> nextNodesList = new ArrayList<Node>();

		for(Node node : nodelist)
		{
			nextNodesList.addAll(computeNextNodes(node, edgelist, nodelist));
		}
		return nextNodesList;
	}

	/**
	 * 
	 * @param node 		needed to compute algorithm with shortPath
	 * @param edgelist	needed to compute algorithm for destinations and sources
	 * @param nodelist	needed to compute algorithm to set and get of destination and sources
	 * @return
	 */
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


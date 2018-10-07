import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * <h1>Project Dijkstra Algorithm This class is used to define the PerfectLineManipulator</h1>
 *
 * @author Hakan Tanis
 * @version 3.0
 * <p>
 * Last Change: by: Hakan Tanis date: 24.09.2018
 */

/**
 * <h2>Example of dragging anchors around to manipulate a line</h2>
 * <p>
 * group contains node, edges, weights and position of weights hO of HalfOrder
 * nodesWithXY is a HashMap of class XYProperties weightNumbers is a ArrayList
 */
public class PerfectLineManipulator extends Application 
{
	AnchorPane group = new AnchorPane();
	private HalfOrder hO = new HalfOrder();
	private HashMap<Node, XYProperties> nodesWithXY = new HashMap<Node, XYProperties>();
	private HashMap<Node, Anchor> node2Anchor =  new HashMap<Node, Anchor>();
	private HashMap<Edge, BoundLine> edge2BoundLine = new HashMap<Edge, BoundLine>();
	private ArrayList<Text> weightNumbers;
	private DijkstraAlgorithm dA = null;


	public static void main(String[] args) throws Exception
	{
		launch(args);
	}


	/**
	 * <h2>initialisation of methods</h2>
	 * <p>
	 * 
	 * @param window
	 *            set x & y DoubleProperty to new SimpleDoubleProperty get nodes and
	 *            edges from HalfOrder = hO create Anchor and adding it to group
	 *            create line from Boundline, set Properties and add to group create
	 *            scene & stage
	 */
	@Override
	public void start(final Stage window) throws Exception 
	{
		double stageX = 1400.0;
		double stageY = 700.0;

		Label labelNode = new Label("Anzahl Knoten:");
		AnchorPane.setTopAnchor(labelNode, 60.0);
		AnchorPane.setLeftAnchor(labelNode, 0.0);
		group.getChildren().add(labelNode);

		TextField textFieldNode = new TextField ();
		AnchorPane.setTopAnchor(textFieldNode, 60.0);
		AnchorPane.setLeftAnchor(textFieldNode, 100.0);
		group.getChildren().add(textFieldNode);

		Label labelEdge = new Label("Anzahl Kanten:");
		AnchorPane.setTopAnchor(labelEdge, 60.0);
		AnchorPane.setLeftAnchor(labelEdge, 300.0);
		group.getChildren().add(labelEdge);

		TextField textFieldEdge = new TextField ();
		AnchorPane.setTopAnchor(textFieldEdge, 60.0);
		AnchorPane.setLeftAnchor(textFieldEdge, 400.0);
		group.getChildren().add(textFieldEdge);


		Button generate = new Button("generate Nodes");
		AnchorPane.setTopAnchor(generate, 25.0);
		AnchorPane.setLeftAnchor(generate, 0.0);
		generate.setOnAction(e ->  
		{
			hO.init(Integer.parseInt(textFieldNode.getText()), Integer.parseInt(textFieldEdge.getText()));
			hO.computeHalfOrder(hO.nodes.get(0), hO.nodes, hO.edges);
			hO.computePositioningData();
			weight();
			dA = new DijkstraAlgorithm(hO,hO.nodes.get(0), hO.nodes.get(Integer.parseInt(textFieldEdge.getText())-1));
		});
		group.getChildren().add(generate);

		Button Example1 = new Button("Example1");
		AnchorPane.setTopAnchor(Example1, 25.0);
		AnchorPane.setLeftAnchor(Example1, 130.0);
		Example1.setOnAction(e ->  
		{			
			hO.initOne();
			hO.computeHalfOrder(hO.nodes.get(0), hO.nodes, hO.edges);
			hO.computePositioningData();
			weight();
			dA = new DijkstraAlgorithm(hO,hO.nodes.get(0),hO.nodes.get(6));
		});
		group.getChildren().add(Example1);

		Button Example2 = new Button("Example2");
		AnchorPane.setTopAnchor(Example2, 25.0);
		AnchorPane.setLeftAnchor(Example2, 235.0);
		Example2.setOnAction(e ->  
		{
			hO.initTwo();
			hO.computeHalfOrder(hO.nodes.get(0), hO.nodes, hO.edges);
			hO.computePositioningData();
			weight();
			dA = new DijkstraAlgorithm(hO,hO.nodes.get(0),hO.nodes.get(7));
		});
		group.getChildren().add(Example2);

		Button Example3 = new Button("Example3");
		AnchorPane.setTopAnchor(Example3, 25.0);
		AnchorPane.setLeftAnchor(Example3, 340.0);
		Example3.setOnAction(e ->  
		{
			hO.initThree();
			hO.computeHalfOrder(hO.nodes.get(0), hO.nodes, hO.edges);
			hO.computePositioningData();
			weight();
			dA = new DijkstraAlgorithm(hO,hO.nodes.get(0),hO.nodes.get(10));
		});
		group.getChildren().add(Example3);

		Button nextStep = new Button("next Step");
		AnchorPane.setRightAnchor(nextStep, 0.0);
		AnchorPane.setBottomAnchor(nextStep, 0.0);
		nextStep.setOnAction(e -> 
		{

			NodeOrEdgeToColor colorForNodeOrEdge = dA.getNextStep();
			
			Color color = null;
			
			switch(colorForNodeOrEdge.toColor)
			{
			case updateWeight:
				color = Color.BLACK;
				break;
			case investigateNodeOrEdge:
				color = Color.ORANGE;
				break;
			case noColor:
				color = Color.GREY;
				break;
			case shortPathColor:
				color = Color.RED;
				break;
			
			}
			if(colorForNodeOrEdge instanceof Node)
			{
				Node node = (Node) colorForNodeOrEdge;
				Anchor anchor = node2Anchor.get(node);
				anchor.setStroke(color);
				anchor.setFill(color);
			}
			else
			{
				Edge edge = (Edge) colorForNodeOrEdge;
				BoundLine line = edge2BoundLine.get(edge);
				line.setStroke(color);
			}

		});
		group.getChildren().add(nextStep);

		Button clear = new Button("clear graph");
		AnchorPane.setTopAnchor(clear, 25.0);
		AnchorPane.setLeftAnchor(clear, 440.0);
		clear.setOnAction(e ->
		{
			group.getChildren().clear();
			group.getChildren().add(generate);
			group.getChildren().add(Example1);
			group.getChildren().add(Example2);
			group.getChildren().add(Example3);
			group.getChildren().add(nextStep);
			group.getChildren().add(clear);
			group.getChildren().add(labelEdge);
			group.getChildren().add(textFieldEdge);
			group.getChildren().add(labelNode);
			group.getChildren().add(textFieldNode);
			
		});
		group.getChildren().add(clear);

		window.setTitle("Dijkstra Algorithmus");
		window.setScene(new Scene(group, stageX, stageY, Color.ALICEBLUE));
		window.show();
	}


	/**
	 * <h2>Weights are created at the start of the program</h2>
	 * <p>
	 * hO.edges to Arraylist = edgelist1 weightNumbers set weight in list tmpText to
	 * display weightNumbers in Text position & positon1 to locate source and
	 * destination of node for edge allEdges determine weight of edge from edgelist1
	 * and saves it in String gewicht1 textXX & textYY positions where to add weight
	 * on edge set Text,X,Y for tmpText add weight, X & Y coordinate (tmpText) to
	 * list weightNumbers add weightNumbers to group
	 */
	public void weight() {
		ArrayList<Edge> edgelist1 = hO.edges;
		weightNumbers = new ArrayList<Text>();

		node2Anchor.clear();
		edge2BoundLine.clear();

		for (int n = 0; n < hO.nodes.size(); n++) 
		{
			Node node = hO.nodes.get(n);

			if (node.getX() == null)
			{
				continue;
			}
			

			DoubleProperty x = new SimpleDoubleProperty(node.getX());
			DoubleProperty y = new SimpleDoubleProperty(node.getY());
			XYProperties xyprop = new XYProperties(x, y);
			nodesWithXY.put(node, xyprop);
			
			String nameNode = ("N" + n);
			Label label = new Label(nameNode);
			label.setLayoutX(hO.nodes.get(n).getX()-5);
			label.setLayoutY(hO.nodes.get(n).getY()-30);
			group.getChildren().add(label);

			Anchor anchor = new Anchor(Color.GREY, x, y);
			node2Anchor.put(node, anchor);
			group.getChildren().add(anchor);
		}

		for (int n = 0; n < hO.edges.size(); n++) 
		{

			Edge edge = hO.edges.get(n);

			if (edge.getSource().getX() == null || edge.getDestination().getX() == null) 
			{
				continue;
			}

			XYProperties xyPropSource = nodesWithXY.get(edge.getSource());
			XYProperties xyPropDest = nodesWithXY.get(edge.getDestination());

			BoundLine line = new BoundLine(xyPropSource.xProperty(), xyPropSource.yProperty(), xyPropDest.xProperty(),
					xyPropDest.yProperty());

			edge2BoundLine.put(edge, line);
			group.getChildren().add(line);
		}

		for (int i = 0; i < edgelist1.size(); i++)
		{
			Text tmpText = new Text();

			Node position = edgelist1.get(i).getSource();
			Node position1 = edgelist1.get(i).getDestination(); 

			Edge allEdges1 = edgelist1.get(i);
			String gewicht1 = String.valueOf(allEdges1.getWeight());

			if (position.getX() == null || position1.getX() == null)
			{
				continue;
			}
			double textXX = ((position.getX() + position1.getX()) / 2);
			double textYY = ((position.getY() + position1.getY()) / 2);

			tmpText.setText(gewicht1);
			tmpText.setX(textXX);
			tmpText.setY(textYY);

			weightNumbers.add(tmpText);

		}

		group.getChildren().addAll(weightNumbers);

	}

	/**
	 * <h2>method: update to get new position of textXX & textYY for weightNumbers
	 * to give updateCoords</h2>
	 */
	public void update()
	{

		ArrayList<Edge> edgelist = hO.edges;
		for (int i = 0; i < edgelist.size(); i++)
		{
			Node position = edgelist.get(i).getSource(); 
			Node position1 = edgelist.get(i).getDestination(); 

			double textXX = ((position.getX() + position1.getX()) / 2);
			double textYY = ((position.getY() + position1.getY()) / 2);

			weightNumbers.get(i).setX(textXX);
			weightNumbers.get(i).setY(textYY);
		}

		updateCoords();
	}

	/**
	 * <h2>Coordinates of saved weights are updated</h2>
	 * <p>
	 * nodesWithXY of nodes is keyseted from Hashmap where position of node is
	 * undefined List @param: hO.nodes with existing nodes is created Array with
	 * size of created list is created add elements of nodelist at m to Array j is
	 * the position in the alphabet ( a=0, b=1, c=2,...) j for position in alphabet
	 * to run through name of nodes Position of nodes is undefined due to the keyset
	 * of nodeWithXY therefor the position in the alphabet is determined trough a if
	 * statement new position in j is updated Is the statement not met, j is
	 * incremented by 1
	 *
	 */
	public void updateCoords() 
	{
		Set<Node> nodes = nodesWithXY.keySet();
		ArrayList<Node> nodeList = hO.nodes; 
		String nodeNames[] = new String[nodeList.size()];

		for (int m = 0; m < nodeList.size(); m++) 
		{
			String nodename = nodeList.get(m).getName();
			nodeNames[m] = nodename;
		} 

		for (Node node : nodes) 
		{

			int j = 0;

			for (String abc : nodeNames) 
			{

				if (abc.equals(node.getName())) 
				{
					XYProperties xyProperties = nodesWithXY.get(node);

					int xAchsis = (int) xyProperties.xProperty().get();
					int yAchsis = (int) xyProperties.yProperty().get();

					hO.nodes.get(j).setX(xAchsis);
					hO.nodes.get(j).setY(yAchsis);
				}

				j++;
			}
		}
	}

	/**
	 * <h2><set class Boundline extends Line with DoubleProperties to display
	 * edge/h2>
	 * <p>
	 * startX of edge startY of edge endX of Edge endY of Edge set look of Line
	 *
	 */
	class BoundLine extends Line 
	{
		BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) 
		{
			startXProperty().bind(startX);
			startYProperty().bind(startY);
			endXProperty().bind(endX);
			endYProperty().bind(endY);
			setStrokeWidth(3);

			setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.5));

			setStrokeLineCap(StrokeLineCap.BUTT);
			setMouseTransparent(true);

		}
	}


	/**
	 * <h2>set @see class Anchor extends Circle with DoubleProperties to create
	 * draggable anchor displayed around a point</h2> x position of node y position
	 * of node color set look of Anchor (Node)
	 */
	class Anchor extends Circle
	{
		Anchor(Color color, DoubleProperty x, DoubleProperty y)
		{
			super(x.get(), y.get(), 10);
			
			setFill(color.deriveColor(1, 1, 1, 0.5));
			setStroke(color);
			setStrokeWidth(2);
			setStrokeType(StrokeType.OUTSIDE);

			x.bind(centerXProperty());
			y.bind(centerYProperty());
			enableDrag();

		}

		/**
		 * <h2>method enableDrag to determine movement of node if being dragged
		 * around</h2> dragDelta x & y to record distance for the drag and drop
		 * operation method @see update is called whenever a Anchor is dragged
		 */
		private void enableDrag()
		{
			final Delta dragDelta = new Delta();
			setOnMousePressed(new EventHandler<MouseEvent>() 
			{
				@Override
				public void handle(MouseEvent mouseEvent) 
				{
					dragDelta.x = getCenterX() - mouseEvent.getX();
					dragDelta.y = getCenterY() - mouseEvent.getY();
					getScene().setCursor(Cursor.MOVE);

				}
			});
			setOnMouseReleased(new EventHandler<MouseEvent>() 
			{
				@Override
				public void handle(MouseEvent mouseEvent) 
				{
					getScene().setCursor(Cursor.HAND);
				}
			});
			setOnMouseDragged(new EventHandler<MouseEvent>() 
			{
				@Override
				public void handle(MouseEvent mouseEvent) 
				{
					double newX = mouseEvent.getX() + dragDelta.x;
					if (newX > 0 && newX < getScene().getWidth())
					{
						setCenterX(newX);
					}
					double newY = mouseEvent.getY() + dragDelta.y;
					if (newY > 0 && newY < getScene().getHeight())
					{
						setCenterY(newY);
					}

					update();

				}
			});
			setOnMouseEntered(new EventHandler<MouseEvent>() 
			{
				@Override
				public void handle(MouseEvent mouseEvent) 
				{
					if (!mouseEvent.isPrimaryButtonDown()) 
					{
						getScene().setCursor(Cursor.HAND);
					}
				}
			});
			setOnMouseExited(new EventHandler<MouseEvent>() 
			{
				@Override
				public void handle(MouseEvent mouseEvent) 
				{
					if (!mouseEvent.isPrimaryButtonDown()) 
					{
						getScene().setCursor(Cursor.HAND);

					}
				}
			});
		}

		/**
		 * <h2>Delta x & y records relative coordinates</h2> x Delta records relative x
		 * coordinate y records relative y coordinate
		 */
		private class Delta 
		{
			double x, y;
		}
	}
}
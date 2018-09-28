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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
public class PerfectLineManipulator extends Application {

	AnchorPane group = new AnchorPane();
	private HalfOrder hO = new HalfOrder();
	private Node startNode;
	private Node targetNode;
	private HashMap<Node, XYProperties> nodesWithXY = new HashMap<Node, XYProperties>();
	//private HashMap<Node, Anchor> colorNodes = new HashMap<Node, Anchor>();
//	private HashMap<Edge, Boundline> colorEdges = new HashMap<Edge, B>();
	private ArrayList<Text> weightNumbers;
	private DijkstraAlgorithm dA = new DijkstraAlgorithm(hO,startNode,targetNode);

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	/**
	 * <h2>initialisation of methods</h2>
	 * <p>
	 * 
	 * @param secondStage
	 *            set x & y DoubleProperty to new SimpleDoubleProperty get nodes and
	 *            edges from HalfOrder = hO create Anchor and adding it to group
	 *            create line from Boundline, set Properties and add to group create
	 *            scene & stage
	 */
	@Override
	public void start(final Stage secondStage) throws Exception {

		double stageX = 1400.0;
		double stageY = 700.0;

		MenuBar mb = new MenuBar();
		mb.setUseSystemMenuBar(true);
		Menu m = new Menu("File");
		mb.getMenus().add(m);

		MenuItem save = new MenuItem("Save");
		MenuItem load = new MenuItem("Load");
		MenuItem mnew = new MenuItem("New");
		m.getItems().add(mnew);
		m.getItems().add(save);
		m.getItems().add(load);

		load.setOnAction(e -> {
			FileChooser c = new FileChooser();
			c.showOpenDialog(secondStage);
		});

		save.setOnAction(e -> {
			FileChooser c = new FileChooser();
			c.showOpenDialog(secondStage);
		});
		group.getChildren().add(mb);

		secondStage.setTitle("Dijkstra");
		Button generate = new Button();
		generate.setText("generate Nodes");
		AnchorPane.setTopAnchor(generate, 25.0);
		AnchorPane.setLeftAnchor(generate, 0.0);
		generate.setOnAction(e ->  {
			hO.initOne();
		});
		group.getChildren().add(generate);
		
		Button Example1 = new Button();
		Example1.setText("Example1");
		AnchorPane.setTopAnchor(Example1, 25.0);
		AnchorPane.setLeftAnchor(Example1, 130.0);
		Example1.setOnAction(e ->  {
			hO.initOne();
		});
		group.getChildren().add(Example1);
	
		Button Example2 = new Button();
		Example2.setText("Example2");
		AnchorPane.setTopAnchor(Example2, 25.0);
		AnchorPane.setLeftAnchor(Example2, 235.0);
		Example2.setOnAction(e ->  {
	//		hO.initTwo();
		});
		group.getChildren().add(Example2);
		
		Button Example3 = new Button();
		Example3.setText("Example3");
		AnchorPane.setTopAnchor(Example3, 25.0);
		AnchorPane.setLeftAnchor(Example3, 340.0);
		Example3.setOnAction(e ->  {
		//	hO.initThree();
		});
		group.getChildren().add(Example3);
	
		Button shortestPath = new Button();
		shortestPath.setText("show shortest Path");
		AnchorPane.setLeftAnchor(shortestPath, 0.0);
		AnchorPane.setBottomAnchor(shortestPath, 0.0);
		shortestPath.setOnAction(e -> {
			
		});
		group.getChildren().add(shortestPath);

		Button nextStep = new Button();
		nextStep.setText("next Step");
		AnchorPane.setRightAnchor(nextStep, 0.0);
		AnchorPane.setBottomAnchor(nextStep, 0.0);
		nextStep.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		group.getChildren().add(nextStep);

		hO.initOne();
		//hO.init();
		hO.computeHalfOrder(hO.nodes.get(0), hO.nodes, hO.edges);
		hO.computePositioningData();
		weight();

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

			Anchor anchor = new Anchor(Color.GREY, x, y);
			group.getChildren().add(anchor);
		}
		for (int n = 0; n < hO.edges.size(); n++) {

			Edge edge = hO.edges.get(n);

			if (edge.getSource().getX() == null || edge.getDestination().getX() == null) 
			{
				continue;
			}

			XYProperties xyPropSource = nodesWithXY.get(edge.getSource());
			XYProperties xyPropDest = nodesWithXY.get(edge.getDestination());

			BoundLine line = new BoundLine(xyPropSource.xProperty(), xyPropSource.yProperty(), xyPropDest.xProperty(),
					xyPropDest.yProperty());
			group.getChildren().add(line);
		}

		secondStage.setTitle("Line Manipulation Sample");
		secondStage.setScene(new Scene(group, stageX, stageY, Color.ALICEBLUE));
		secondStage.show();
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

		for (int i = 0; i < edgelist1.size(); i++) {

			Text tmpText = new Text();

			Node position = edgelist1.get(i).getSource();
			Node position1 = edgelist1.get(i).getDestination(); 

			Edge allEdges1 = edgelist1.get(i);
			String gewicht1 = String.valueOf(allEdges1.getWeight());

			if (position.getX() == null || position1.getX() == null) {
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
	public void update() {

		ArrayList<Edge> edgelist = hO.edges;
		for (int i = 0; i < edgelist.size(); i++) {

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
	public void updateCoords() {

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
	class BoundLine extends Line {
		BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
			startXProperty().bind(startX);
			startYProperty().bind(startY);
			endXProperty().bind(endX);
			endYProperty().bind(endY);
			setStrokeWidth(3);
			setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.5));
			// setStroke1(Color.YELLOW.deriveColor(0, 1, 1, 0.5));
			// setStroke2(Color.GREEN.deriveColor(0, 1, 1, 0.5));
			setStrokeLineCap(StrokeLineCap.BUTT);
			setMouseTransparent(true);
		}
	}


	/**
	 * <h2>set @see class Anchor extends Circle with DoubleProperties to create
	 * draggable anchor displayed around a point</h2> x position of node y position
	 * of node color set look of Anchor (Node)
	 */
	class Anchor extends Circle {
		Anchor(Color color, DoubleProperty x, DoubleProperty y) {
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
		private void enableDrag() {
			final Delta dragDelta = new Delta();
			setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {

					dragDelta.x = getCenterX() - mouseEvent.getX();
					dragDelta.y = getCenterY() - mouseEvent.getY();
					getScene().setCursor(Cursor.MOVE);

				}
			});
			setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					getScene().setCursor(Cursor.HAND);
				}
			});
			setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					double newX = mouseEvent.getX() + dragDelta.x;
					if (newX > 0 && newX < getScene().getWidth()) {
						setCenterX(newX);
					}
					double newY = mouseEvent.getY() + dragDelta.y;
					if (newY > 0 && newY < getScene().getHeight()) {
						setCenterY(newY);
					}

					update();

				}
			});
			setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (!mouseEvent.isPrimaryButtonDown()) {
						getScene().setCursor(Cursor.HAND);
					}
				}
			});
			setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (!mouseEvent.isPrimaryButtonDown()) {
						getScene().setCursor(Cursor.HAND);

					}
				}
			});
		}

		/**
		 * <h2>Delta x & y records relative coordinates</h2> x Delta records relative x
		 * coordinate y records relative y coordinate
		 */
		private class Delta {
			double x, y;
		}
	}
}
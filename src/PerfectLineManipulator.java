import javafx.application.Application;
import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import java.util.*;

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
 * group contains node, edges, weights and position of weights
 * hO of HalfOrder
 * nodesWithXY is a HashMap of class XYProperties
 * weightNumbers is a ArrayList
 * */
    public class PerfectLineManipulator extends Application
    {

    public Group group = new Group();
    private HalfOrder hO = new HalfOrder();
    private HashMap<Node, XYProperties> nodesWithXY = new HashMap<Node, XYProperties>();
    private ArrayList<Text> weightNumbers;

    public static void main(String[] args) throws Exception { launch(args); }
    /**
     * <h2>initialisation of methods</h2>
     * <p>
     * @param secondStage
     * set x & y DoubleProperty to new SimpleDoubleProperty
     * get nodes and edges from HalfOrder = hO
     * create Anchor and adding it to group
     * create line from Boundline, set Properties and add to group
     * create scene & stage
     */
    @Override public void start(final Stage secondStage) throws Exception
    {
        hO.initTwo();
        weight();

        for (int n = 0; n <  hO.nodes.size(); n++)
        {
            Node node = hO.nodes.get(n);

            DoubleProperty x = new SimpleDoubleProperty(node.getX());
            DoubleProperty y = new SimpleDoubleProperty(node.getY());
            XYProperties xyprop = new XYProperties(x,y);
            nodesWithXY.put(node, xyprop);

            Anchor anchor = new Anchor(Color.GREY, x, y);
            group.getChildren().add(anchor);
        }
        for (int n = 0; n< hO.edges.size() ; n++)
        {
            Edge edge = hO.edges.get(n);
            XYProperties xyPropSource = nodesWithXY.get(edge.getSource());
            XYProperties xyPropDest = nodesWithXY.get(edge.getDestination());

            BoundLine line = new BoundLine(xyPropSource.xProperty(),xyPropSource.yProperty(), xyPropDest.xProperty(), xyPropDest.yProperty());
            group.getChildren().add(line);
        }

        secondStage.setTitle("Line Manipulation Sample");
        secondStage.setScene(new Scene(group, 400, 400, Color.ALICEBLUE));
        secondStage.show();
    }

    // Die Startgewichte werden beim Start des Programms erstellt
     /**
      * <h2>Weights are created at the start of the program </h2>
      * <p>
      * hO.edges to Arraylist = edgelist1
      * weightNumbers set weight in list
      * tmpText to display weightNumbers in Text
      * position & positon1 to locate source and destination of node for edge
      * allEdges determine weight of edge from edgelist1 and saves it in String gewicht1
      * textXX & textYY positions where to add weight on edge
      * set Text,X,Y for tmpText
      * add weight, X & Y coordinate (tmpText) to list weightNumbers
      * add weightNumbers to group
      */
    public void  weight ()
    {
        ArrayList<Edge> edgelist1 = hO.edges;
        weightNumbers = new ArrayList<Text>();

        for (int i = 0; i < edgelist1.size(); i++)
        {
            // Der Text für das Gewicht wird erstellt
            Text tmpText = new Text();

            // Die Positionen werden in einer Node lokalisiert
            Node position = edgelist1.get(i).getSource(); // Der Anfangspunkt von der Kante
            Node position1 = edgelist1.get(i).getDestination(); // Endpunkt der Kante

            // Die Zahlen der Gewichte werden aus der Edgelist ermittelt und in ein String gespeichert
            Edge allEdges1 = edgelist1.get(i);
            String gewicht1 = String.valueOf(allEdges1.getWeight());

            // Die Position der Gewichte werden errechnet, damit die Mitte lokalisiert werden kann
            double textXX = ((position.getX() + position1.getX()) / 2);
            double textYY = ((position.getY() + position1.getY()) / 2);

            // Das Gewicht und die X und Y Koordinate wird dem Text übergeben
            tmpText.setText(gewicht1);
            tmpText.setX(textXX);
            tmpText.setY(textYY);

            // Der Text mit Gewicht, X und Y Koordinate wird der Liste "weightNumbers" hinzugefügt
            weightNumbers.add(tmpText);

        }
        // Dann werden die weightNumbers der Gruppe hinzugefügt
        group.getChildren().addAll(weightNumbers);

    }

    // Die Koordinaten der Gewichte werden zwischengespeichert
     /**
      * <h2>method: update to get new position of textXX & textYY for weightNumbers to give updateCoords</h2>
      */
    public void update(){

        ArrayList<Edge> edgelist1 = hO.edges;
        for (int i = 0; i < edgelist1.size(); i++)
        {
            // Die Positionen werden in einer Node lokalisiert
            Node position = edgelist1.get(i).getSource(); // Der Anfangspunkt von der Kante
            Node position1 = edgelist1.get(i).getDestination(); // Endpunkt der Kante

            // Die Position der Gewichte wird errechnet, damit die Mitte lokalisiert werden kann
            double textXX = ((position.getX() + position1.getX()) / 2);
            double textYY = ((position.getY() + position1.getY()) / 2);

            // Die X und Y Koordinaten werden für die Gewichte geupdatet
            weightNumbers.get(i).setX(textXX);
            weightNumbers.get(i).setY(textYY);
        }

        updateCoords();
    }

    // Die Koordinaten der gespeicherten Gewichte werden geupdated
    /**
     * <h2>Coordinates of saved weights are updated</h2>
     * <p>
     * nodesWithXY of nodes is keyseted from Hashmap where position of node is undefined
     * List @param: hO.nodes with existing nodes is created
     * Array with size of created list is created
     * add elements of nodelist at m to Array
     * j is the position in the alphabet ( a=0, b=1, c=2,...)
     * j for position in alphabet to run through name of nodes
     * Position of nodes is undefined due to the keyset of nodeWithXY therefor the position in the alphabet is determined trough a if statement
     * new position in j is updated
     * Is the statement not met, j is incremented by 1
     *
     */
    public void updateCoords()
    {
        // Die Hashmap der X und Y Koordinate der Nodes werden gekeysetted
        // Dabei sind die Positionen der Knoten in der Hashmap undefiniert
        Set<Node> nodes = nodesWithXY.keySet();
        ArrayList<Node> nodeList = hO.nodes; // Eine Liste mit den vorhandenen Nodes wird erstellt
        String nodeNames[] = new String[nodeList.size()]; // Ein Array wird mit der Größe der erstellten Liste erstellt

        // Die Elemente der Nodelist werden an der Stelle "m" zu dem Array hinzugefügt
        for (int m = 0; m < nodeList.size(); m++)
        {
            String nodename = nodeList.get(m).getName();
            nodeNames[m] = nodename;
        } // Resultat: nodeNames ["a", "b", "c", "d", "e", "f", "g"]

        for (Node node : nodes)
        {
            // Position des Alphabets
            int j = 0;
            // Die Namen der Knoten werden chronologisch durchlaufen
            for (String abc: nodeNames)
            {
                // Da bei dem keyset der nodeWithXY die Positionen der Hashmap undefiniert sind
                // wird durch die if Bedingung die Position des Alphabets ermittelt
                if (abc.equals(node.getName()))
                {
                    XYProperties xyProperties = nodesWithXY.get(node);

                    int xAchsis = (int) xyProperties.xProperty().get();
                    int yAchsis = (int) xyProperties.yProperty().get();

                    // Die neue Position wird an der Stelle "j" geupdated
                    // Die Stelle "j" ist die Positon vom Alphabet ("a" = 0, "b" = 1, "c" = 2)
                    hO.nodes.get(j).setX(xAchsis);
                    hO.nodes.get(j).setY(yAchsis);
                }
                // Wenn die Bedingung nicht erfüllt ist, wird "j" um "1" erhöht
                j++;
            }
        }
    }

    /**
     * <h2><set class Boundline extends Line with DoubleProperties to display edge/h2>
     * <p>
     * startX of edge
     * startY of edge
     * endX of Edge
     * endY of Edge
     * set look of Line
     *
     */
    class BoundLine extends Line
    {
        BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
            startXProperty().bind(startX);
            startYProperty().bind(startY);
            endXProperty().bind(endX);
            endYProperty().bind(endY);
            setStrokeWidth(3);
            setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.5));
            //setStroke1(Color.YELLOW.deriveColor(0, 1, 1, 0.5));
            //setStroke2(Color.GREEN.deriveColor(0, 1, 1, 0.5));
            setStrokeLineCap(StrokeLineCap.BUTT);
            setMouseTransparent(true);
        }
    }

    // a draggable anchor displayed around a point.

    /**
     * <h2>set @see class Anchor extends Circle with DoubleProperties to create draggable anchor displayed around a point</h2>
     * x position of node
     * y position of node
     * color
     * set look of Anchor (Node)
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

        // make a node movable by dragging it around with the mouse.
        /**
         * <h2>method enableDrag to determine movement of node if being dragged around</h2>
         * dragDelta x & y to record distance for the drag and drop operation
         * method @see update is called whenever a Anchor is dragged
         */
        private void enableDrag()
        {
            final Delta dragDelta = new Delta();
            setOnMousePressed(new EventHandler<MouseEvent>()
            {
                @Override public void handle(MouseEvent mouseEvent)
                {
                    // record a delta distance for the drag and drop operation.
                    dragDelta.x = getCenterX() - mouseEvent.getX();
                    dragDelta.y = getCenterY() - mouseEvent.getY();
                    getScene().setCursor(Cursor.MOVE);

                }
            });
            setOnMouseReleased(new EventHandler<MouseEvent>()
            {
                @Override public void handle(MouseEvent mouseEvent)
                {
                    getScene().setCursor(Cursor.HAND);
                }
            });
            setOnMouseDragged(new EventHandler<MouseEvent>()
            {
                @Override public void handle(MouseEvent mouseEvent)
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
                    // Die Methode update(); wird beim draggen immer aufgerufen
                    update();

                }
            });
            setOnMouseEntered(new EventHandler<MouseEvent>()
            {
                @Override public void handle(MouseEvent mouseEvent)
                {
                    if (!mouseEvent.isPrimaryButtonDown())
                    {
                        getScene().setCursor(Cursor.HAND);
                    }
                }
            });
            setOnMouseExited(new EventHandler<MouseEvent>()
            {
                @Override public void handle(MouseEvent mouseEvent)
                {
                    if (!mouseEvent.isPrimaryButtonDown())
                    {
                        getScene().setCursor(Cursor.HAND);
//                        weight(dragDelta.x, dragDelta.y);
                        //group.getChildren().add(text);
                    }
                }
            });
        }

        /**
         * <h2>Delta x & y records relative coordinates</h2>
         * x Delta records relative x coordinate
         * y records relative y coordinate
         */
        private class Delta
        {
            double x, y;
        }
    }
}
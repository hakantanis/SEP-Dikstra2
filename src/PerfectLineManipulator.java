import javafx.application.Application;
import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;



import org.w3c.dom.NodeList;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/** Example of dragging anchors around to manipulate a line. */
public class PerfectLineManipulator extends Application
{
    public static void main(String[] args) throws Exception { launch(args); }
    @Override public void start(final Stage stage) throws Exception
    {
        HalfOrder hO = new HalfOrder();
        hO.initTwo();

        Group group = new Group();
        HashMap<Node, XYProperties> nodesWithXY = new HashMap<Node, XYProperties>();
        Text weight = new Text();

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

            BoundLine line = new BoundLine(xyPropSource.xProperty(),xyPropSource.yProperty(),
                    xyPropDest.xProperty(), xyPropDest.yProperty());
            group.getChildren().add(line);
        }
        for (int n = 0; n < hO.edges.size(); n++)

        //private void dataPosition (ArrayList<Node> nodelist,Node firstNode, ArrayList<Edge> edgelist)
        {
            ArrayList<Node> nodelist = hO.nodes;
            ArrayList<Edge> edgelist = hO.edges;

            Node firstNode = new Node("START");
            firstNode.setX(30);
            firstNode.setY(400);

            for (int j = 0; j < edgelist.size(); j++)
            {
                for (int i = 0; i < nodelist.size(); i++)
                {
                    Node position = nodelist.get(i);
                    Edge allEdges = edgelist.get(j);

                    String gewicht = String.valueOf(allEdges.getWeight());

                    int textx = (firstNode.getX() + position.getX()) / 2;
                    int texty = (firstNode.getY() + position.getY()) / 2;
                    Text text = new Text(textx, texty, gewicht);
                    group.getChildren().add(text);
                }
            }
        }
        stage.setTitle("Line Manipulation Sample");
        stage.setScene(new Scene(group, 400, 400, Color.ALICEBLUE));
        stage.show();

       /* ArrayList<DoubleProperty> listOfProperties = new ArrayList<DoubleProperty>();
        for (int i = 0; i <  ; i++)
        {
            listOfProperties.add(new SimpleDoubleProperty(edgelist.get(i).source.x));
            listOfProperties.add(new SimpleDoubleProperty(edgelist.get(i).source.y));
            listOfProperties.add(new SimpleDoubleProperty(edgelist.get(i).destination.x));
            listOfProperties.add(new SimpleDoubleProperty(edgelist.get(i).destination.y));
        }
        */

       /* DoubleProperty startX = new SimpleDoubleProperty(100);
        DoubleProperty startY = new SimpleDoubleProperty(100);
        DoubleProperty testX  = new SimpleDoubleProperty(300);
        DoubleProperty testY  = new SimpleDoubleProperty(300);
        DoubleProperty endX   = new SimpleDoubleProperty(300);
        DoubleProperty endY   = new SimpleDoubleProperty(200); */



        //Anchor start    = new Anchor(Color.PALEGREEN, startX, startY);
        //Anchor test    = new Anchor(Color.GREY, testX, testY);
        //Anchor end      = new Anchor(Color.TOMATO,    endX,   endY);

        //??
       /* ArrayList<listOfLists> ListsOfHalforder = new ArrayList<listOfLists>();
            for (int i = 0; i < listOfLists.size; i++)
            {
                return  Node;
            }

        ArrayList<Line> listOfLines = new ArrayList<Line>();
            for (int i = 0; (i < edgelist); i++)
            {
                listOfLines.add(new BoundLine(
                        {
                        edgelist.get(i).source.x;
                        edgelist.get(i).source.y;
                        edgelist.get(i).destination.x;
                        edgelist.get(i).destination.y;
                        }
            }
        */
       // Line line = new BoundLine(startX, startY, endX, endY);
       // Line line1= new BoundLine(testX, testY,startX, startY);

    }
    private void dataPosition (ArrayList<Node> nodelist, Node firstNode, ArrayList<Edge> edgelist)
    {
        for (int j = 0; j < edgelist.size(); j++)
        {
            for (int i = 0; i < nodelist.size(); i++)
            {
                Node position = nodelist.get(i);
                Edge allEdges = edgelist.get(j);
                String gewicht = String.valueOf(allEdges.getWeight());

                int textx = (firstNode.getX() + position.getX()) / 2;
                int texty = (firstNode.getY() + position.getY()) / 2;
                Text text = new Text(textx, texty, gewicht);
                text.setFont(new Font(20));
                text.setTextAlignment(TextAlignment.CENTER);
                text.setText(gewicht);
            }
        }
    }

    class BoundLine extends Line
    {
        BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
            startXProperty().bind(startX);
            startYProperty().bind(startY);
            endXProperty().bind(endX);
            endYProperty().bind(endY);
            setStrokeWidth(3);
            setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.5));
            //setStroke(Color.YELLOW.deriveColor(0, 1, 1, 0.5));
            //setStroke(Color.GREEN.deriveColor(0, 1, 1, 0.5));
            setStrokeLineCap(StrokeLineCap.BUTT);
            setMouseTransparent(true);
        }
    }

    // a draggable anchor displayed around a point.
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
                        getScene().setCursor(Cursor.DEFAULT);
                    }
                }
            });
        }

        // records relative x and y co-ordinates.
        private class Delta { double x, y; }
    }
}
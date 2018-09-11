
/**
 * Project Dijkstra Algorithm
 * This class is used to show an example of a circle
 *
 * @author Hakan Tanis
 * @author Kevin Adamczewski
 * @author Jonas Litmeyer
 * Date 30.05.2018
 * @version 3.0
 *
 * Last Change:
 * by: Hakan Tanis
 * date: 30.05.2018
 */

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

/** Example of dragging anchors around to manipulate a line. */
public class LineManipulator extends Application {
    public static void main(String[] args) throws Exception { launch(args); }
    @Override public void start(final Stage stage) throws Exception {

        /** initValue shows where the node is located in the scene. */
        VBox box = new VBox();
        final Scene scene = new Scene(box,300, 250);
        scene.setFill(null);
        Line line = new Line();

        Line line2 = new Line(10.0f,0.3f,200.0f,200.0f);

        line.setStartX(0.0f);
        line.setStartY(0.0f);
        line.setEndX(100.0f);
        line.setEndY(100.0f);

        box.getChildren().add(line);

        stage.setScene(scene);
        stage.show();
        /*
        DoubleProperty startX = new SimpleDoubleProperty(100);
        DoubleProperty startY = new SimpleDoubleProperty(100);
        DoubleProperty nodeX  = new SimpleDoubleProperty(300);
        DoubleProperty endX   = new SimpleDoubleProperty(300);
        DoubleProperty endY   = new SimpleDoubleProperty(200);
        DoubleProperty nodeY  = new SimpleDoubleProperty(300);

        Anchor start    = new Anchor(Color.PALEGREEN, startX, startY);
        Anchor node    = new Anchor(Color.DARKGRAY, nodeX, nodeY);
        Anchor end      = new Anchor(Color.TOMATO,    endX,   endY);

        Line line = new BoundLine(startX, startY, endX, endY,nodeX,nodeY);
       // Line line2 = new BoundLine (endX, endY, nodeX,nodeY,startY,startX);
        stage.setTitle("Line Manipulation Sample");
        stage.setResizable(true);
        stage.setScene(new Scene(new Group(line, start, node, end), 400, 400, Color.ALICEBLUE));
        stage.Line().add(line);
        stage.setMaxWidth(1200);
        stage.setMaxHeight(600);
        stage.show();
 */
      /*



    */
    }

    class BoundLine extends Line {
        BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY, DoubleProperty nodeX, DoubleProperty nodeY)
        {
            startXProperty().bind(startX);
            startYProperty().bind(startY);
            nodeXProperty().bind(nodeX);
            nodeYProperty().bind(nodeY);
            endXProperty().bind(endX);
            endYProperty().bind(endY);
            setStrokeWidth(3);
            setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.5));
            setStrokeLineCap(StrokeLineCap.BUTT);
            //getStrokeDashArray().setAll(10.0, 5.0);
            setMouseTransparent(true);
        }
    }
            // Define the property
            private DoubleProperty nodeX = new SimpleDoubleProperty();
            // Define a getter for the property's value
            public final double getnodeXProperty(){return nodeX.get();}
            // Define a setter for the property's value
            public final void setNodeXProperty(double value){nodeX.set(value);}
            // Define a getter for the property itself
            public DoubleProperty nodeXProperty() {return nodeX;}

            // Define the property
            private DoubleProperty nodeY = new SimpleDoubleProperty();
            // Define a getter for the property's value
            public final double getnodeYProperty(){return nodeY.get();}
            // Define a setter for the property's value
            public final void setNodeYProperty(double value){nodeY.set(value);}
            // Define a getter for the property itself
            public DoubleProperty nodeYProperty() {return nodeY;}


    // a draggable anchor displayed around a point.
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

        // make a node movable by dragging it around with the mouse.
        private void enableDrag() {
            final Delta dragDelta = new Delta();
            setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    // record a delta distance for the drag and drop operation.
                    dragDelta.x = getCenterX() - mouseEvent.getX();
                    dragDelta.y = getCenterY() - mouseEvent.getY();
                    getScene().setCursor(Cursor.MOVE);
                }
            });
            setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    getScene().setCursor(Cursor.HAND);
                }
            });
            setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    double newX = mouseEvent.getX() + dragDelta.x;
                    if (newX > 0 && newX < getScene().getWidth()) {
                        setCenterX(newX);
                    }
                    double newY = mouseEvent.getY() + dragDelta.y;
                    if (newY > 0 && newY < getScene().getHeight()) {
                        setCenterY(newY);
                    }
                }
            });
            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.HAND);
                    }
                }
            });
            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.DEFAULT);
                    }
                }
            });
        }

        // records relative x and y co-ordinates.
        private class Delta { double x, y; }
    }
}
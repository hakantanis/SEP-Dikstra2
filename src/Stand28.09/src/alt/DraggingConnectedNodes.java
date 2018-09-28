import java.util.Random;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class DraggingConnectedNodes extends Application {

    private final Random rng = new Random();

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        ObjectProperty<Node> lastUnconnectedNode = new SimpleObjectProperty<>();
        pane.setOnMouseClicked(e -> {
            Circle c = createDraggingCircle(e.getX(), e.getY(), 15, pane, randomColor());
            pane.getChildren().add(c);
            if (lastUnconnectedNode.get() == null) {
                lastUnconnectedNode.set(c);
            } else {
                connect(lastUnconnectedNode.get(), c);
                lastUnconnectedNode.set(null);
            }

        });

        Scene scene = new Scene(pane, 600, 600);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void connect(Node n1, Node n2) {
        if (n1.getParent() != n2.getParent()) {
            throw new IllegalArgumentException("Nodes are in different containers");
        }
        Pane parent = (Pane) n1.getParent();
        Line line = new Line();
        line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = n1.getBoundsInParent();
            return b.getMinX() + b.getWidth() / 2 ;
        }, n1.boundsInParentProperty()));
        line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = n1.getBoundsInParent();
            return b.getMinY() + b.getHeight() / 2 ;
        }, n1.boundsInParentProperty()));
        line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = n2.getBoundsInParent();
            return b.getMinX() + b.getWidth() / 2 ;
        }, n2.boundsInParentProperty()));
        line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = n2.getBoundsInParent();
            return b.getMinY() + b.getHeight() / 2 ;
        }, n2.boundsInParentProperty()));
        parent.getChildren().add(line);
    }

    private Circle createDraggingCircle(double radius, double x, double y, Pane parent, Color fill) {
        Circle c = new Circle(radius, x, y, fill);
        ObjectProperty<Point2D> mouseLoc = new SimpleObjectProperty<>();
        c.setOnMousePressed(e -> mouseLoc.set(new Point2D(e.getX(), e.getY())));
        c.setOnMouseDragged(e -> {
            double deltaX = e.getX() - mouseLoc.get().getX();
            double deltaY = e.getY() - mouseLoc.get().getY();
            c.setCenterX(c.getCenterX() + deltaX);
            c.setCenterY(c.getCenterY() + deltaY);
            mouseLoc.set(new Point2D(e.getX(), e.getY()));
        });
        c.addEventFilter(MouseEvent.MOUSE_CLICKED, Event::consume);
        return c ;
    }

    private Color randomColor() {
        return new Color(rng.nextDouble(), rng.nextDouble(), rng.nextDouble(), 1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
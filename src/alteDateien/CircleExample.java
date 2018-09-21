
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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import sun.invoke.empty.Empty;

public class CircleExample extends Application {
    @Override
    public void start(Stage stage) {
        //Drawing a Circle
        Circle circle = new Circle();

        //Drawing a Line/Edges
        Line line = new Line ();

        //Setting the properties of the circle
        circle.setCenterX(300.0f);
        circle.setCenterY(135.0f);
        circle.setRadius(100.0f);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITESMOKE);

        //Setting the properties for the line
        line.setStartX(100);
        line.setEndX(200);
        line.setStroke(Color.RED);
        line.setFill(Color.RED);

        //Creating a Group object for the circle
        Group root = new Group(circle);

        //Creating a Group object for the line
        Group roots = new Group(line);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);
        //Scene scenes = new Scene(roots, width: 200, height: 50);

        //Setting the properties of the line (empty has to be done for Buschermöhle)


        //Setting title to the Stage
        stage.setTitle("Drawing a Circle");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
        Line line1 = new Line(50, 050, 100, 200);

    }

}       
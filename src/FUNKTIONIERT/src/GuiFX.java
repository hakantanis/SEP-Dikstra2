import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GuiFX extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage stage) throws Exception {
		
		AnchorPane layout = new AnchorPane();
		
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
			
		});
		
		save.setOnAction(new EventHandler<ActionEvent>() {
	
			public void handle(ActionEvent event) {
				FileChooser c = new FileChooser();
				c.showOpenDialog(stage);	
		}
			
		});
		
		layout.getChildren().add(mb);
		
		Button generate = new Button("generate Node");
		layout.getChildren().add(generate);
		AnchorPane.setTopAnchor(generate, 25.0);
		AnchorPane.setLeftAnchor(generate, 0.0);
		
		Button previousStep = new Button("previous Step");
		AnchorPane.setLeftAnchor(previousStep,0.0);
		AnchorPane.setBottomAnchor(previousStep,0.0);

		layout.getChildren().add(previousStep);
		
		Button nextStep= new Button("next Step");
		AnchorPane.setRightAnchor(nextStep,0.0);
		AnchorPane.setBottomAnchor(nextStep,0.0);
		layout.getChildren().add(nextStep);
		
		Scene scene = new Scene(layout, 1400, 800);
		stage.setScene(scene);
		
		stage.show();
		
	}

}

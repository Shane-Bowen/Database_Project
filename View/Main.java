/*
 * Name: Shane Bowen
 * ID: R00149085
 * Class: SD2-A 
 */

package View;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	//*************************************************
	//	Declaring TabPane as a global variable
	//*************************************************	

	static TabPane tabPane = new TabPane();

	public static void main(String[] args) {
		launch(args);
	}

	//*****************************************************************
	//	Creating the main BorderPane and adding tabs to it.
	//****************************************************************

	public void start(Stage primaryStage) throws Exception {

		try {
			BorderPane mainPane = new BorderPane();
			Group root = new Group();
			Scene scene = new Scene(root,700,500);

			Staff staff = new Staff();
			Client client = new Client();
			Job job = new Job();
			Parts parts = new Parts();
			Orders order = new Orders();
			Courier courier = new Courier();
			
			tabPane.getTabs().addAll(staff, client, job, parts, order, courier);
		
			mainPane.setCenter(tabPane);

			mainPane.prefHeightProperty().bind(scene.heightProperty());
			mainPane.prefWidthProperty().bind(scene.widthProperty());

			root.getChildren().add(mainPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
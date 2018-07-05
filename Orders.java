package Application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Orders extends Tab {

	private DB_Connection connection;

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	private void createButtonActionPerformed(String orderNo, String orderedByDate, String arrivalDate, String courierNo ) 
	{
		int result = connection.addOrders(orderNo, orderedByDate, arrivalDate, courierNo);

		if ( result == 1 ){
			AlertBox.display("Orders", "Order Not Added!");
		}

		else {
			AlertBox.display("Orders", "Order Not Added!");
		}
	}

	private void updateButtonActionPerformed(String orderNo, String orderedByDate, String arrivalDate, String courierNo) 
	{
		int result = connection.updateOrders( orderNo, orderedByDate, arrivalDate, courierNo );

		if ( result == 1 ){  
			AlertBox.display("Orders", "Order Updated!");
		}

		else {    	  
			AlertBox.display("Orders", "Order Not Updated!");
		}
	}

	public Orders() {

		connection = new DB_Connection();

		setText("Orders");

		BorderPane border = new BorderPane();

		Label title = new Label("Order Details");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		Label order = new Label("Order No:");
		GridPane.setConstraints(order, 1, 0); 

		TextField orderTextFld = new TextField();
		GridPane.setConstraints(orderTextFld, 2, 0);

		Label orderedDate = new Label("Order By Date:");
		GridPane.setConstraints(orderedDate, 1, 1);

		TextField orderedDateTextFld = new TextField();
		GridPane.setConstraints(orderedDateTextFld, 2, 1);

		Label arrival = new Label("Arrival By Date:");
		GridPane.setConstraints(arrival, 1, 2);

		TextField arrivalTextFld = new TextField();
		GridPane.setConstraints(arrivalTextFld, 2, 2);

		Label courier = new Label("Courier No:");
		GridPane.setConstraints(courier, 1, 3);

		TextField courierTextFld = new TextField();
		GridPane.setConstraints(courierTextFld, 2, 3);

		grid.getChildren().addAll(order, orderTextFld, orderedDate, orderedDateTextFld, arrival, arrivalTextFld, courier, courierTextFld);
		border.setCenter(grid);

		Button create = new Button ("Create");
		create.setOnAction((ActionEvent event) ->{

			String orderNo = orderTextFld.getText();
			if(orderNo.equals(""))
			{
				AlertBox.display("Error", "Order No can not be empty!");
			}

			else{
				String orderedByDate = orderedDateTextFld.getText();
				String arrivalDate = arrivalTextFld.getText();
				String courierNo = courierTextFld.getText();

				createButtonActionPerformed(orderNo, orderedByDate, arrivalDate, courierNo);
			}
		});

		Button update = new Button("Update");
		update.setOnAction((ActionEvent event) ->{

			String orderNo = orderTextFld.getText();
			if(orderNo.equals(""))
			{
				AlertBox.display("Error", "Order No can not be empty!");
			}
			
			else{
			String orderedByDate = orderedDateTextFld.getText();
			String arrivalDate = arrivalTextFld.getText();
			String courierNo = courierTextFld.getText();

			updateButtonActionPerformed(orderNo, orderedByDate, arrivalDate, courierNo);
			}
		});

		Button delete = new Button("Delete");
		delete.setOnAction((ActionEvent event) ->{

			RemoveBox.display("Remove", "Enter details of Order you want to remove!", "Order No");

		});

		Button quit = new Button ("Quit");
		quit.setOnAction(e -> closeProgram());

		//**********************************************************
		// Creating the new layout HBox and adding the Reset and
		// Quit button to it
		//**********************************************************

		HBox hbox = new HBox(5);
		hbox.getChildren().addAll(create, delete, update, quit);
		BorderPane.setMargin(hbox, new Insets(10));
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		border.setBottom(hbox);

		setContent(border);		
	}
}
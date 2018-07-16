package View;

import javafx.stage.*;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import Connection.DB_Connection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;

public class RemoveBox   {

	private static DB_Connection connection;

	public static void display(String title, String message, String item) {
		Stage window = new Stage();
		VBox layout = new VBox(10);

		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(300);
		window.setMinHeight(100);

		Label label = new Label();
		label.setText(message);

		Label label1 = new Label();
		label1.setText(item + ": ");

		TextField Input = new TextField();

		layout.getChildren().addAll(label, label1, Input);

		//**********************************************************
		// if title equals remove then we display the prompt which
		// asks the user what person they want to remove
		//**********************************************************

		Button removeButton = new Button("Remove");

		layout.getChildren().add(removeButton);

		removeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if(item == "Staff No")
				{
					connection = new DB_Connection();

					String number = Input.getText();
					int result = connection.deleteStaff(number);

					if(result == 1)
					{
						AlertBox.display("Remove", "Staff has been removed!");
					}

					else{
						AlertBox.display("Remove", "Staff has not been removed!");
					}
				}

				else if(item == "Client No")
				{
					connection = new DB_Connection();

					String number = Input.getText();
					int result = connection.deleteClient(number);

					if(result == 1)
					{
						AlertBox.display("Remove", "Client has been removed!");
					}

					else{
						AlertBox.display("Remove", "Client has not been removed!");
					}
				}

				else if(item == "Job No")
				{
					connection = new DB_Connection();

					String number = Input.getText();
					int result = connection.deleteJob(number);

					if(result == 1)
					{
						AlertBox.display("Remove", "Job has been removed!");
					}

					else{
						AlertBox.display("Remove", "Job has not been removed!");
					}
				}

				else if(item == "Part No")
				{
					connection = new DB_Connection();

					String number = Input.getText();
					int result = connection.deletePart(number);

					if(result == 1)
					{
						AlertBox.display("Remove", "Part has been removed!");
					}

					else{
						AlertBox.display("Remove", "Part has not been removed!");
					}
				}

				else if(item == "Order No")
				{
					connection = new DB_Connection();

					String number = Input.getText();
					int result = connection.deleteOrder(number);

					if(result == 1)
					{
						AlertBox.display("Remove", "Order has been removed!");
					}
					
					else{
						AlertBox.display("Remove", "Order has not been removed!");
					}
				}

				else if(item == "Courier No")
				{
					connection = new DB_Connection();

					String number = Input.getText();
					int result = connection.deleteCourier(number);

					if(result == 1)
					{
						AlertBox.display("Remove", "Courier has been removed!");
					}
					
					else{
						AlertBox.display("Remove", "Courier has not been removed!");
					}
				}

				window.close();
			}      
		});

		//Display window and wait for it to be closed before returning
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
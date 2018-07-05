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

public class Courier extends Tab {

	private DB_Connection connection;

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	private void createButtonActionPerformed(String courierNo, String firstName, String lastName, String telNo ) 
	{
		int result = connection.addCourier(courierNo, firstName, lastName, telNo);

		if ( result == 1 ){
			AlertBox.display("Courier", "Courier Added");
		}

		else {  
			AlertBox.display("Courier", "Courier Not Added");
		}
	}

	private void updateButtonActionPerformed(String courierNo, String firstName, String lastName, String telNo ) 
	{
		int result = connection.updateCourier( courierNo, firstName, lastName, telNo );

		if ( result == 1 ){  
			AlertBox.display("Courier", "Courier Updated!");
		}

		else {    	  
			AlertBox.display("Courier", "Courier Not Updated!");
		}
	}

	public Courier() {

		connection = new DB_Connection();

		setText("Courier");

		BorderPane border = new BorderPane();

		Label title = new Label("Courier Details");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		Label courier = new Label("Courier No:");
		GridPane.setConstraints(courier, 1, 0); 

		TextField courierTextFld = new TextField();
		GridPane.setConstraints(courierTextFld, 2, 0);

		Label first = new Label("First Name:");
		GridPane.setConstraints(first, 1, 1);

		TextField firstTextFld = new TextField();
		GridPane.setConstraints(firstTextFld, 2, 1);

		Label last = new Label("Last Name:");
		GridPane.setConstraints(last, 1, 2);

		TextField lastTextFld = new TextField();
		GridPane.setConstraints(lastTextFld, 2, 2);

		Label tel = new Label("Tel No:");
		GridPane.setConstraints(tel, 1, 3);

		TextField telTextFld = new TextField();
		GridPane.setConstraints(telTextFld, 2, 3);

		grid.getChildren().addAll(courier, courierTextFld, first, firstTextFld, last, lastTextFld, tel, telTextFld);
		border.setCenter(grid);

		Button create = new Button ("Create");
		create.setOnAction((ActionEvent event) ->{

			String courierNo = courierTextFld.getText();

			if(courierNo.equals(""))
			{
				AlertBox.display("Error", "Courier No can not be blank!");
			}

			else{
				String firstName = firstTextFld.getText();
				String lastName = lastTextFld.getText();
				String telNo = telTextFld.getText();

				createButtonActionPerformed(courierNo, firstName, lastName, telNo);
			}
		});

		Button update = new Button("Update");
		update.setOnAction((ActionEvent event) ->{

			String courierNo = courierTextFld.getText();

			if(courierNo.equals(""))
			{
				AlertBox.display("Error", "Courier No can not be blank!");
			}
			
			else{
			String firstName = firstTextFld.getText();
			String lastName = lastTextFld.getText();
			String telNo = telTextFld.getText();

			updateButtonActionPerformed(courierNo, firstName, lastName, telNo);
			}

		});


		Button delete = new Button("Delete");
		delete.setOnAction((ActionEvent event) ->{

			RemoveBox.display("Remove", "Enter details of Courier you want to remove!", "Courier No");

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
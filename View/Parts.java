package View;

import Connection.DB_Connection;
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

public class Parts extends Tab {

	private DB_Connection connection;

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	private void createButtonActionPerformed(String partNo, String Description, Integer price, String orderNo ) 
	{
		int result = connection.addParts(partNo, Description, price, orderNo);

		if ( result == 1 ){
			AlertBox.display("Parts", "Part Added!");
		}

		else {  
			AlertBox.display("Parts", "Part Not Added!");
		}
	}

	private void updateButtonActionPerformed(String partNo, String Description, Integer price, String orderNo) 
	{
		int result = connection.updateParts( partNo, Description, price, orderNo );

		if ( result == 1 ){  
			AlertBox.display("Parts", "Part Updated!");
		}

		else {    	  
			AlertBox.display("Parts", "Part Not Updated!");
		}
	}

	public Parts() {

		connection = new DB_Connection();

		setText("Parts");

		BorderPane border = new BorderPane();

		Label title = new Label("Part Details");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		Label part = new Label("Part No:");
		GridPane.setConstraints(part, 1, 0); 

		TextField partTextFld = new TextField();
		GridPane.setConstraints(partTextFld, 2, 0);

		Label desc = new Label("Description:");
		GridPane.setConstraints(desc, 1, 1);

		TextField descTextFld = new TextField();
		GridPane.setConstraints(descTextFld, 2, 1);

		Label cost = new Label("Price:");
		GridPane.setConstraints(cost, 1, 2);

		TextField priceTextFld = new TextField();
		GridPane.setConstraints(priceTextFld, 2, 2);

		Label order = new Label("OrderNo:");
		GridPane.setConstraints(order, 1, 3);

		TextField orderTextFld = new TextField();
		GridPane.setConstraints(orderTextFld, 2, 3);

		grid.getChildren().addAll(part, partTextFld, desc, descTextFld, cost, priceTextFld, order, orderTextFld);
		border.setCenter(grid);

		Button create = new Button ("Create");
		create.setOnAction((ActionEvent event) ->{

			String partNo = partTextFld.getText();

			if(partNo.equals(""))
			{
				AlertBox.display("Error", "Part No can not be blank!");
			}

			else{
				String Description = descTextFld.getText();
				Integer price = Integer.valueOf(priceTextFld.getText());
				String orderNo = orderTextFld.getText();

				createButtonActionPerformed(partNo, Description, price, orderNo);
			}
		});

		Button update = new Button("Update");
		update.setOnAction((ActionEvent event) ->{

			String partNo = partTextFld.getText();

			if(partNo.equals(""))
			{
				AlertBox.display("Error", "Part No can not be blank!");
			}
			
			else{
			String Description = descTextFld.getText();
			Integer price = Integer.valueOf(priceTextFld.getText());
			String orderNo = orderTextFld.getText();

			updateButtonActionPerformed(partNo, Description, price, orderNo);
			}

		});

		Button delete = new Button("Delete");
		delete.setOnAction((ActionEvent event) ->{

			RemoveBox.display("Remove", "Enter details of Parts you want to remove!", "Part No");

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